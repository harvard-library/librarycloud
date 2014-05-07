/**
*
* package-info is the reserved name for the jaxb class used to set package-level information for
* jaxb classes; here it is used to set namespaces for our "item" api, as well as for mods and xlink
* (otherwise jaxb generates ns1, ns2, etc)
* 
* @author Michael Vandermillen
*
*/

@javax.xml.bind.annotation.XmlSchema(namespace = "http://api.lib.harvard.edu/v2/item",  
    xmlns = {   
		@javax.xml.bind.annotation.XmlNs(namespaceURI = "http://www.loc.gov/mods/v3", prefix = "mods"),  
		@javax.xml.bind.annotation.XmlNs(namespaceURI = "http://www.w3.org/1999/xlink", prefix = "xlink"),
		@javax.xml.bind.annotation.XmlNs(namespaceURI = "http://api.lib.harvard.edu/v2/item", prefix = "")  
    },  
    elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED) 
package edu.harvard.lib.lcloud;