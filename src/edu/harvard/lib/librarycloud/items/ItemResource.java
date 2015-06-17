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
package edu.harvard.lib.librarycloud.items;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
	 
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBException;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

import org.apache.log4j.Logger;
import org.glassfish.jersey.server.JSONP;

import edu.harvard.lib.librarycloud.items.dc.Metadata;
import edu.harvard.lib.librarycloud.items.mods.ModsType;

/**
*
* ItemResource is the main entry point for item queries and individual item requests;
* It uses the jersey implementation (see web.xml) of jax-rs api for RESTful web services.
* Requests of type items/{id} are for an individual item;
* Requests of type items?<search parameters> are for searching the api;
* 
* @author Michael Vandermillen
*
*/

@Path ("/v2")
public class ItemResource {
	Logger log = Logger.getLogger(ItemResource.class); 
	ItemDAO itemdao = new ItemDAO();
	
	// because of problems rendering json with moxy, xml and json now divided into separate methods
	//this one for xml
	@GET @Path("items/{id}")
	//@Produces ({"application/javascript", MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML + ";qs=0.9"})
	@Produces (MediaType.APPLICATION_XML)
	public ModsType getItem(@PathParam("id") String id) {
		log.info("getItem called for id: " + id);
		ModsType modsType = null; 
		try {
			modsType = itemdao.getMods(id);
		} catch (JAXBException je) {
			System.out.println(je);
			log.error(je.getMessage());
			throw new LibraryCloudException("Internal Server Error:" + je.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
		}
		//System.out.println("test");
		return modsType;
	}

	// because of problems rendering json with moxy, xml and json now divided into separate methods
	//this one for json	
	@GET @Path("items/{id}")
    @JSONP(queryParam = "callback")
	@Produces ({MediaType.APPLICATION_JSON + ";qs=0.9", "application/javascript;qs=0.9"})
	public String getItemJson(@PathParam("id") String id) {
		log.info("getItem called for id: " + id);
		ModsType modsType = null;
		String modsString = null;

		try {
			modsType = itemdao.getMods(id);
			modsString = itemdao.marshallObject(modsType);
		} catch (JAXBException je) {
			System.out.println(je);
			log.error(je.getMessage());
			throw new LibraryCloudException("Internal Server Error:" + je.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
		}
	    return itemdao.getJsonFromXml(modsString);
	}

	// because of problems rendering json with moxy, xml and json now divided into separate methods
	//this one for xml	
	@GET @Path("items/{id}.dc")
	@Produces (MediaType.APPLICATION_XML)
	public Metadata getDublinCoreItem(@PathParam("id") String id) {
		log.info("getDublinCoreItem called for id: " + id);
		//ModsType modsType = null;
		Metadata metadata = null;
		try {
			metadata = itemdao.getDublinCore(id);

		} catch (JAXBException je) {
			je.printStackTrace();
			log.error(je);
			throw new LibraryCloudException("Internal Server Error:" + je.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
		}

		return metadata;
	}

	// because of problems rendering json with moxy, xml and json now divided into separate methods
	//this one for json
	@GET @Path("items/{id}.dc")
    @JSONP(queryParam = "callback")
	@Produces ({MediaType.APPLICATION_JSON + ";qs=0.9", "application/javascript;qs=0.9"})
	public String getDublinCoreItemJson(@PathParam("id") String id) {
		log.info("getDublinCoreItemJson called for id: " + id);
		//ModsType modsType = null;
		Metadata metadata = null;
		String metadataString = null;
		try {
			metadata = itemdao.getDublinCore(id);
			metadataString = itemdao.marshallObject(metadata);

		} catch (JAXBException je) {
			je.printStackTrace();
			log.error(je);
			throw new LibraryCloudException("Internal Server Error:" + je.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
		}

		return itemdao.getJsonFromXml(metadataString);
	}
	
	// because of problems rendering json with moxy, xml and json now divided into separate methods
	//this one for xml
	@GET @Path("items")
	//@Produces ({"application/javascript", MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML + ";qs=0.9"})
	@Produces (MediaType.APPLICATION_XML)
	public SearchResultsMods getSearchResults(@Context UriInfo ui) {
		log.info("getSearchResults made query: " + "TO DO");
	    MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
	    //we don't currently need to use the pathParam
	    //MultivaluedMap<String, String> pathParams = ui.getPathParameters();

		SearchResultsMods results = null;
		try {
			results = itemdao.getModsResults(queryParams);	
		} catch (JAXBException je) {
			je.printStackTrace();
			log.error(je.getMessage());
			throw new LibraryCloudException("Internal Server Error:" + je.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
		}
		return results;
	}

	// because of problems rendering json with moxy, xml and json now divided into separate methods
	//this one for json
	@GET @Path("items")
    @JSONP(queryParam = "callback")
	@Produces ({MediaType.APPLICATION_JSON + ";qs=0.9", "application/javascript;qs=0.9"})
	public String getSearchResultsJson(@Context UriInfo ui) {
		log.info("getSearchResults made query: " + "TO DO");
	    MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
	    //we don't currently need to use the pathParam
	    //MultivaluedMap<String, String> pathParams = ui.getPathParameters();

		SearchResultsMods results = null;
		String resultsString = null;
		try {
			results = itemdao.getModsResults(queryParams);	
			String rawResultsString = itemdao.marshallObject(results);
			// need to fix pagination so they get serialized as numbers
			resultsString = itemdao.fixPaginationAndFacets(rawResultsString);
			
		} catch (JAXBException je) {
			je.printStackTrace();
			log.error(je.getMessage());
			throw new LibraryCloudException("Internal Server Error:" + je.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
		}
	    return itemdao.getJsonFromXml(resultsString);
	}

	// because of problems rendering json with moxy, xml and json now divided into separate methods
	//this one for xml
	@GET @Path("items.dc")
	@Produces (MediaType.APPLICATION_XML)
	public SearchResultsDC getDublinCoreSearchResults(@Context UriInfo ui) {
		log.info("getDublinCoreSearchResults made query: " + "TO DO");
		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		SearchResultsDC resultsDC = null;
		try {
			resultsDC = itemdao.getDCResults(queryParams);
		} catch (JAXBException je) {
			je.printStackTrace();
			log.error(je.getMessage());
			throw new LibraryCloudException("Internal Server Error:" + je.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
		} 
		return resultsDC;
	}

	// because of problems rendering json with moxy, xml and json now divided into separate methods
	//this one for json
	@GET @Path("items.dc")
    @JSONP(queryParam = "callback")
	@Produces ({MediaType.APPLICATION_JSON + ";qs=0.9", "application/javascript;qs=0.9"})
	public String getDublinCoreSearchResultsJson(@Context UriInfo ui) {
		log.info("getDublinCoreSearchResultsJson made query: " + "TO DO");
		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		SearchResultsDC resultsDC = null;
		String resultsString = null;
		try {
			resultsDC = itemdao.getDCResults(queryParams);
			String rawResultsString = itemdao.marshallObject(resultsDC);
			// need to fix pagination so they get serialized as numbers
			resultsString = itemdao.fixPaginationAndFacets(rawResultsString);
		} catch (JAXBException je) {
			je.printStackTrace();
			log.error(je.getMessage());
			throw new LibraryCloudException("Internal Server Error:" + je.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
		} 
		return itemdao.getJsonFromXml(resultsString);
	}
	
}
