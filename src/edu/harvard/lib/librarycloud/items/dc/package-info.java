/**
*
* package-info is the reserved name for the jaxb class used to set package-level information for
* jaxb classes; here it is used to set namespaces for our "item" api, as well as for mods and xlink
* (otherwise jaxb generates ns1, ns2, etc)
* 
* @author Michael Vandermillen
*
*/

@javax.xml.bind.annotation.XmlSchema(namespace = "http://www.openarchives.org/OAI/2.0/oai_dc/", 
xmlns = {   
	@javax.xml.bind.annotation.XmlNs(namespaceURI = "http://purl.org/dc/elements/1.1/", prefix = "dc"),  
	@javax.xml.bind.annotation.XmlNs(namespaceURI = "http://www.openarchives.org/OAI/2.0/oai_dc/", prefix = "oai_dc")
}, 
elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED)
package edu.harvard.lib.librarycloud.items.dc;
