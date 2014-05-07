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

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.DocumentObjectBinder;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer.RemoteSolrException;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.eclipse.persistence.jaxb.MarshallerProperties;
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
		    	throw new ResourceNotFoundException("Item, " + id + ", is not found");
		    else {
			    doc = docs.get(0);
	        	modsType = getModsType(doc);
		    }
		}
		catch (SolrServerException  se) {
			  System.out.println(se);
		}
		return modsType;
	}
	
	
	/**
	 * Returns search results for a given query. Search parameters are parsed and mapped into
	 * solr (solrj) query syntax
	 * TO DO (20140506): consider pulling the query building guts into a separate queryBuilder method
	 * @param queryParams query parameters to map to a solr query
	 * @return      the SearchResults for this query
	 * @see         SearchResults
	 */
	public SearchResults getResults(MultivaluedMap<String, String> queryParams) throws JAXBException {
		SearchResults results = new SearchResults();
		//List<Item> items = new ArrayList<Item>();
		Item item = new Item();
		SolrDocumentList docs = null;		
	    HttpSolrServer server = null;
	    ArrayList queryList = new ArrayList();
	    server = SolrServer.getSolrConnection();
	    SolrQuery query = new SolrQuery();
	    int limit = 10;
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
		    		if (value.contains(" "))
			    		value = "( " + value.replaceAll(" ", " AND ") + ")";
		    		if (key.equals("q"))
			    		queryList.add("keyword:" + value);
		    		else
		    			queryList.add(key + "_keyword:" + value);
		    	}	
		    }
	    }

	    Iterator it = queryList.iterator();
	    String queryStr = "";
	    while(it.hasNext()) {
	    	String qTerm = (String)it.next();
	    	System.out.print("QT: " + qTerm + "\n");
	    	queryStr += qTerm;
	    	System.out.print("QS: " + queryStr + "\n");
	    	if (it.hasNext())
	    		queryStr += " AND ";
	    }
	    System.out.print("queryStr: " + queryStr);
	    query.setQuery(queryStr);
	    QueryResponse response = null;
	    try {
	    	response = server.query(query);
	    }
		catch (SolrServerException  se) {
			  throw new BadParameterException(se.getMessage()); 
		}
	    catch (RemoteSolrException rse) {
	    	if (rse.getMessage().contains("SyntaxError"))
	    		throw new BadParameterException("Incorrect query syntax");
	    	else {
	    		String msg = rse.getMessage().replace("_keyword","");
	    		throw new BadParameterException("Incorrect query syntax:" + msg);
	    	}	
	    }
	    List<FacetField> facets = response.getFacetFields();
	    Facet facet = new Facet();
	    List<FacetType> facetTypes = new ArrayList<FacetType>();
	    if (facets != null) {
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
    	// here is where we would throw an exception for results of 0
    	// but we want to pass back a 200 with 0 results found
	    //if (docs.getNumFound() == 0)
		Pagination pagination = new Pagination();
	    pagination.setNumFound(docs.getNumFound());
	    pagination.setStart(docs.getStart());
	    pagination.setRows(limit);
	    List<ModsType> modsTypes = new ArrayList<ModsType>();
        for (final SolrDocument doc : docs) {
        	//Item item = new Item();
        	ModsType modsType = null;
        	try {
    	    modsType = (new ItemDAO()).getModsType(doc);
			} catch (JAXBException je) {
				//TO DO - intelligent error handling
				System.out.println(je);
			}	
    	    modsTypes.add(modsType);
    	    //items.add(item);
        }
        //items.setModsType(modsType);
        item.setModsTypes(modsTypes);
		results.setItem(item);
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
    		//String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><results xmlns:mods=\"http://www.loc.gov/mods/v3\" xmlns=\"http://api.lib.harvard.edu/v2/item\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"><pagination><numFound>11</numFound><rows>2</rows><start>0</start></pagination><item><mods:mods version=\"3.4\"><mods:titleInfo><mods:title>Peanut research</mods:title></mods:titleInfo><mods:name type=\"corporate\"><mods:namePart>National Peanut Council</mods:namePart></mods:name></mods:mods></item><item><mods:mods version=\"3.4\"><mods:titleInfo><mods:nonSort>Die </mods:nonSort><mods:title>&quot;Peanuts&quot;, Verbreitung und ästhetische Formen</mods:title><mods:subTitle>ein Comic-Bestseller im Medienverbund</mods:subTitle></mods:titleInfo><mods:name type=\"personal\"><mods:namePart>Strobel, Ricarda</mods:namePart><mods:namePart type=\"date\">1954-</mods:namePart></mods:name></mods:mods></item></results>";
            JSONObject xmlJSONObj = XML.toJSONObject(xml);
            jsonString = xmlJSONObj.toString(5);
            //System.out.println(jsonString);
        } catch (JSONException je) {
        	//TO DO - error handling
            System.out.println(je.toString());
        }
	    return jsonString;

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
			  System.out.println(se);
		}
		return item;
	}
}