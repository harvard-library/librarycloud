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


import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
	 
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import gov.loc.mods.v3.ModsType;

/**
*
* ItemResource is the main entry point for item queries and individual item requests;
* It uses the jersey implementation (see web.xml) of jax-rs api for RESTful web services.
* Requests of type items/{id} are for an individual item;
* Requests of type items?<search parameters> are for searching the api;
* Default format is XML; dot notation (items.*) are for variant formats (json, dc and html);
* Request of pattern .dc and .html additionally rely on xslt in servlet filter (see web.xml);
* TO DO (20140506) - move dc (and html) xslt back from filter, to allow subsequent dc json serialization
* 
* @author Michael Vandermillen
*
*/

@Path ("/v2")
public class ItemResource {
	Logger log = Logger.getLogger(ItemResource.class); 
	ItemDAO itemdao = new ItemDAO();
	
	@GET @Path("items/{id}")
	@Produces (MediaType.APPLICATION_XML)
	public ModsType getItem(@PathParam("id") String id, @Context HttpServletResponse response) {
		log.info("getItem called for id: " + id);
		response.setHeader("Access-Control-Allow-Origin", "*");
		ModsType modsType = null; 
		try {
			modsType = itemdao.getMods(id);
		} catch (JAXBException je) {
			System.out.println(je);
			log.error(je.getMessage());
		}
		return modsType;
	}

	//duplicate of above to avoid error if user tries appending .xml (is there a better way?)
	@GET @Path("items/{id}.xml")
	@Produces (MediaType.APPLICATION_XML)
	public ModsType getItemXml(@PathParam("id") String id, @Context HttpServletResponse response) {
		log.info("getItem called for id: " + id);
		response.setHeader("Access-Control-Allow-Origin", "*");
		ModsType modsType = null; 
		try {
			modsType = itemdao.getMods(id);
		} catch (JAXBException je) {
			System.out.println(je);
			log.error(je.getMessage());
		}
		return modsType;
	}
	
	@GET @Path("items/{id}.json")
	@Produces ("application/json")
	public String getJsonItem(@PathParam("id") String id, @Context HttpServletResponse response) {
		log.info("getJsonItem called for id: " + id);
		response.setHeader("Access-Control-Allow-Origin", "*");
		ModsType modsType = null; 
		String modsJson = null;
		try {
			modsType = itemdao.getMods(id);
			String modsXml = itemdao.marshallObject(modsType);
			modsJson = itemdao.writeJson(modsXml);
			//modsJson = itemdao.transform(modsXml, Config.getInstance().JSON_XSLT);
		} catch (JAXBException je) {
			je.printStackTrace();
			log.error(je);
		}

		return modsJson;
	}

	@GET @Path("items/{id}")
	@Produces ("application/json")
	public String getJsonItemByHeader(@PathParam("id") String id, @Context HttpServletResponse response) {
		log.info("getJsonItem called for id: " + id);
		response.setHeader("Access-Control-Allow-Origin", "*");
		ModsType modsType = null; 
		String modsJson = null;
		try {
			modsType = itemdao.getMods(id);
			String modsXml = itemdao.marshallObject(modsType);
			modsJson = itemdao.writeJson(modsXml);
			//modsJson = itemdao.transform(modsXml, Config.getInstance().JSON_XSLT);
		} catch (JAXBException je) {
			je.printStackTrace();
			log.error(je);
		}

		return modsJson;
	}
	
	@GET @Path("items/{id}.dc")
	@Produces (MediaType.APPLICATION_XML)
	public String getDublinCoreItem(@PathParam("id") String id) {
		log.info("getDublinCoreItem called for id: " + id);
		ModsType modsType = null; 
		String dcXml = null;
		try {
			modsType = itemdao.getMods(id);
			String modsXml = itemdao.marshallObject(modsType);
			dcXml = itemdao.transform(modsXml, Config.getInstance().DC_XSLT); 
		} catch (JAXBException je) {
			je.printStackTrace();
			log.error(je);
		}

		return dcXml;
	}

	@GET @Path("items/{id}.dc.json")
	@Produces (MediaType.APPLICATION_JSON)
	public String getDublinCoreJsonItem(@PathParam("id") String id) {
		log.info("getDublinCoreItem called for id: " + id);
		ModsType modsType = null; 
		String dcJson = null;
		try {
			modsType = itemdao.getMods(id);
			String modsXml = itemdao.marshallObject(modsType);
			String dcXml = itemdao.transform(modsXml, Config.getInstance().DC_XSLT); 
			//dcJson = itemdao.transform(dcXml, Config.getInstance().JSON_XSLT); 
			dcJson = itemdao.writeJson(dcXml);
		} catch (JAXBException je) {
			je.printStackTrace();
			log.error(je);
		}

		return dcJson;
	}

	@GET @Path("items/{id}.dc")
	@Produces (MediaType.APPLICATION_JSON)
	public String getDublinCoreJsonItemByHeader(@PathParam("id") String id) {
		log.info("getDublinCoreItem called for id: " + id);
		ModsType modsType = null; 
		String dcJson = null;
		try {
			modsType = itemdao.getMods(id);
			String modsXml = itemdao.marshallObject(modsType);
			String dcXml = itemdao.transform(modsXml, Config.getInstance().DC_XSLT); 
			//dcJson = itemdao.transform(dcXml, Config.getInstance().JSON_XSLT); 
			dcJson = itemdao.writeJson(dcXml);
		} catch (JAXBException je) {
			je.printStackTrace();
			log.error(je);
		}

		return dcJson;
	}
	
	@GET @Path("items/{id}.html")
	@Produces (MediaType.APPLICATION_XML)
	public ModsType getHtmlItem(@PathParam("id") String id) {
		ModsType modsType = null; 
		try {
			modsType = itemdao.getMods(id);
		} catch (JAXBException je) {
			je.printStackTrace();
			log.error(je.getMessage());
		}

		return modsType;
	}	
	
	@GET @Path("items")
	@Produces (MediaType.APPLICATION_XML)
	public SearchResults getSearchResults(@Context UriInfo ui, @Context HttpServletResponse response) {
		log.info("getSearchResults made query: " + "TO DO");
	    MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
	    response.setHeader("Access-Control-Allow-Origin", "*");
	    //we don't currently need to use the pathParam
	    //MultivaluedMap<String, String> pathParams = ui.getPathParameters();

		SearchResults results = null;
		
		try {
			results = itemdao.getResults(queryParams);	
		} catch (JAXBException je) {
			je.printStackTrace();
			log.error(je.getMessage());
		}
		return results;
	}
	
	//duplicate of above to avoid error if user tries appending .xml (is there a better way?)
	@GET @Path("items.xml")
	@Produces (MediaType.APPLICATION_XML)
	public SearchResults getSearchResultsXML(@Context UriInfo ui, @Context HttpServletResponse response) {
		log.info("getSearchResults made query: " + "TO DO");
	    MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
	    response.setHeader("Access-Control-Allow-Origin", "*");
	    //we don't currently need to use the pathParam
	    //MultivaluedMap<String, String> pathParams = ui.getPathParameters();

		SearchResults results = null;
		
		try {
			results = itemdao.getResults(queryParams);	
		} catch (JAXBException je) {
			je.printStackTrace();
			log.error(je.getMessage());
		}
		return results;
	}
	
	@GET @Path("items.json")
	@Produces (MediaType.APPLICATION_JSON)
	public String getJsonSearchResults(@Context UriInfo ui, @Context HttpServletResponse response) {
		log.info("getJsonSearchResults made query: " + "TO DO");
	    MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
	    response.setHeader("Access-Control-Allow-Origin", "*");
		String resultsJson = null;
		try {
			SearchResultsSlim results = itemdao.getSlimResults(queryParams);	
			String resultsXml = itemdao.marshallObject(results);
			//resultsJson = itemdao.transform(resultsXml, Config.getInstance().JSON_XSLT);
			resultsJson = itemdao.writeJson(resultsXml);
		} catch (JAXBException je) {
			je.printStackTrace();
			log.error(je.getMessage());
		}	

		return resultsJson;
	}

	@GET @Path("items")
	@Produces (MediaType.APPLICATION_JSON)
	public String getJsonSearchResultsByHeader(@Context UriInfo ui, @Context HttpServletResponse response) {
		log.info("getJsonSearchResults made query: " + "TO DO");
	    MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
	    response.setHeader("Access-Control-Allow-Origin", "*");
		String resultsJson = null;
		try {
			SearchResultsSlim results = itemdao.getSlimResults(queryParams);	
			String resultsXml = itemdao.marshallObject(results);
			//resultsJson = itemdao.transform(resultsXml, Config.getInstance().JSON_XSLT);
			resultsJson = itemdao.writeJson(resultsXml);
		} catch (JAXBException je) {
			je.printStackTrace();
			log.error(je.getMessage());
		}	

		return resultsJson;
	}

	
	@GET @Path("items.dc")
	@Produces (MediaType.APPLICATION_XML)
	public String getDublinCoreSearchResults(@Context UriInfo ui) {
		log.info("getDublinCoreSearchResults made query: " + "TO DO");
	    MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		String resultsDC = null;
		
		try {
			SearchResults results = itemdao.getResults(queryParams);	
			String resultsXml = itemdao.marshallObject(results);
			resultsDC = itemdao.transform(resultsXml, Config.getInstance().DC_XSLT);
		} catch (JAXBException je) {
			je.printStackTrace();
			log.error(je.getMessage());
		}
		return resultsDC;
	}

	@GET @Path("items.dc.json")
	@Produces (MediaType.APPLICATION_JSON)
	public String getJsonDCSearchResults(@Context UriInfo ui, @Context HttpServletResponse response) {
		log.info("getJsonDCSearchResults made query: " + "TO DO");
	    MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
	    response.setHeader("Access-Control-Allow-Origin", "*");
		String resultsJson = null;
		try {
			SearchResultsSlim results = itemdao.getSlimResults(queryParams);	
			String resultsXml = itemdao.marshallObject(results);
			String resultsDC = itemdao.transform(resultsXml, Config.getInstance().DC_XSLT);
			//resultsJson = itemdao.transform(resultsDC, Config.getInstance().JSON_XSLT);
			resultsJson = itemdao.writeJson(resultsDC);
		} catch (JAXBException je) {
			je.printStackTrace();
			log.error(je.getMessage());
		}	

		return resultsJson;
	}

	@GET @Path("items.dc")
	@Produces (MediaType.APPLICATION_JSON)
	public String getJsonDCSearchResultsByHeader(@Context UriInfo ui, @Context HttpServletResponse response) {
		log.info("getJsonDCSearchResults made query: " + "TO DO");
	    MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
	    response.setHeader("Access-Control-Allow-Origin", "*");
		String resultsJson = null;
		try {
			SearchResultsSlim results = itemdao.getSlimResults(queryParams);	
			String resultsXml = itemdao.marshallObject(results);
			String resultsDC = itemdao.transform(resultsXml, Config.getInstance().DC_XSLT);
			//resultsJson = itemdao.transform(resultsDC, Config.getInstance().JSON_XSLT);
			resultsJson = itemdao.writeJson(resultsDC);
		} catch (JAXBException je) {
			je.printStackTrace();
			log.error(je.getMessage());
		}	

		return resultsJson;
	}
	
	@GET @Path("items.html")
	@Produces (MediaType.APPLICATION_XML)
	public SearchResults getHtmlSearchResults(@Context UriInfo ui) {
		log.info("getHtmlSearchResults made query: " + "TO DO");
	    MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		SearchResults results = null;
		
		try {
			results = itemdao.getResults(queryParams);	
		} catch (JAXBException je) {
			je.printStackTrace();
			log.error(je.getMessage());
		}
		return results;
	}

	@GET @Path("error")
	@Produces (MediaType.APPLICATION_XML)
	public void getHtmlSearchResults(@QueryParam("status") int status) {
		log.info("error: " + status);
		throw new LibCommException("Error: Please see documentation at link below", status);
	}
	
}
