<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema"
    xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:mods="http://www.loc.gov/mods/v3" version="2.0"
    xmlns:saxon="http://saxon.sf.net/"
    >   
    <xsl:template match="/">
        <xsl:apply-templates/>
    </xsl:template>
    
    <xsl:template match="results">
        <results>
            <xsl:apply-templates/>
        </results>
    </xsl:template>
    
    <xsl:template match="pagination">
        <pagination>
            <xsl:copy-of select="*"/>
        </pagination>
        <xsl:apply-templates select="item"/>
    </xsl:template>
    
    <xsl:template match="item">
        <item>
        <xsl:variable name="unparsed" select="original"/>
        <xsl:variable name="parsed" select="saxon:parse($unparsed)"/>
        <xsl:copy-of select="$parsed" exclude-result-prefixes="#all" />
        </item>
    </xsl:template>
</xsl:stylesheet>