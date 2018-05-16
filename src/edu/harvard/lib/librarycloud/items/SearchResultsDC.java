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

import edu.harvard.lib.librarycloud.items.dc.Metadata;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

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
public class SearchResultsDC extends SearchResults {
	
	public SearchResultsDC () {
    super();
	}
	
	private Pagination pagination;

	//private List<ModsType> modsTypes;
	//private List<Metadata> metadataList;
	private DublinCoreGroup itemGroup;
	
	/*
	@XmlElement()
	public List<Metadata> getItems() {
		return metadataList;
	}
	
	public void setItems(List<Metadata> metadata) {
		this.metadataList = metadataList;
	}
	*/

	@XmlElement(name = "items")
	public DublinCoreGroup getItemGroup() {
		return itemGroup;
	}
	
	public void setitemGroup(DublinCoreGroup itemGroup) {
		this.itemGroup = itemGroup;
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
