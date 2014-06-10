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
* SearchResults is the java object representation of results returned from solr; it contains
* pagination, item (wrapping a mods object) and optional facet section
*
* @author Michael Vandermillen
*
*/

@XmlRootElement(name="results")
@XmlType(propOrder={"pagination", "itemGroup","facet"})
public class SearchResults {
	
	public SearchResults () {
		
	}
	
	private Pagination pagination;

	//private List<ModsType> modsTypes;
	private ItemGroup itemGroup;
	private Facet facet;
	
	@XmlElement(name = "pagination")
	public Pagination getPagination() {
		return pagination;
	}
	
	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}
	
	@XmlElement(name = "items")
	public ItemGroup getItemGroup() {
		return itemGroup;
	}
	
	public void setItemGroup(ItemGroup itemGroup) {
		this.itemGroup = itemGroup;
	}

	@XmlElement(name = "facets")
	public Facet getFacet() {
		return facet;
	}
	
	public void setFacet(Facet facet) {
		this.facet = facet;
	}


	/*
	//the old way was to add ModsType objects directly, now they get wrapped as a list in Item
	//hold on for now in case things change for some reason
	@XmlElement(name = "mods", namespace = "http://www.loc.gov/mods/v3")
	public List<ModsType> getModsTypes() {
		
		return modsTypes;
	}
	
	public void setModsTypes(List<ModsType> modsTypes) {
		this.modsTypes = modsTypes;
	}
	*/
}

