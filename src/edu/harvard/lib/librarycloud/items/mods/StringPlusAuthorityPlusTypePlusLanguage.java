//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-661 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.02.02 at 03:34:01 PM MST 
//


package edu.harvard.lib.librarycloud.items.mods;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for stringPlusAuthorityPlusTypePlusLanguage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="stringPlusAuthorityPlusTypePlusLanguage">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.loc.gov/mods/v3>stringPlusAuthorityPlusLanguage">
 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "stringPlusAuthorityPlusTypePlusLanguage")
public class StringPlusAuthorityPlusTypePlusLanguage
    extends StringPlusAuthorityPlusLanguage
{

    @XmlAttribute(name = "type")
    protected String spaType;

    /**
     * Gets the value of the spaType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpaType() {
        return spaType;
    }

    /**
     * Sets the value of the spaType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpaType(String value) {
        this.spaType = value;
    }

}
