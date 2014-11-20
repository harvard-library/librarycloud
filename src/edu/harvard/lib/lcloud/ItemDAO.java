/**********************************************************************
 * Copyright (c) 2012 by the President and Fellows of Harvard College
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA.
 *
 * Contact information
 *
 * Office for Information Systems
 * Harvard University Library
 * Harvard University
 * Cambridge, MA  02138
 * (617)495-3724
 * hulois@hulmail.harvard.edu
 **********************************************************************/
package edu.harvard.lib.lcloud;

import gov.loc.mods.v3.ModsType;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer.RemoteSolrException;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

/**
*
* ItemDAO makes queries to the solr index via solrj methods; item id info or query paramters
* are parsed and mapped into solr query syntax
* 
* @author Michael Vandermillen
*
*/
public class ItemDAO {
	Logger log = Logger.getLogger(ItemDAO.class);	
    private int limit = 10;
    private Facet facet = null;
	
	/**
	 * Returns a MODS record for a given recordIdentifier.
	 * @param id    a recordIdentifier for a solr document
	 * @return      the ModsType for this recordidentifier
	 * @see         ModsType
	 */
	public ModsType getMods(String id) throws JAXBException {
		SolrDocumentList docs;
		SolrDocument doc;
		ModsType modsType = new ModsType();
	    HttpSolrServer server = null;
		try {		   
		    server = SolrServer.getSolrConnection();
		    SolrQuery query = new SolrQuery("recordIdentifier:" + id);	
		    QueryResponse response = server.query(query);
		    docs = response.getResults();
		    if (docs.size() == 0)
		    	throw new ResourceNotFoundException("Item " + id + " not found");
		    else {
			    doc = docs.get(0);
	        	modsType = getModsType(doc);
		    }
		}
		catch (SolrServerException  se) {
			se.printStackTrace();
			log.error(se.getMessage());
		}
		return modsType;
	}
	
	/**
	 * Returns search results for a given query.
	 * @param queryParams query parameters to map to a solr query
	 * @return      the SearchResults for this query
	 * @see         SearchResults
	 */
	public SearchResults getResults(MultivaluedMap<String, String> queryParams) throws JAXBException {
		SolrDocumentList docs = doQuery(queryParams);
    	// here is where we would throw an exception for results of 0
    	// but we want to pass back a 200 with 0 results found
	    //if (docs.getNumFound() == 0)
		
		SearchResults results = buildFullResults(docs);
		return results;	
	}

	/**
	 * Returns search results for a given query; the slim version of search results excludes the 
	 * "items" wrapper eiement, for better conversion to json. 
	 * @param queryParams query parameters to map to a solr query
	 * @return      the SearchResultsSlim object for this query
	 * @see         SearchResultsSlim
	 */
	public SearchResultsSlim getSlimResults(MultivaluedMap<String, String> queryParams) throws JAXBException {
		SolrDocumentList docs = doQuery(queryParams);
    	// here is where we would throw an exception for results of 0
    	// but we want to pass back a 200 with 0 results found
	    //if (docs.getNumFound() == 0)
		
		SearchResultsSlim results = buildSlimResults(docs);
		return results;	
	}

	/**
	 * Returns a SolrDocumentList for a given set of search parameters. Search parameters are parsed 
	 * and mapped into solr (solrj) query syntax, and solr query is performed.
	 * @param queryParams query parameters to map to a solr query
	 * @return      the SolrDocumentList for this query
	 * @see         SolrDocumentList
	 */
	
	private SolrDocumentList doQuery(MultivaluedMap<String, String> queryParams) {
		SolrDocumentList docs = null;		
	    HttpSolrServer server = null;
	    String queryStr = "";
	    SolrQuery query = new SolrQuery();
	    System.out.println("queryParams: " + queryParams.size());
   
	    ArrayList<String> queryList = new ArrayList<String>();
	    server = SolrServer.getSolrConnection();

	    if (queryParams.size() > 0) {
	    
		    for (String key : queryParams.keySet()) {
	    		String value = queryParams.getFirst(key);
		    	System.out.println(key + " : " + queryParams.getFirst(key) +"\n");
			    if (key.equals("start")) {
			    	int startNo = Integer.parseInt(value);
			    	if (startNo < 0)
			    		startNo = 0;
			    	query.setStart(startNo);
			    }	
			    else if (key.equals("limit")) {
			    	limit = Integer.parseInt(value);
			    	query.setRows(limit);
			    }	
			    else if (key.equals("sort.asc") || key.equals("sort"))
			    	query.setSort(value, ORDER.asc);
			    else if (key.equals("sort.desc"))
			    	query.setSort(value, ORDER.desc);
			    else if (key.startsWith("facet")){
			    	query.setFacet(true);
			    	String[] facetArray = value.split(",");
			    	for(String f: facetArray){
			    		query.addFacetField(f);
			    	}
			    }
			    else {
			    	if (key.endsWith("_exact"))
			    		queryList.add(key.replace("_exact", "") + ":\"" + value + "\"");
			    	else {
			    		value = value.trim();
			    		if (value.contains(" OR "))
			    			value = "(" + value.replaceAll(" OR ", "+OR+") + ")";
			    		if (value.contains(" "))
				    		value = "( " + value.replaceAll(" ", " AND ") + ")";
			    		if (value.contains(":"))
			    			value = "\"" + value + "\"";
			    		if (key.equals("q")) {
			    			if (!value.equals("*")) {
			    				queryList.add("keyword:" + value);
			    			}	
			    		}	
			    		else
			    			if (!key.equals("callback"))
			    				queryList.add(key + "_keyword:" + value);
			    	}	
			    }
		    }

		    Iterator<String> it = queryList.iterator();
	
		    while(it.hasNext()) {
		    	String qTerm = (String)it.next();
		    	System.out.print("QT: " + qTerm + "\n");
		    	queryStr += qTerm;
		    	System.out.print("QS: " + queryStr + "\n");
		    	if (it.hasNext())
		    		queryStr += " AND ";
		    }
		    if (queryList.size() == 0)
		    	queryStr = "*:*";
	    }
	    else {
	    	queryStr = "*:*";
	    }
	    
	    System.out.print("queryStr: " + queryStr);
	    query.setQuery(queryStr);
	    QueryResponse response = null;
	    try {
	    	response = server.query(query);
	    }
		catch (SolrServerException  se) {
			log.error(se.getMessage());
			throw new BadParameterException(se.getMessage()); 
		}
	    catch (RemoteSolrException rse) {
	    	if (rse.getMessage().contains("SyntaxError")) {
	    		log.error("solr syntax error");
	    		throw new BadParameterException("Incorrect query syntax");
	    	}	
	    	else {
	    		String msg = rse.getMessage().replace("_keyword","");
	    		log.error(msg);
	    		throw new BadParameterException("Incorrect query syntax:" + msg);
	    	}	
	    }
	    List<FacetField> facets = response.getFacetFields();
	    facet = null;
	    if (facets != null) {
		    facet = new Facet();
		    List<FacetType> facetTypes = new ArrayList<FacetType>();
	    	for(FacetField facetField : facets)
	        {	
	        	List<FacetTerm> facetTerms = new ArrayList<FacetTerm>();
	        	FacetType facetType = new FacetType();
	        	facetType.setFacetName(facetField.getName());
	            List<FacetField.Count> facetEntries = facetField.getValues();
	            for(FacetField.Count fcount : facetEntries)
	            {
	            	if (fcount.getCount() > 0) {
	            	FacetTerm facetTerm = new FacetTerm();
	            	facetTerm.setTermName(fcount.getName());
	            	facetTerm.setTermCount(fcount.getCount());
	                //System.out.println(fcount.getName() + ": " + fcount.getCount());
	            	facetTerms.add(facetTerm);
	            	}
	            }
	            facetType.setFacetTerms(facetTerms);
	            facetTypes.add(facetType);
	        }
        	facet.setFacetTypes(facetTypes);  
	    }
    	docs = response.getResults();
		return docs;
	}

	/**
	 * Returns SearchResults for a given SolrDocumentList. A full results object with an "items" wrapper
	 * element around the mods items is used to logically separate pagination, items and facets in the XML
	 * @param doc   solr document list to build results
	 * @return      the SearchResults object for this solr result
	 * @see         SearchResults
	 */
	
	private SearchResults buildFullResults(SolrDocumentList docs) {
		SearchResults results = new SearchResults();	
		Pagination pagination = new Pagination();
	    pagination.setNumFound(docs.getNumFound());
	    pagination.setStart(docs.getStart());
	    pagination.setRows(limit);
	    //List<ModsType> modsTypes = new ArrayList<ModsType>();
	    ItemGroup itemGroup = new  ItemGroup();
	    List<Item> items = new ArrayList<Item>();
        for (final SolrDocument doc : docs) {
        	Item item = new Item();
        	ModsType modsType = null;
        	try {
        		modsType = (new ItemDAO()).getModsType(doc);
			} catch (JAXBException je) {
				log.error(je.getMessage());
				je.printStackTrace();
			}	
    	    //modsTypes.add(modsType);
    	    //items.add(item);
        	item.setModsType(modsType);
        	items.add(item);
        }
        //items.setModsType(modsType);
        itemGroup.setItems(items);
		results.setItemGroup(itemGroup);
		results.setPagination(pagination);
		if (facet != null)
			results.setFacet(facet);
		return results;
	}

	/**
	 * Returns SearchResultsSlim for a given SolrDocumentList. A "slimmer" results object without the 
	 * "items" wrapper element is created for better transform to json.
	 * @param doc   solr document list to build results
	 * @return      the SearchResultsSlim object for this solr result
	 * @see         SearchResultsSlim
	 */
	
	private SearchResultsSlim buildSlimResults(SolrDocumentList docs) {
		SearchResultsSlim results = new SearchResultsSlim();	
		Pagination pagination = new Pagination();
	    pagination.setNumFound(docs.getNumFound());
	    pagination.setStart(docs.getStart());
	    pagination.setRows(limit);
	    //List<ModsType> modsTypes = new ArrayList<ModsType>();
	    List<Item> items = new ArrayList<Item>();
        for (final SolrDocument doc : docs) {
        	Item item = new Item();
        	ModsType modsType = null;
        	try {
        		modsType = (new ItemDAO()).getModsType(doc);
			} catch (JAXBException je) {
				log.error(je.getMessage());
				je.printStackTrace();
			}	
        	item.setModsType(modsType);
        	items.add(item);
        }
		results.setItems(items);
		results.setPagination(pagination);
		if (facet != null)
			results.setFacet(facet);
		return results;
	}
	
	/**
	 * 
	 * Returns a mods document, which is embedded in escaped xml form in the 
	 * originalMods field of each solr document. This escaped string is unmarshalled (jaxb)
	 * into a loc.gov.mods.v3.ModsType (and all child elements) and returned as an individual item
	 * or added to a search result set
	 * @param doc a SolrDocument from which to extract the mods record
	 * @return      the ModsType
	 * @see         ModsType
	 */
    protected ModsType getModsType(SolrDocument doc) throws JAXBException {
    	String modsString = (String) doc.getFieldValue("originalMods");
    	Unmarshaller unmarshaller = JAXBHelper.context.createUnmarshaller();
        StringReader reader = new StringReader(modsString);
        ModsType modsType = (ModsType) ((JAXBElement)(unmarshaller.unmarshal(reader))).getValue();
        return modsType;
    }
    
	/**
	 * 
	 * Marshals a SearchResult or ModsType object to an XML string. This string
	 * is converted to json using the org.json package. Order of the json array is
	 * not guaranteed (as per spec); TO DO (201405007) - investigate means for
	 * preserving XML order (json schema?)
	 * 
	 * @param obj a a SearchResult or ModsType object for conversion to json string
	 * @return      the json String
	 */
	protected String writeJson(Object obj) throws JAXBException
	{
	    StringWriter sw = new StringWriter();
	    String jsonString = null;
	    Marshaller jaxbMarshaller = JAXBHelper.context.createMarshaller();
	    jaxbMarshaller.marshal(obj, sw);

    	try {
    		String xml = sw.toString();
            JSONObject xmlJSONObj = XML.toJSONObject(xml);
            jsonString = xmlJSONObj.toString(5);
            ;System.out.println(jsonString);
        } catch (JSONException je) {
        	log.error(je.getMessage());
        	je.printStackTrace();
        }
	    return jsonString;

	}	
	

	/**
	 * 
	 * Marshals a SearchResult or ModsType object to an XML string. Uses xslt transform 
	 * rather than xml to json class (the xsl preserves order, the class does not)
	 * 
	 * @param obj a a SearchResult or ModsType object for conversion to json string
	 * @return      the json String
	 */
	protected String transform(String xmlString, String xslFilename) 
	{
		//StringWriter sw = marshallObject(obj);
	    String result = null;
	    try {
	        StringReader reader = new StringReader(xmlString);
	        StringWriter writer = new StringWriter();
	        TransformerFactory tFactory = TransformerFactory.newInstance();
	        Transformer transformer = tFactory.newTransformer(
	                new javax.xml.transform.stream.StreamSource(this.getClass().getClassLoader().getResourceAsStream(xslFilename)));

	        transformer.transform(
	                new javax.xml.transform.stream.StreamSource(reader), 
	                new javax.xml.transform.stream.StreamResult(writer));

	        result = writer.toString();
	    } catch (Exception e) {
	    	log.error(e.getMessage());
	        e.printStackTrace();
	    }
	    return result;
	}
	
	protected String marshallObject(Object obj) throws JAXBException {
	    StringWriter sw = new StringWriter();
	    String jsonString = null;
	    Marshaller jaxbMarshaller = JAXBHelper.context.createMarshaller();
	    jaxbMarshaller.marshal(obj, sw);	
	    return sw.toString();
	}
	
	
	// deprecated, keep for now - this is how we would provide access to individual solr fields
	// but we are instead displaying the embedded mods record in 1 solr field;
	// the old Item class (individual fields) has been moved to SolrItem for possible future use
	private Item setItem(SolrDocument doc) {

		Item item = new Item();
		/*
		for (Map.Entry<String, Object> entry : doc.getFieldValueMap().entrySet())
		 {
		     System.out.println(entry.getKey() + "/" + entry.getValue());
		 }
		 */
		//item.setMods((String) doc.getFieldValue("originalMods"));
		/*
		item.setOriginal((String) doc.getFieldValue("original"));
		item.setAbstractTOC((ArrayList) doc.getFieldValue("abstractTOC"));
		item.setClassification((ArrayList) doc.getFieldValue("classification"));
		item.setCopyrightDate((ArrayList) doc.getFieldValue("copyrightDate"));
		item.setDateCaptured((ArrayList) doc.getFieldValue("dateCaptured"));
		item.setDateCreated((ArrayList) doc.getFieldValue("dateCreated"));
		item.setDateIssued((ArrayList) doc.getFieldValue("dateIssued"));
		item.setEdition((String) doc.getFieldValue("edition"));
		item.setGenre((String) doc.getFieldValue("genre"));
		item.setIdentifier((ArrayList) doc.getFieldValue("identifier"));
		item.setLanguageCode((ArrayList) doc.getFieldValue("languageCode"));
		item.setName((ArrayList) doc.getFieldValue("name"));
		item.setOriginDate((String) doc.getFieldValue("originDate"));
		item.setOriginPlace((ArrayList) doc.getFieldValue("originPlace"));
		item.setPhysicalDescription((String) doc.getFieldValue("physicalDescription"));
		item.setPublisher((String) doc.getFieldValue("publisher"));
		item.setRecordIdentifier((String) doc.getFieldValue("recordIdentifier"));
		item.setResourceType((String) doc.getFieldValue("resourceType"));
		item.setRole((ArrayList) doc.getFieldValue("role"));
		item.setSource((String) doc.getFieldValue("source"));
		item.setSubjectPlace((ArrayList) doc.getFieldValue("subjectPlace"));
		item.setTitle((String) doc.getFieldValue("title"));
		*/
		
		return item;
	}

	
	// deprecated - use getModsType instead, rewrite and use this if we decide to wrap
	// the mods document in an <item> tag
	public Item getItem(String id) {
		SolrDocumentList docs;
		SolrDocument doc;
		Item item = new Item();
	    HttpSolrServer server = null;
		try {		   
		    server = SolrServer.getSolrConnection();
		    SolrQuery query = new SolrQuery("recordIdentifier:" + id);	
		    QueryResponse response = server.query(query);
		    docs = response.getResults();
		    if (docs.size() == 0)
		    	item = null;
		    else {
			    doc = docs.get(0);
	        	item = setItem(doc);
		    }
		}
		catch (SolrServerException  se) {
			log.error(se.getMessage());
			se.printStackTrace();
		}
		return item;
	}
}