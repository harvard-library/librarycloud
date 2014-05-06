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

import gov.loc.mods.v3.ModsType;

/**
*
* ItemResource is the main entry point for item queries and individual item requests;
* It uses the jersey implementation (see web.xml) of jax-rs api for RESTful web services.
* Requests of /v2/items/{id} are for an individual item;
* Requests of /v2/items?<search parameters> are for searching the api;
* Default format is XML; dot notation (items.*) are for variant formats (json, dc and html);
* 
* @author Michael Vandermillen
*
*/

@Path ("/v2")
public class ItemResource {
	ItemDAO itemdao = new ItemDAO();
	
	@GET @Path("items/{id}")
	@Produces (MediaType.APPLICATION_XML)
	public ModsType getItem(@PathParam("id") String id) {
		ModsType modsType = null; 
		try {
			modsType = itemdao.getMods(id);
		} catch (JAXBException je) {
			//TO-DO better error handling
			System.out.print(je);
		}
		return modsType;
	}

	@GET @Path("items/{id}.json")
	@Produces ("application/json")
	public String getJsonItem(@PathParam("id") String id) {
		ModsType modsType = null; 
		String modsString = null;
		try {
			modsType = itemdao.getMods(id);
			modsString = itemdao.writeJson(modsType);
		} catch (JAXBException je) {
			//TO-DO better error handling
			System.out.print(je);
		}

		return modsString;
	}

	@GET @Path("items/{id}.dc")
	@Produces (MediaType.APPLICATION_XML)
	public ModsType getDublinCoreItem(@PathParam("id") String id) {
		ModsType modsType = null; 
		try {
			modsType = itemdao.getMods(id);
		} catch (JAXBException je) {
			//TO-DO better error handling
			System.out.print(je);
		}

		return modsType;
	}
	
	@GET @Path("items/{id}.html")
	@Produces (MediaType.APPLICATION_XML)
	public ModsType getHtmlItem(@PathParam("id") String id) {
		ModsType modsType = null; 
		try {
			modsType = itemdao.getMods(id);
		} catch (JAXBException je) {
			//TO-DO better error handling
			System.out.print(je);
		}

		return modsType;
	}	
	
	@GET @Path("items")
	@Produces (MediaType.APPLICATION_XML)
	public SearchResults getSearchResults(@Context UriInfo ui) {
	    MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
	    //we don't currently need to use the pathParam
	    //MultivaluedMap<String, String> pathParams = ui.getPathParameters();

		SearchResults results = null;
		
		try {
			results = itemdao.getResults(queryParams);	
		} catch (JAXBException je) {
			//TO-DO better error handling
			System.out.println(je);
		}
		return results;
	}

	@GET @Path("items.json")
	@Produces (MediaType.APPLICATION_JSON)
	public String getJsonSearchResults(@Context UriInfo ui) {
	    MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		String jsonString = null;
		try {
			SearchResults results = itemdao.getResults(queryParams);	
			jsonString = itemdao.writeJson(results);
		} catch (JAXBException je) {
			//TO-DO better error handling
			System.out.println(je);
		}	

		return jsonString;
	}
	
	@GET @Path("items.dc")
	@Produces (MediaType.APPLICATION_XML)
	public SearchResults getDublinCOreSearchResults(@Context UriInfo ui) {
	    MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		SearchResults results = null;
		
		try {
			results = itemdao.getResults(queryParams);	
		} catch (JAXBException je) {
			//TO-DO better error handling
			System.out.println(je);
		}
		return results;
	}
	
	@GET @Path("items.html")
	@Produces (MediaType.APPLICATION_XML)
	public SearchResults getHtmlSearchResults(@Context UriInfo ui) {
	    MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		SearchResults results = null;
		
		try {
			results = itemdao.getResults(queryParams);	
		} catch (JAXBException je) {
			//TO-DO better error handling
			System.out.println(je);
		}
		return results;
	}
	
}
