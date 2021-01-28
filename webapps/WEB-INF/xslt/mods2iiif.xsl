<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:mods="http://www.loc.gov/mods/v3"
    xmlns:lc="http://api.lib.harvard.edu/v2/item"
    xmlns:cdwalite="http://www.getty.edu/research/conducting_research/standards/cdwa/cdwalite"
    xmlns:HarvardDRS="http://hul.harvard.edu/ois/xml/ns/HarvardDRS"
    xmlns:sets="http://hul.harvard.edu/ois/xml/ns/sets"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs" version="2.0">

    <xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>

    <xsl:template match="lc:results">
        <xsl:apply-templates/>
    </xsl:template>
    <xsl:template match="lc:items">
        <iiif_bibrecs>
            <xsl:apply-templates/>
        </iiif_bibrecs>
    </xsl:template>

    <xsl:template match="lc:pagination"/>

    <xsl:template match="mods:mods">
        <iiif_bibrec>
            <xsl:apply-templates select="mods:extension[HarvardDRS:DRSMetadata]" mode="mongoid"/>
            <xsl:apply-templates select="mods:titleInfo[not(@type)]"/>
            <xsl:apply-templates select="mods:name[mods:role/mods:roleTerm = 'creator']"/>
            <xsl:apply-templates select="mods:originInfo"/>
            <xsl:apply-templates select="mods:edition"/>
            <xsl:apply-templates select="mods:abstract"/>
            <xsl:apply-templates select="mods:language"/>
            <xsl:apply-templates select=".//mods:form"/>
            <xsl:apply-templates select=".//mods:extent"/>
            <!--<xsl:apply-templates select="//cdwalite:termMaterialsTech"/>-->
            <!--<xsl:apply-templates select="//mods:subject"/>-->
            <!--<xsl:apply-templates select="//mods:physicalLocation[not(@displayLabel='Harvard repository')]"/>-->
            <!--<xsl:apply-templates select="//mods:hierarchicalGeographic"/>-->
            <xsl:apply-templates select=".//mods:coordinates"/>
            <xsl:apply-templates select=".//mods:genre"/>
            <!--<xsl:apply-templates select="//cdwalite:culture"/>-->
            <!--<xsl:apply-templates select="//cdwalite:style"/>-->
            <xsl:apply-templates select="mods:tableOfContents"/>
            <xsl:apply-templates
                select=".//mods:note[not(@type = 'statement of responsibility') and not(@type = 'organization') and not(@type = 'funding')]"/>
            <xsl:apply-templates select=".//mods:note[@type = 'statement of responsibility']"/>
            <xsl:apply-templates select=".//mods:note[@type = 'statement of funding']"/>
            <xsl:apply-templates select=".//mods:accessCondition"/>
            <!--<xsl:apply-templates select="mods:name[not(mods:role/mods:roleTerm='creator')]"/>-->
            <!-- TO DO: role, subject info from spreadsheet -->
            <!--<xsl:apply-templates select="/mods:relatedItem[@type='series']/"/>-->
            <xsl:apply-templates
                select="mods:physicalLocation[@displayLabel = 'Harvard repository']"/>
            <xsl:apply-templates select="mods:extension[HarvardDRS:DRSMetadata]" mode="drsid"/>
        </iiif_bibrec>
    </xsl:template>

    <xsl:template match="mods:titleInfo[not(@type)]">
        <title>
            <xsl:apply-templates select="mods:nonSort" mode="title"/>
            <xsl:apply-templates select="mods:title" mode="title"/>
            <xsl:apply-templates select="mods:subTitle" mode="title"/>
            <xsl:apply-templates select="mods:namePart" mode="title"/>
        </title>
    </xsl:template>

    <xsl:template match="mods:nonSort" mode="title">
        <xsl:value-of select="normalize-space(.)"/>
        <xsl:text> </xsl:text>
    </xsl:template>

    <xsl:template match="mods:title" mode="title">
        <xsl:value-of select="normalize-space(.)"/>
    </xsl:template>

    <xsl:template match="mods:subTitle" mode="title">
        <xsl:text>, </xsl:text>
        <xsl:value-of select="normalize-space(.)"/>
    </xsl:template>

    <xsl:template match="mods:namePart" mode="title">
        <xsl:text>. </xsl:text>
        <xsl:value-of select="normalize-space(.)"/>
    </xsl:template>

    <xsl:template match="mods:name[mods:role/mods:roleTerm = 'creator']">
        <creator>
            <xsl:apply-templates select="mods:namePart" mode="creator"/>
        </creator>
    </xsl:template>

    <xsl:template match="mods:namePart" mode="creator">
        <xsl:if test="position() > 1">
            <xsl:text> </xsl:text>
        </xsl:if>
        <xsl:value-of select="normalize-space(.)"/>
    </xsl:template>

    <xsl:template match="mods:originInfo">
        <xsl:apply-templates select="mods:place"/>
        <xsl:apply-templates select="mods:publisher"/>
        <xsl:apply-templates select="mods:dateCreated[not(@point)]"/>
        <xsl:apply-templates select="mods:dateIssued[not(@point)]"/>
        <xsl:apply-templates select="mods:copyrightDate"/>
    </xsl:template>

    <xsl:template match="mods:place">
        <xsl:apply-templates select="mods:placeTerm[not(@type = 'code')]"/>
    </xsl:template>

    <xsl:template match="mods:placeTerm[not(@type = 'code')]">
        <placeOfOrigin>
            <xsl:value-of select="normalize-space(.)"/>
        </placeOfOrigin>
    </xsl:template>

    <xsl:template match="mods:publisher">
        <publisher>
            <xsl:value-of select="normalize-space(.)"/>
        </publisher>
    </xsl:template>

    <xsl:template match="mods:dateCreated | mods:dateIssued | mods:copyrightDate">
        <date>
            <xsl:value-of select="normalize-space(.)"/>
        </date>
    </xsl:template>

    <xsl:template match="mods:edition">
        <edition>
            <xsl:value-of select="normalize-space(.)"/>
        </edition>
    </xsl:template>

    <xsl:template match="mods:abstract">
        <abstract>
            <xsl:value-of select="normalize-space(.)"/>
        </abstract>
    </xsl:template>

    <xsl:template match="mods:language">
        <xsl:apply-templates select="mods:languageTerm[@type = 'text']"/>
    </xsl:template>

    <xsl:template match="mods:languageTerm[@type = 'text']">
        <language>
            <xsl:value-of select="normalize-space(.)"/>
        </language>
    </xsl:template>

    <xsl:template match="mods:form">
        <description>
            <xsl:value-of select="normalize-space(.)"/>
        </description>
    </xsl:template>


    <xsl:template match="mods:extent">
        <extent>
            <xsl:value-of select="normalize-space(.)"/>
        </extent>
    </xsl:template>

    <xsl:template match="mods:physicalLocation[not(@displayLabel = 'Harvard repository')]"> </xsl:template>

    <xsl:template match="mods:coordinates">
        <coordinates>
            <xsl:value-of select="normalize-space(.)"/>
        </coordinates>
    </xsl:template>

    <xsl:template match="mods:genre">
        <genre>
            <xsl:value-of select="normalize-space(.)"/>
        </genre>
    </xsl:template>

    <xsl:template match="mods:tableOfContents">
        <contents>
            <xsl:value-of select="normalize-space(.)"/>
        </contents>
    </xsl:template>

    <xsl:template
        match="mods:note[not(@type = 'statement of responsibility') and not(@type = 'organization') and not(@type = 'funding')]">
        <notes>
            <xsl:value-of select="normalize-space(.)"/>
        </notes>
    </xsl:template>

    <xsl:template match="mods:note[@type = 'statement of responsibility']">
        <attribution>
            <xsl:value-of select="normalize-space(.)"/>
        </attribution>
    </xsl:template>

    <xsl:template match="mods:note[@type = 'funding']">
        <funding>
            <xsl:value-of select="normalize-space(.)"/>
        </funding>
    </xsl:template>

    <xsl:template match="mods:accessCondition">
        <rights>
            <xsl:value-of select="normalize-space(.)"/>
        </rights>
    </xsl:template>

    <xsl:template match="mods:physicalLocation[@displayLabel = 'Harvard repository']">
        <repository>
            <xsl:value-of select="normalize-space(.)"/>
        </repository>
    </xsl:template>

    <xsl:template match="mods:extensions[HarvardDRS:DRSMetadata]" mode="mongoid">
        <xsl:apply-templates select="HarvardDRS:DRSMetadata" mode="mongoid"/>
    </xsl:template>

    <xsl:template match="HarvardDRS:DRSMetadata" mode="mongoid">
        <xsl:apply-templates select="HarvardDRS:drsObjectId" mode="mongoid"/>
    </xsl:template>

    <xsl:template match="HarvardDRS:drsObjectId" mode="mongoid">
        <_id>
            <xsl:value-of select="normalize-space(.)"/>
        </_id>
    </xsl:template>

    <xsl:template match="mods:extensions[HarvardDRS:DRSMetadata]" mode="drsid">
        <xsl:apply-templates select="HarvardDRS:DRSMetadata" mode="drsid"/>
    </xsl:template>

    <xsl:template match="HarvardDRS:DRSMetadata" mode="drsid">
        <xsl:apply-templates select="HarvardDRS:drsObjectId" mode="drsid"/>
    </xsl:template>

    <xsl:template match="HarvardDRS:drsObjectId" mode="drsid">
        <drsId>
            <xsl:value-of select="normalize-space(.)"/>
        </drsId>
    </xsl:template>

</xsl:stylesheet>
