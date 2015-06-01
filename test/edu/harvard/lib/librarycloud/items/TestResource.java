package edu.harvard.lib.librarycloud.items;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
	 
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import edu.harvard.lib.librarycloud.items.Item;
import edu.harvard.lib.librarycloud.items.ItemDAO;
import edu.harvard.lib.librarycloud.items.Pagination;
import edu.harvard.lib.librarycloud.items.SolrServer;
import edu.harvard.lib.librarycloud.items.SearchResultsMods;
import edu.harvard.lib.librarycloud.items.mods.ModsType;

@Path ("/v2")
public class TestResource {
	
	@GET @Path("test")
	public SearchResultsMods get(@Context UriInfo ui) {
	    MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
	    MultivaluedMap<String, String> pathParams = ui.getPathParameters();
		SearchResultsMods results = new SearchResultsMods();
		List<Item> items = new ArrayList<Item>();
		SolrDocumentList docs = null;		
	    HttpSolrServer server = null;
	    ArrayList queryList = new ArrayList();
	    server = SolrServer.getSolrConnection();
	    SolrQuery query = new SolrQuery();
	    int limit = 10;
    	for (String key : queryParams.keySet()) {
    		String value = queryParams.getFirst(key);
	    	System.out.println(key + " : " + queryParams.getFirst(key) +"\n");
		    if (key.equals("start")) 
		    	query.setStart(Integer.parseInt(value));
		    else if (key.equals("limit")) {
		    	limit = Integer.parseInt(value);
		    	query.setRows(limit);
		    }	
		    else if (key.equals("sort.asc") || key.equals("sort"))
		    	query.setSort(value, ORDER.asc);
		    else if (key.equals("sort.desc"))
		    	query.setSort(value, ORDER.desc);
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
	    	//QueryResponse response = server.query(query);
	    	response = server.query(query);
	    }
		catch (SolrServerException  se) {
			  System.out.println(se);
		}
    	docs = response.getResults();
	    if (docs.getNumFound() == 0)
	    	  throw new NotFoundException("none found");
		Pagination pagination = new Pagination();
	    pagination.setNumFound(docs.getNumFound());
	    pagination.setStart(docs.getStart());
	    pagination.setRows(limit);
        for (final SolrDocument doc : docs) {
        	Item item = new Item();
        	ModsType modsType = null;
        	try {
    	    modsType = (new ItemDAO()).unmarshallModsType(doc);
			} catch (JAXBException je) {
				//TO DO - intelligent error handling
				System.out.println(je);
			}	
    	    //item.setModsType(modsType);
    	    items.add(item);
        }
        //items.setModsType(modsType);
		//results.setItems(items);
		results.setPagination(pagination);
		return results;	    
	}
	
	/*
	protected static void marshalingExample(SearchResults results) throws JAXBException
	{
	    JAXBContext jaxbContext = JAXBContext.newInstance(SearchResults.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    
	    jaxbMarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
	    //jaxbMarshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, false);

	    jaxbMarshaller.marshal(results, System.out);
	}
	*/

	/*
	protected SearchResults getJsonResults(SearchResults results) throws JAXBException
	{
	    JAXBContext jaxbContext = JAXBContext.newInstance(SearchResults.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
   
	    jaxbMarshaller.setProperty(MarshallerProperties.MEDIA_TYPE, "application/json");
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    //jaxbMarshaller.setProperty(MarshallerProperties.JSON_INCLUDE_ROOT, false);
	     
	    //Marshal the employees list in console
	    jaxbMarshaller.marshal(results, System.out);
	    return results;
	}	
	*/

}
