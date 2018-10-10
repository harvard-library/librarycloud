//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-661 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2009.02.02 at 03:34:01 PM MST 
//


package edu.harvard.lib.librarycloud.items.mods;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for roleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="roleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence maxOccurs="unbounded">
 *         &lt;element name="roleTerm">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.loc.gov/mods/v3>stringPlusAuthority">
 *                 &lt;attribute name="type" type="{http://www.loc.gov/mods/v3}codeOrText" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "roleType", propOrder = {
    "roleTerm"
})
public class RoleType {

    @XmlElement(required = true)
    protected List<RoleType.RoleTerm> roleTerm;

    /**
     * Gets the value of the roleTerm property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roleTerm property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoleTerm().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RoleType.RoleTerm }
     * 
     * 
     */
    public List<RoleType.RoleTerm> getRoleTerm() {
        if (roleTerm == null) {
            roleTerm = new ArrayList<RoleType.RoleTerm>();
        }
        return this.roleTerm;
    }


    /**
     * if it is a code: 100, 110, 111, 700, 710, 711 $4.
     * 					If it is text:100, 110, 700, 710 $e.		
     * 					
     * 
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.loc.gov/mods/v3>stringPlusAuthority">
     *       &lt;attribute name="type" type="{http://www.loc.gov/mods/v3}codeOrText" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class RoleTerm
        extends StringPlusAuthority
    {

        @XmlAttribute
        protected CodeOrText type;

        /**
         * Gets the value of the type property.
         * 
         * @return
         *     possible object is
         *     {@link CodeOrText }
         *     
         */
        public CodeOrText getType() {
            return type;
        }

        /**
         * Sets the value of the type property.
         * 
         * @param value
         *     allowed object is
         *     {@link CodeOrText }
         *     
         */
        public void setType(CodeOrText value) {
            this.type = value;
        }

    }

    //harvard - 2018-10-10: update to mods 3.6
    @XmlAttribute
    protected String valueURI;

    /**
     * Gets the value of the valueURI property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getValueURI() {
        return valueURI;
    }

    /**
     * Sets the value of the valueURI property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setValueURI(String value) {
        this.valueURI = value;
    }

}
