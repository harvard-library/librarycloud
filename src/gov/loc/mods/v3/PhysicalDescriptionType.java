//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-661 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.02.02 at 03:34:01 PM MST 
//


package gov.loc.mods.v3;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for physicalDescriptionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="physicalDescriptionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="form" type="{http://www.loc.gov/mods/v3}stringPlusAuthorityPlusType"/>
 *         &lt;element name="reformattingQuality">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="access"/>
 *               &lt;enumeration value="preservation"/>
 *               &lt;enumeration value="replacement"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="internetMediaType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="extent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="digitalOrigin">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="born digital"/>
 *               &lt;enumeration value="reformatted digital"/>
 *               &lt;enumeration value="digitized microfilm"/>
 *               &lt;enumeration value="digitized other analog"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="note" type="{http://www.loc.gov/mods/v3}noteType"/>
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
@XmlType(name = "physicalDescriptionType", propOrder = {
    "formOrReformattingQualityOrInternetMediaType"
})
public class PhysicalDescriptionType {

    @XmlElementRefs({
        @XmlElementRef(name = "note", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class),
        @XmlElementRef(name = "reformattingQuality", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class),
        @XmlElementRef(name = "internetMediaType", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class),
        @XmlElementRef(name = "extent", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class),
        @XmlElementRef(name = "form", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class),
        @XmlElementRef(name = "digitalOrigin", namespace = "http://www.loc.gov/mods/v3", type = JAXBElement.class)
    })
    protected List<JAXBElement<?>> formOrReformattingQualityOrInternetMediaType;
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

    /**
     * Gets the value of the formOrReformattingQualityOrInternetMediaType property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the formOrReformattingQualityOrInternetMediaType property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFormOrReformattingQualityOrInternetMediaType().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link NoteType }{@code >}
     * {@link JAXBElement }{@code <}{@link StringPlusAuthorityPlusType }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getFormOrReformattingQualityOrInternetMediaType() {
        if (formOrReformattingQualityOrInternetMediaType == null) {
            formOrReformattingQualityOrInternetMediaType = new ArrayList<JAXBElement<?>>();
        }
        return this.formOrReformattingQualityOrInternetMediaType;
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

}
