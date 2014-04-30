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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

//import com.sun.xml.internal.txw2.annotation.XmlElement;


import org.eclipse.persistence.jaxb.MarshallerProperties;

import java.util.ArrayList;
import java.util.List;
/**
*
*
* @author Michael Vandermillen
*
*/

@XmlRootElement(name="results")
//@XmlType(propOrder={"pagination", "items"})
@XmlType(propOrder={"pagination", "items"})
public class SearchResults {
	
	public SearchResults () {
		
	}
	
	private Pagination pagination;

	//private List<ModsType> modsTypes;
	private List<Item> items;
	
	@XmlElement(name = "pagination")
	public Pagination getPagination() {
		return pagination;
	}
	
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	/*
	@XmlElement(name = "mods", namespace = "http://www.loc.gov/mods/v3")
	public List<ModsType> getModsTypes() {
		
		return modsTypes;
	}
	
	public void setModsTypes(List<ModsType> modsTypes) {
		this.modsTypes = modsTypes;
	}
	*/
	
	@XmlElement(name = "item")
	public List<Item> getItems() {
		return items;
	}
	
	public void setItems(List<Item> items) {
		this.items = items;
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

