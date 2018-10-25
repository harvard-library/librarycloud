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
		@javax.xml.bind.annotation.XmlNs(namespaceURI = "http://www.openarchives.org/OAI/2.0/oai_dc/", prefix = "oai_dc"),  
		@javax.xml.bind.annotation.XmlNs(namespaceURI = "http://purl.org/dc/elements/1.1/", prefix = "dc"),
		@javax.xml.bind.annotation.XmlNs(namespaceURI = "http://www.loc.gov/MARC21/slim", prefix = "marc"),
		@javax.xml.bind.annotation.XmlNs(namespaceURI = "http://www.w3.org/1999/xlink", prefix = "xlink"),
		@javax.xml.bind.annotation.XmlNs(namespaceURI = "http://api.lib.harvard.edu/v2/item", prefix = ""),
		@javax.xml.bind.annotation.XmlNs(namespaceURI = "http://hul.harvard.edu/ois/xml/ns/urlinfo", prefix = "urlinfo"),
		@javax.xml.bind.annotation.XmlNs(namespaceURI = "http://hul.harvard.edu/ois/xml/ns/originalDocument", prefix = "originalDocument"),
		@javax.xml.bind.annotation.XmlNs(namespaceURI = "http://hul.harvard.edu/ois/xml/ns/HarvardRepositories", prefix = "HarvardRepositories"),
		@javax.xml.bind.annotation.XmlNs(namespaceURI = "http://hul.harvard.edu/ois/xml/ns/priorrecordids", prefix = "priorrecordids"),
		@javax.xml.bind.annotation.XmlNs(namespaceURI = "http://hul.harvard.edu/ois/xml/ns/HarvardDRS", prefix = "HarvardDRS"),
		@javax.xml.bind.annotation.XmlNs(namespaceURI = "http://hul.harvard.edu/ois/xml/ns/processingDate", prefix = "processingDate"),
		@javax.xml.bind.annotation.XmlNs(namespaceURI = "http://hul.harvard.edu/ois/xml/ns/availableTo", prefix = "availableTo"),
		@javax.xml.bind.annotation.XmlNs(namespaceURI = "http://hul.harvard.edu/ois/xml/ns/digitalFormats", prefix = "digitalFormats")
    },
    elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED) 
package edu.harvard.lib.librarycloud.items;
