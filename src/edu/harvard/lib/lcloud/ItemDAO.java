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
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.eclipse.persistence.jaxb.MarshallerProperties;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

/**
*
*
* @author Michael Vandermillen
*
*/
public class ItemDAO {

	/*
    static final JAXBContext context = initContext();

    private static JAXBContext initContext()  {
    	JAXBContext context = null;
    	try {
    		context = JAXBContext.newInstance(ModsType.class,SearchResults.class);
    	} catch (JAXBException je) {
    		System.out.println(je);
    	}
    	return context;
    }
    */
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
		    	modsType = null;
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
	
	public SearchResults getResults(String q, String title, String name, String start, String rows, String sort) throws JAXBException {
		long starttime = System.currentTimeMillis();

		SearchResults results = new SearchResults();
		List<Item> items = new ArrayList<Item>();
		SolrDocumentList docs = null;		
	    HttpSolrServer server = null;
		try {		   
		    server = SolrServer.getSolrConnection();
		    SolrQuery query = new SolrQuery();
		    ArrayList qList = new ArrayList();
		    String qStr = "";
		    if (q != null)
		    	qList.add("keyword:" + q);
		    if (title != null)
		    	qList.add("title_keyword:" + title);
		    if (name != null)
		    	qList.add("name_keyword:" + name);
		    Iterator it = qList.iterator();
		    while(it.hasNext()) {
		    	String qTerm = (String)it.next();
		    	System.out.print("QT: " + qTerm + "\n");
		    	qStr += qTerm;
		    	System.out.print("QS: " + qStr + "\n");
		    	if (it.hasNext())
		    		qStr += " AND ";
		    }
		    System.out.print("qStr: " + qStr);
		    query.setQuery(qStr);
		    if (start != null) 
		    	query.setStart(Integer.parseInt(start));
		    if (rows != null)
		    	query.setRows(Integer.parseInt(rows));
		    if (sort != null)
		    	query.setSort(sort, ORDER.asc);
		    	
		    QueryResponse response = server.query(query);
		    
		    docs = response.getResults();
		}
		catch (SolrServerException  se) {
			  System.out.println(se);
		}
		
		//List<ModsType> modsTypes = new ArrayList<ModsType>();
		Pagination pagination = new Pagination();
	    pagination.setNumFound(docs.getNumFound());
	    pagination.setStart(docs.getStart());
	    pagination.setRows(rows == null ? 10:Integer.parseInt(rows));
		System.out.println("\n1: " + ((System.currentTimeMillis() - starttime)));
        for (final SolrDocument doc : docs) {
        	Item item = new Item();
    	    ModsType modsType = getModsType(doc);
    	    item.setModsType(modsType);
    	    items.add(item);
        }
		System.out.println("\n2: " + ((System.currentTimeMillis() - starttime)));
        //items.setModsType(modsType);
		results.setItems(items);
		results.setPagination(pagination);
		return results;
	}
	
    public ModsType getModsType(SolrDocument doc) throws JAXBException {
    	String modsString = (String) doc.getFieldValue("originalMods");
    	//System.out.println(modsString);
    	//JAXBContext jc = JAXBContext.newInstance(ModsType.class);
        //Unmarshaller unmarshaller = jc.createUnmarshaller();
    	Unmarshaller unmarshaller = JAXBHelper.context.createUnmarshaller();
        //System.out.println(modsString);
        StringReader reader = new StringReader(modsString);
        ModsType modsType = (ModsType) ((JAXBElement)(unmarshaller.unmarshal(reader))).getValue();
        return modsType;
    }
	
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
	
	
	protected String writeJson(Object obj) throws JAXBException
	{
	    StringWriter sw = new StringWriter();
	    String jsonString = null;
		//JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
	    //Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	    Marshaller jaxbMarshaller = JAXBHelper.context.createMarshaller();
	    jaxbMarshaller.marshal(obj, sw);

    	try {
    		//System.out.println(sw.toString());
    		String xml = sw.toString();
    		//String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><results xmlns:mods=\"http://www.loc.gov/mods/v3\" xmlns=\"http://api.lib.harvard.edu/v2/item\" xmlns:xlink=\"http://www.w3.org/1999/xlink\"><pagination><numFound>11</numFound><rows>2</rows><start>0</start></pagination><item><mods:mods version=\"3.4\"><mods:titleInfo><mods:title>Peanut research</mods:title></mods:titleInfo><mods:name type=\"corporate\"><mods:namePart>National Peanut Council</mods:namePart></mods:name></mods:mods></item><item><mods:mods version=\"3.4\"><mods:titleInfo><mods:nonSort>Die </mods:nonSort><mods:title>&quot;Peanuts&quot;, Verbreitung und ästhetische Formen</mods:title><mods:subTitle>ein Comic-Bestseller im Medienverbund</mods:subTitle></mods:titleInfo><mods:name type=\"personal\"><mods:namePart>Strobel, Ricarda</mods:namePart><mods:namePart type=\"date\">1954-</mods:namePart></mods:name></mods:mods></item></results>";
            JSONObject xmlJSONObj = XML.toJSONObject(xml);
            jsonString = xmlJSONObj.toString(5);
            System.out.println(jsonString);
        } catch (JSONException je) {
            System.out.println(je.toString());
        }
	    return jsonString;

	}	
}
