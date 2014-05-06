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

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.ArrayList;
import java.util.List;

/**
*
* SolrItem is a legacy class moved from Item; it is currently unused, but would be the means to map
* individual solr fields (below is just a subset of all fields in solr); instead we use the embedded
* originalMods field and parse those fields for display
*
* @author Michael Vandermillen
*
*/

@XmlRootElement()
//@XmlType(propOrder={"original","title","name","role","resourceType","genre","originPlace","publisher","edition","originDate","dateCreated","dateCaptured","dateIssued","copyrightDate","languageCode","physicalDescription","abstractTOC","subjectPlace","classification","identifier","recordIdentifier","source"})
public class SolrItem {

	private String mods = "";
	private ArrayList abstractTOC;
	private ArrayList classification;
	private ArrayList copyrightDate;
	private ArrayList dateCaptured;
	private ArrayList dateCreated;
	private ArrayList dateIssued;
	private String edition;
	private String genre;
	private ArrayList identifier;
	private ArrayList languageCode;
	private ArrayList name;
	private String originDate;
	private ArrayList originPlace;
	private String physicalDescription;
	private String publisher;
	private String recordIdentifier;
	private String resourceType;
	private ArrayList role;
	private String source;
	private ArrayList subjectPlace;
	private String title;
	
	
	public SolrItem () {
		
	}
	
    @XmlElement(name="original")
	public String getMods() {
		return mods;
	}

    @XmlElement  
	public ArrayList getAbstractTOC() {
		return abstractTOC;
	}
    @XmlElement  
	public ArrayList getClassification() {
		return classification;
	}
    @XmlElement  
	public ArrayList getCopyrightDate() {
		return copyrightDate;
	}
    @XmlElement  
	public ArrayList getDateCaptured() {
		return dateCaptured;
	}
    @XmlElement  
	public ArrayList getDateCreated() {
		return dateCreated;
	}
    @XmlElement  
	public ArrayList getDateIssued() {
		return dateIssued;
	}
    @XmlElement  
	public String getEdition() {
		return edition;
	}
    @XmlElement  
	public String getGenre() {
		return genre;
	}
    @XmlElement  
	public ArrayList getIdentifier() {
		return identifier;
	}
    @XmlElement  
	public ArrayList getLanguageCode() {
		return languageCode;
	}
    @XmlElement  
	public ArrayList getName() {
		return name;
	}
    @XmlElement  
	public String getOriginDate() {
		return originDate;
	}
    @XmlElement  
	public ArrayList getOriginPlace() {
		return originPlace;
	}
    @XmlElement  
	public String getPhysicalDescription() {
		return physicalDescription;
	}
    @XmlElement  
	public String getPublisher() {
		return publisher;
	}
    @XmlElement  
	public String getRecordIdentifier() {
		return recordIdentifier;
	}
 
    @XmlElement  
	public ArrayList getRole() {
		return role;
	}
 
    @XmlElement  
	public String getSource() {
		return source;
	}
 
    @XmlElement  
	public String getTitle() {
		return title;
	}
 
    
	public void setMods(String mods) {
    	//System.out.println("MODS - 21: " + mods.substring(21));
    	//mods = mods.substring(21);
		//mods = mods.replace("<?xml version=\"1.0\"?>","");
		//mods = StringEscapeUtils.unescapeXml(mods);
		//mods = mods.replaceAll("&","&amp;");
		//mods = mods.replaceAll("&amp;#","&#");
		this.mods = mods;
	}

    
    
    
	public void setAbstractTOC(ArrayList abstractTOC) {
		this.abstractTOC = abstractTOC;
	}

	public void setClassification(ArrayList classification) {
		this.classification = classification;
	}

	public void setCopyrightDate(ArrayList copyrightDate) {
		this.copyrightDate = copyrightDate;
	}

	public void setDateCaptured(ArrayList dateCaptured) {
		this.dateCaptured = dateCaptured;
	}

	public void setDateCreated(ArrayList dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setDateIssued(ArrayList dateissued) {
		this.dateIssued = dateIssued;
	}

	public void setEdition(String edition) {
		this.edition = edition;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setIdentifier(ArrayList identifier) {
		this.identifier = identifier;
	}

	public void setLanguageCode(ArrayList languageCode) {
		this.languageCode = languageCode;
	}

	public void setName(ArrayList name) {
		this.name = name;
	}

	public void setOriginDate(String originDate) {
		this.originDate = originDate;
	}

	public void setOriginPlace(ArrayList originPlace) {
		this.originPlace = originPlace;
	}

	public void setPhysicalDescription(String physicalDescription) {
		this.physicalDescription = physicalDescription;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setRecordIdentifier(String recordIdentifier) {
		this.recordIdentifier = recordIdentifier;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public void setRole(ArrayList role) {
		this.role = role;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setSubjectPlace(ArrayList subjectPlace) {
		this.subjectPlace = subjectPlace;
	}

	
	public void setTitle(String title) {
		this.title = title;
	}

	protected static void marshalingExample(Item item) throws JAXBException
	{
	    JAXBContext jaxbContext = JAXBContext.newInstance(Item.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	     
	    jaxbMarshaller.marshal(item, System.out);

	}
	
 }
