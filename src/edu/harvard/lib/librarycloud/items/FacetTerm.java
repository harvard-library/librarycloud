package edu.harvard.lib.librarycloud.items;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
*
* FacetTerm is the java object representation of one solr facet name/count pair.
* A list of 1 or more FacetTerms is contained within the parent FacetType class 
* 
* @author Michael Vandermillen
*
*/

@XmlRootElement()
@XmlType(propOrder={"termName","termCount"})
public class FacetTerm {

	private String termName;
	private long termCount;
	
	public FacetTerm() {
		
	}
	
    @XmlElement(name = "term") 
	public String getTermName() {  
        return termName;  
    }  
  
     public void setTermName(String termName) {  
        this.termName = termName;  
    } 
	
     @XmlElement(name = "count") 
     public long getTermCount() {  
        return termCount;  
    }  
  
    public void setTermCount(long termCount) {  
        this.termCount = termCount;  
    } 
            
}
