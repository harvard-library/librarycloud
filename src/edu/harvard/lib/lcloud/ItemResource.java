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

import org.apache.log4j.Logger;
import org.dublincore.Metadata;
import org.glassfish.jersey.server.JSONP;

import gov.loc.mods.v3.ModsType;

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
	
	@GET @Path("items/{id}")
    @JSONP(queryParam = "callback")
	@Produces ({"application/javascript", MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML + ";qs=0.9"})
	public ModsType getItem(@PathParam("id") String id) {
		log.info("getItem called for id: " + id);
		ModsType modsType = null; 
		try {
			modsType = itemdao.getMods(id);
		} catch (JAXBException je) {
			System.out.println(je);
			log.error(je.getMessage());
		}
		return modsType;
	}
	
	
	@GET @Path("items/{id}.dc")
    @JSONP(queryParam = "callback")
	@Produces ({"application/javascript", MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML + ";qs=0.9"})
	public Metadata getDublinCoreItem(@PathParam("id") String id) {
		log.info("getDublinCoreItem called for id: " + id);
		ModsType modsType = null;
		String dcXml = null;
		Metadata metadata = null;
		try {
			modsType = itemdao.getMods(id);
			String modsXml = itemdao.marshallObject(modsType);
			dcXml = itemdao.transform(modsXml, Config.getInstance().DC_XSLT); 
			metadata = itemdao.getDublinCore(dcXml);

		} catch (JAXBException je) {
			je.printStackTrace();
			log.error(je);
		}

		return metadata;
	}


	@GET @Path("items")
    @JSONP(queryParam = "callback")
	@Produces ({"application/javascript", MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML + ";qs=0.9"})
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
		}
		return results;
	}
	
	
	@GET @Path("items.dc")
    @JSONP(queryParam = "callback")
	@Produces ({"application/javascript", MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML + ";qs=0.9"})
	public SearchResultsDC getDublinCoreSearchResults(@Context UriInfo ui) {
		log.info("getDublinCoreSearchResults made query: " + "TO DO");
		MultivaluedMap<String, String> queryParams = ui.getQueryParameters();
		SearchResultsDC resultsDC = null;
		try {
			resultsDC = itemdao.getDCResults(queryParams);
		} catch (JAXBException je) {
			je.printStackTrace();
			log.error(je.getMessage());
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return resultsDC;
	}
	
}
