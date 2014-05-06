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

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;

/**
*
* Facet is the java object representation of solr facet_counts list; it contains a list of
* one or more FacetType objects
* 
* @author Michael Vandermillen
*
*/

@XmlRootElement()
public class Facet {
	
	private List<FacetType> facetTypes;
	
	public Facet() {
	
	}

    @XmlElement(name="facetField")  
    public List<FacetType> getFacetTypes() {  
        return facetTypes;  
    }  
  
    public void setFacetTypes( List<FacetType> facetTypes) {  
        this.facetTypes = facetTypes;  
    }     
    
}
