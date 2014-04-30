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
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
	 
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;

import gov.loc.mods.v3.ModsType;

/**
*
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
			//TO DO intelligent error handling
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
			System.out.print(je);
		}

		return modsType;
	}	
	
	@GET @Path("items")
	@Produces (MediaType.APPLICATION_XML)
	public SearchResults getSearchResults(
		@QueryParam("q") String q,
		@QueryParam("title") String title,
		@QueryParam("name") String name,
		@QueryParam("start") String start,
		@QueryParam("rows") String rows,
		@QueryParam("sort") String sort
		) {
		SearchResults results = null;
		
		try {
			results = itemdao.getResults(q,title,name,start,rows,sort);	
		} catch (JAXBException je) {
			System.out.println(je);
		}
		return results;
	}

	@GET @Path("items.json")
	@Produces (MediaType.APPLICATION_JSON)
	public String getJsonSearchResults(
		@QueryParam("q") String q,
		@QueryParam("title") String title,
		@QueryParam("name") String name,
		@QueryParam("start") String start,
		@QueryParam("rows") String rows,
		@QueryParam("sort") String sort
		) {
		String jsonString = null;
		try {
			SearchResults results = itemdao.getResults(q,title,name,start,rows,sort);	
			jsonString = itemdao.writeJson(results);
		} catch (JAXBException je) {
			//TO DO - intelligent error handling
			System.out.println(je);
		}	

		return jsonString;
	}
	
	@GET @Path("items.dc")
	@Produces (MediaType.APPLICATION_XML)
	public SearchResults getDublinCOreSearchResults(
		@QueryParam("q") String q,
		@QueryParam("title") String title,
		@QueryParam("name") String name,
		@QueryParam("start") String start,
		@QueryParam("rows") String rows,
		@QueryParam("sort") String sort
		) {
		SearchResults results = null;
		
		try {
			results = itemdao.getResults(q,title,name,start,rows,sort);	
		} catch (JAXBException je) {
			System.out.println(je);
		}		

		return results;
	}
	
	@GET @Path("items.html")
	@Produces (MediaType.APPLICATION_XML)
	public SearchResults getHtmlSearchResults(
		@QueryParam("q") String q,
		@QueryParam("title") String title,
		@QueryParam("name") String name,
		@QueryParam("start") String start,
		@QueryParam("rows") String rows,
		@QueryParam("sort") String sort
		) {
		SearchResults results = null;
		
		try {
			results = itemdao.getResults(q,title,name,start,rows,sort);	
		} catch (JAXBException je) {
			System.out.println(je);
		}		

		return results;
	}
/*
	@GET @Path("items/{id}/mock")
	@Produces (MediaType.APPLICATION_JSON)
	public Pagination getMockItem() {
		TestRecords testrecs = new TestRecords();
		return testrecs.getMockPagination();
	}

	@GET @Path("items/{id}/mock")
	@Produces (MediaType.APPLICATION_XML)
	public ModsType getMods(@PathParam("id") String id) {
		ModsType modstype = new ModsType();
		TestRecords testrecs = new TestRecords();
		try {
			modstype = testrecs.getModsType();
			
		} catch (JAXBException je) {
			System.out.print(je);
		}
		return modstype;
	}
*/
	/*
	@GET @Path("/items/mock")
	@Produces (MediaType.APPLICATION_XML)
	public SearchResults getMockSearchResults(@QueryParam("q") String q) {
		SearchResults results = new SearchResults();
		TestRecords testrecs = new TestRecords();
		List<Item> items = testrecs.getMockItems();
		Pagination pagination = testrecs.getMockPagination();
		results.setItems(items);
		results.setPagination(pagination);

		return results;
	}
*/
	
}
