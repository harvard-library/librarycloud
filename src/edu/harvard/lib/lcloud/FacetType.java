package edu.harvard.lib.lcloud;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;





import java.util.ArrayList;
import java.util.List;

/**
*
* FacetType is the java object representation of one entry within the solr facet_fields list.
* A list of 1 or more FacetType is contained within the parent Facet class. 
* 
* @author Michael Vandermillen
*
*/

@XmlRootElement()
public class FacetType {

	private String facetName;
	private List<FacetTerm> facetTerms;
	
	public FacetType() {
	
	}
	
	@XmlElement()
    public String getFacetName() {  
        return facetName;  
    }  
  
    public void setFacetName(String facetName) {  
        this.facetName = facetName;  
    } 	

    @XmlElement(name = "facet") 
    public List<FacetTerm> getFacetTerms() {  
        return facetTerms;  
    }  
  
    public void setFacetTerms( List<FacetTerm> facetTerms) {  
        this.facetTerms = facetTerms;  
    }  
    
}