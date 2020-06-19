<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:mods="http://www.loc.gov/mods/v3"
    xmlns:lc="http://api.lib.harvard.edu/v2/item"
    xmlns:HarvardDRS="http://hul.harvard.edu/ois/xml/ns/HarvardDRS"
    xmlns:sets="http://hul.harvard.edu/ois/xml/ns/sets"
    xmlns:cdwalite="http://www.getty.edu/research/conducting_research/standards/cdwa/cdwalite"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs" version="2.0">
    
    <xsl:output method="text" encoding="utf-8" />
    
    <xsl:param name="delim" select="','" />
    <xsl:param name="quote" select="'&quot;'" />
    <xsl:param name="break" select="'&#xA;'" />
    
    <xsl:template match="lc:results">
        <xsl:apply-templates select="lc:pagination"/>
        <xsl:apply-templates select="lc:items"/>
    </xsl:template>
    
    <xsl:template match="lc:items">
        <xsl:text>"Title","Creator/Contributor","Date","Language","Description","Subjects","Genre","Repository","urn"&#xA;</xsl:text>
        <xsl:apply-templates select="mods:mods"/>
    </xsl:template>
    
    <xsl:template match="lc:pagination"/>
    
    <xsl:template match="mods:mods">
        <xsl:variable name="subjects">
            <xsl:apply-templates select="mods:subject"/>
        </xsl:variable>
        <xsl:variable name="genres">
            <xsl:apply-templates select="mods:genre"/>
        </xsl:variable>
        <xsl:text>"</xsl:text>
        <xsl:apply-templates select="mods:titleInfo[not(@type)]"/>
        <xsl:apply-templates select="mods:relatedItem[@type='constituent']/mods:titleInfo/mods:title" mode="relatedtitle"/>
        <xsl:apply-templates select="mods:titleInfo[@type]"/>
        <xsl:text>",</xsl:text>
        <xsl:text>"</xsl:text><xsl:apply-templates select="mods:name[mods:role/mods:roleTerm='creator']"/><xsl:text>",</xsl:text>
        <xsl:text>"</xsl:text><xsl:apply-templates select="mods:originInfo"/><xsl:text>",</xsl:text>
        <xsl:text>"</xsl:text><xsl:apply-templates select="mods:language/mods:languageTerm[@type='text']"/><xsl:text>",</xsl:text>
        <xsl:text>"</xsl:text><xsl:apply-templates select="mods:physicalDescription"/><xsl:apply-templates select="mods:extension/cdwalite:indexingMaterialsTechSet"/><xsl:text>",</xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="substring($subjects,3)"/><xsl:text>",</xsl:text>
        <xsl:text>"</xsl:text><xsl:value-of select="substring($genres,3)"/><xsl:text>",</xsl:text>
        <!--<xsl:text>"</xsl:text><xsl:apply-templates select="mods:note"/><xsl:text>",</xsl:text>-->
        <xsl:text>"</xsl:text><xsl:apply-templates select="mods:location/mods:physicalLocation[@type='repository']"/><xsl:text>",</xsl:text>
        <xsl:text>"</xsl:text><xsl:apply-templates select="mods:extension/HarvardDRS:DRSMetadata"/><xsl:text>"</xsl:text>
        <xsl:text>&#xA;</xsl:text>
    </xsl:template>
    
    <xsl:template match="mods:extension/HarvardDRS:DRSMetadata">
        <xsl:value-of select="HarvardDRS:fileDeliveryURL"/>
    </xsl:template>

    <xsl:template match="mods:physicalLocation">
        <xsl:value-of select="normalize-space(.)"/>
    </xsl:template>

    <xsl:template match="mods:subject">
        <xsl:apply-templates select="mods:topic"/>
        <xsl:apply-templates select="mods:geographic"/>
        <xsl:apply-templates select="mods:temporal"/>
    </xsl:template>
    
    <xsl:template match="mods:topic|mods:geographic|mods:temporal|mods:genre">
        <xsl:text>, </xsl:text><xsl:value-of select="normalize-space(.)"/>
    </xsl:template>
    
    <xsl:template match="cdwalite:indexingMaterialsTechSet">
        <xsl:text>, </xsl:text><xsl:value-of select="normalize-space(cdwalite:termMaterialsTech)"/>
    </xsl:template>
    
    <xsl:template match="mods:physicalDescription">
        <xsl:apply-templates select="normalize-space(mods:extent)"/>
    </xsl:template>
    
    <xsl:template match="mods:extent">
        <xsl:value-of select="."/>
    </xsl:template>
    
    <xsl:template match="mods:languageTerm">
        <xsl:value-of select="normalize-space(.)"/>
    </xsl:template>
    
    <xsl:template match="mods:originInfo">
        <xsl:variable name="originDates">
            <xsl:apply-templates select="mods:dateCreated[not(@point)]"/>
            <xsl:apply-templates select="mods:dateIssued[not(@point)]"/>
            <xsl:apply-templates select="mods:dateCaptured[not(@point)]"/>
            <xsl:apply-templates select="mods:copyrightDate[not(@point)]"/>
            <xsl:apply-templates select="mods:dateOther[not(@point)]"/>
        </xsl:variable>
        <xsl:value-of select="substring($originDates,3)"/>
    </xsl:template>
    
    <xsl:template match="mods:dateCreated|mods:dateIssued|mods:dateCaptured|mods:copyrightDate|mods:dateOther">
        <xsl:text>, </xsl:text><xsl:value-of select="."/>
    </xsl:template>
    
    <xsl:template match="mods:name">
        <xsl:apply-templates select="mods:namePart[not(@type)]"/>
        <xsl:apply-templates select="mods:namePart[@type]"/>
        <xsl:apply-templates select="mods:role[not(mods:roleTerm='creator')]"/>
    </xsl:template>
    
    <xsl:template match="mods:namePart">
        <xsl:if test="@type">
            <xsl:text>, </xsl:text>
        </xsl:if>
        <xsl:value-of select="."/>
    </xsl:template>
    <xsl:template match="mods:role">
        <xsl:text>, </xsl:text><xsl:value-of select="mods:roleTerm"/>
    </xsl:template>
    
    <xsl:template match="mods:titleInfo">
        <xsl:if test="@type"><xsl:text>. </xsl:text></xsl:if>
        <xsl:variable name="concatedTitle"><xsl:apply-templates/></xsl:variable>
        <xsl:value-of select="normalize-space($concatedTitle)"/>
    </xsl:template>   

    <xsl:template match="mods:nonSort|mods:title|modssubtitle|mods:partNumber|mods:partName">
        <xsl:value-of select="normalize-space(.)"/><xsl:text> </xsl:text>
    </xsl:template>

    <xsl:template match="mods:title" mode="relatedtitle">
        <xsl:text>. </xsl:text><xsl:value-of select="."/>
    </xsl:template>

</xsl:stylesheet>