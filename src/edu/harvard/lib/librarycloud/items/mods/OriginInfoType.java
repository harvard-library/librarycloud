//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-661 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.02.02 at 03:34:01 PM MST 
//


package edu.harvard.lib.librarycloud.items.mods;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for originInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="originInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="place" type="{http://www.loc.gov/mods/v3}placeType"/>
 *         &lt;element name="publisher" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dateIssued" type="{http://www.loc.gov/mods/v3}dateType"/>
 *         &lt;element name="dateCreated" type="{http://www.loc.gov/mods/v3}dateType"/>
 *         &lt;element name="dateCaptured" type="{http://www.loc.gov/mods/v3}dateType"/>
 *         &lt;element name="dateValid" type="{http://www.loc.gov/mods/v3}dateType"/>
 *         &lt;element name="dateModified" type="{http://www.loc.gov/mods/v3}dateType"/>
 *         &lt;element name="copyrightDate" type="{http://www.loc.gov/mods/v3}dateType"/>
 *         &lt;element name="dateOther" type="{http://www.loc.gov/mods/v3}dateOtherType"/>
 *         &lt;element name="edition" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="issuance">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="continuing"/>
 *               &lt;enumeration value="monographic"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="frequency" type="{http://www.loc.gov/mods/v3}stringPlusAuthority"/>
 *       &lt;/choice>
 *       &lt;attGroup ref="{http://www.loc.gov/mods/v3}language"/>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "originInfoType", propOrder = {
    "placeOrPublisherOrDateIssued"
})
public class OriginInfoType {
	
    @XmlElementRefs({
        @XmlElementRef(name = "frequency", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class),
        @XmlElementRef(name = "copyrightDate", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class),
        @XmlElementRef(name = "issuance", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class),
        @XmlElementRef(name = "dateIssued", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class),
        @XmlElementRef(name = "dateModified", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class),
        @XmlElementRef(name = "dateCreated", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class),
        @XmlElementRef(name = "dateOther", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class),
        @XmlElementRef(name = "edition", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class),
        @XmlElementRef(name = "dateValid", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class),
        @XmlElementRef(name = "publisher", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class),
        @XmlElementRef(name = "dateCaptured", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class),
        @XmlElementRef(name = "place", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class)
    })
    

    protected List<JAXBElement<?>> placeOrPublisherOrDateIssued;
    @XmlAttribute(name = "lang")
    protected String mLang;
    @XmlAttribute(namespace = "http://www.w3.org/XML/1998/namespace")
    protected String lang;
    @XmlAttribute
    protected String script;
    @XmlAttribute
    protected String altRepGroup;
    @XmlAttribute
    protected String transliteration;
    @XmlAttribute
    protected String displayLabel;

    /**
     * Gets the value of the placeOrPublisherOrDateIssued property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the placeOrPublisherOrDateIssued property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlaceOrPublisherOrDateIssued().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link StringPlusAuthority }{@code >}
     * {@link JAXBElement }{@code <}{@link DateType }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link DateType }{@code >}
     * {@link JAXBElement }{@code <}{@link DateType }{@code >}
     * {@link JAXBElement }{@code <}{@link DateType }{@code >}
     * {@link JAXBElement }{@code <}{@link DateOtherType }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link DateType }{@code >}
     * {@link JAXBElement }{@code <}{@link DateType }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link PlaceType }{@code >}
     * 
     * 
     */
    
    public List<JAXBElement<?>> getPlaceOrPublisherOrDateIssued() {
        if (placeOrPublisherOrDateIssued == null) {
            placeOrPublisherOrDateIssued = new ArrayList<JAXBElement<?>>();
        }
        return this.placeOrPublisherOrDateIssued;
    }

    /**
     * Gets the value of the mLang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMLang() {
        return mLang;
    }

    /**
     * Sets the value of the mLang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMLang(String value) {
        this.mLang = value;
    }

    /**
     * Gets the value of the lang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLang(String value) {
        this.lang = value;
    }

    /**
     * Gets the value of the script property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScript() {
        return script;
    }

    /**
     * Sets the value of the script property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScript(String value) {
        this.script = value;
    }

    /**
     * Gets the value of the transliteration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransliteration() {
        return transliteration;
    }

    /**
     * Sets the value of the transliteration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransliteration(String value) {
        this.transliteration = value;
    }

    /**
     * Gets the value of the displayLabel property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getDisplayLabel() {
        return displayLabel;
    }

    /**
     * Sets the value of the displayLabel property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setDisplayLabel(String value) {
        this.displayLabel = value;
    }


}
