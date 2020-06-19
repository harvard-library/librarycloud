<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:mods="http://www.loc.gov/mods/v3"
    xmlns:lc="http://api.lib.harvard.edu/v2/item"
    xmlns:HarvardDRS="http://hul.harvard.edu/ois/xml/ns/HarvardDRS"
    xmlns:sets="http://hul.harvard.edu/ois/xml/ns/sets"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema"
    exclude-result-prefixes="xs" version="2.0">
    
    <xsl:output method="xml" indent="yes" omit-xml-declaration="yes"/>
    
    <xsl:template match="lc:results">
        <xsl:apply-templates/>
    </xsl:template>
    <xsl:template match="lc:items">
        <records>
            <xsl:apply-templates/>
        </records>
    </xsl:template>

    <xsl:template match="lc:pagination"/>

    <!-- ALMA -->
    <xsl:template match="mods:mods[mods:recordInfo/mods:recordIdentifier[@source='MH:ALMA']]">
        <record>
            <xsl:apply-templates select="mods:recordInfo/mods:recordIdentifier"  mode="alma"/>
            <xsl:apply-templates select="mods:titleInfo[not(@type)][1]/mods:title" mode="alma"/>
            <xsl:choose>
                <xsl:when test="mods:name/mods:role[1]/mods:roleTerm">
                    <xsl:apply-templates select="mods:name[mods:role[1]/mods:roleTerm]" mode="alma"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:apply-templates select="mods:name[1]" mode="alma"/>
                </xsl:otherwise>
            </xsl:choose>
            <xsl:apply-templates select="mods:originInfo" mode="alma"/>
            <xsl:apply-templates select="mods:location/mods:url[@access = 'raw object']" mode="alma"/>
            <xsl:apply-templates select="mods:location/mods:url[@access = 'preview']" mode="alma"/>
            <xsl:apply-templates select="mods:extension/HarvardDRS:DRSMetadata" mode="alma"/>
            <xsl:apply-templates select="mods:typeOfResource" mode="alma"/>
            <xsl:apply-templates select="mods:extension/sets:sets" mode="alma"/>
            <!--<xsl:apply-templates select="mods:identifier[@type='uri']"/>-->
        </record>
    </xsl:template>
    
    <xsl:template match="mods:recordIdentifier" mode="alma">
        <identifier>
            <xsl:value-of select="."/>
        </identifier>
        <source>
            <xsl:value-of select="@source"/>
        </source>
    </xsl:template>
    
    <xsl:template match="mods:title" mode="alma">
        <title>
            <xsl:apply-templates mode="alma"/>
        </title>
    </xsl:template>
    
    <xsl:template match="mods:name" mode="alma">
        <!--<xsl:if test="mods:role/mods:roleTerm[@authority] eq 'creator'">-->
        <!--<xsl:if test="mods:role[1]/mods:roleTerm eq 'creator'">-->
        <role>
            <xsl:value-of select="mods:role/mods:roleTerm"/>
        </role>
        <name>
            <xsl:value-of select="mods:namePart"/>
        </name>
        <date>
            <xsl:value-of select="mods:namePart[@type]"/>
        </date>
        <!--</xsl:if>-->
    </xsl:template>
    
    <xsl:template match="mods:originInfo" mode="alma">
        <placeCode>
            <xsl:value-of select="mods:place/mods:placeTerm[@type = 'code']"/>
        </placeCode>
        <isUS>
            <xsl:choose>
                <xsl:when test="ends-with(mods:place/mods:placeTerm[@type = 'code'], 'u') and string-length(mods:place/mods:placeTerm[@type = 'code']) = 3">
                    <xsl:text>United States</xsl:text>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:text/>
                </xsl:otherwise>
            </xsl:choose>
        </isUS>
        <placeName>
            <xsl:value-of select="mods:place/mods:placeTerm[@type = 'text']"/>
        </placeName>
        <publisher>
            <xsl:value-of select="mods:publisher"/>
        </publisher>
        <dateIssued>
            <xsl:choose>
                <xsl:when test="mods:dateOther[@keyDate]">
                    <xsl:value-of select="mods:dateOther[@keyDate]"/>
                </xsl:when>
                <xsl:when test="mods:dateCreated[not(@*)]">
                    <xsl:value-of select="mods:dateCreated[not(@*)]"/>
                </xsl:when>
                <xsl:when test="mods:dateCreated[not(@point)]">
                    <xsl:value-of select="mods:dateCreated[not(@point)]"/>
                </xsl:when>
                <xsl:when test="mods:dateIssued[not(@*)]">
                    <xsl:value-of select="mods:dateIssued[not(@*)]"/>
                </xsl:when>
                <xsl:when test="mods:dateIssued[not(@point)]">
                    <xsl:value-of select="mods:dateIssued[not(@point)]"/>
                </xsl:when>
            </xsl:choose>
        </dateIssued>
        <dateStart>
            <xsl:choose>
                <xsl:when test="mods:dateIssued[@point = 'start']">
                    <xsl:value-of select="mods:dateIssued[@point = 'start']"/>
                </xsl:when>
                <xsl:when test="mods:dateCreated[@point = 'start']">
                    <xsl:value-of select="mods:dateCreated[@point = 'start']"/>
                </xsl:when>
            </xsl:choose>
        </dateStart>
        <dateEnd>
            <xsl:choose>
                <xsl:when test="mods:dateIssued[@point = 'end']">
                    <xsl:value-of select="mods:dateIssued[@point = 'end']"/>
                </xsl:when>
                <xsl:when test="mods:dateCreated[@point = 'end']">
                    <xsl:value-of select="mods:dateCreated[@point = 'end']"/>
                </xsl:when>
            </xsl:choose>
        </dateEnd>
        <issuance>
            <xsl:value-of select="mods:issuance"/>
        </issuance>
    </xsl:template>
    
    <xsl:template match="mods:url[@access = 'raw object']" mode="alma">
        <uri>
            <xsl:value-of select="."/>
        </uri>
    </xsl:template>
    
    <xsl:template match="mods:url[@access = 'preview']" mode="alma">
        <preview>
            <xsl:value-of select="."/>
        </preview>
    </xsl:template>
    
    <xsl:template match="HarvardDRS:DRSMetadata" mode="alma">
        <xsl:apply-templates select="HarvardDRS:accessFlag" mode="alma"/>
    </xsl:template>
    
    <xsl:template match="HarvardDRS:accessFlag" mode="alma">
        <accessFlag>
            <xsl:value-of select="."/>
        </accessFlag>
    </xsl:template>
    
    <xsl:template match="mods:typeOfResource" mode="alma">
        <isCollection>
            <xsl:choose>
                <xsl:when test="./@collection = 'yes'">
                    <xsl:text>Collection record</xsl:text>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:text/>
                </xsl:otherwise>
            </xsl:choose>
        </isCollection>
    </xsl:template>
    
    <xsl:template match="sets:sets" mode="alma">
        <sets>
            <xsl:apply-templates select="sets:set" mode="alma"/>
        </sets>
    </xsl:template>
    <xsl:template match="sets:set" mode="alma">
        <xsl:apply-templates select="sets:setName" mode="alma"/>
    </xsl:template>
    <xsl:template match="sets:setName" mode="alma">
        <setname><xsl:value-of select="."/></setname>
    </xsl:template>
    
    <!--   VIA -->
    
    <xsl:template match="mods:mods[mods:recordInfo/mods:recordIdentifier[@source='MH:VIA']]">
        <record>
            <xsl:apply-templates select="mods:recordInfo/mods:recordIdentifier"  mode="via"/>
            <xsl:apply-templates select="mods:titleInfo[not(@*)][1]/mods:title" mode="via"/>
            <xsl:apply-templates select="mods:name" mode="via"/>
            <xsl:apply-templates select="mods:originInfo" mode="via"/>
            <xsl:apply-templates select=".//mods:location/mods:url[@access = 'raw object']" mode="via"/>
            <xsl:apply-templates select="mods:relatedItem[1]" mode="via"/>
            <xsl:apply-templates select="mods:extension/HarvardDRS:DRSMetadata" mode="via"/>
            <xsl:apply-templates select=".//mods:location/mods:url[@access = 'preview']" mode="via"/>
            <xsl:apply-templates select="mods:extension/sets:sets" mode="via"/>
        </record>
    </xsl:template>
    
    <xsl:template match="mods:recordIdentifier" mode="via">
        <identifier>
            <xsl:value-of select="."/>
        </identifier>
        <source>
            <xsl:value-of select="@source"/>
        </source>
    </xsl:template>
    
    <xsl:template match="mods:title" mode="via">
        <title>
            <xsl:apply-templates mode="via"/>
        </title>
    </xsl:template>
    
    <xsl:template match="mods:name" mode="via">
        <!--<xsl:if test="mods:role/mods:roleTerm[@authority] eq 'creator'">-->
        <xsl:if test="mods:role[1]/mods:roleTerm[1] eq 'creator'">
            <role>
                <xsl:value-of select="mods:role/mods:roleTerm"/>
            </role>
            <name>
                <xsl:value-of select="mods:namePart"/>
            </name>
            <date>
                <xsl:value-of select="mods:namePart[@type]"/>
            </date>
        </xsl:if>
    </xsl:template>
    
    <xsl:template match="mods:originInfo" mode="via">
        <placeCode>
            <xsl:value-of select="mods:place/mods:placeTerm[@type = 'code']"/>
        </placeCode>
        <placeName>
            <xsl:value-of select="mods:place/mods:placeTerm[@type = 'text']"/>
        </placeName>
        <publisher>
            <xsl:value-of select="mods:publisher"/>
        </publisher>
        <dateIssued>
            <xsl:value-of select="mods:dateOther[(@keyDate)]"/>
        </dateIssued>
        <dateStart>
            <xsl:choose>
                <xsl:when test="mods:dateCreated[not(@point = 'start')]">
                    <xsl:value-of select="mods:dateCreated[not(@point)]"/>
                </xsl:when>
                <xsl:when
                    test="mods:dateCreated[@point = 'start'] and mods:dateCreated[@point = 'end']">
                    <xsl:value-of select="mods:dateCreated[@point = 'start']"/>
                    <xsl:text>-</xsl:text>
                    <xsl:value-of select="mods:dateCreated[@point = 'end']"/>
                </xsl:when>
                <xsl:when
                    test="mods:dateCreated[@point = 'start'] and not(mods:dateCreated[@point = 'end'])">
                    <xsl:value-of select="mods:dateCreated[@point = 'start']"/>
                </xsl:when>
                <xsl:when test="mods:dateOther[@keyDate]">
                    <xsl:value-of select="mods:dateOther[@keyDate]"/>
                </xsl:when>
            </xsl:choose>
        </dateStart>
    </xsl:template>
    
    <xsl:template match="mods:url[@access = 'raw object']" mode="via">
        <type>
            <xsl:text>still image</xsl:text>
        </type>
        <uri>
            <xsl:value-of select="."/>
        </uri>
    </xsl:template>
    
    <xsl:template match="mods:url[@access = 'preview']" mode="via">
        <preview>
            <xsl:value-of select="."/>
        </preview>
    </xsl:template>
    
    <xsl:template match="mods:relatedItem" mode="via">
        <relatedItem>
            <xsl:value-of select="mods:titleInfo/mods:title"/>
        </relatedItem>
        <relationship>
            <xsl:value-of select="@type"/>
        </relationship>
    </xsl:template>
    
    <xsl:template match="HarvardDRS:DRSMetadata" mode="via">
        <xsl:apply-templates select="HarvardDRS:accessFlag" mode="via"/>
    </xsl:template>
    
    <xsl:template match="HarvardDRS:accessFlag" mode="via">
        <accessFlag>
            <xsl:value-of select="."/>
        </accessFlag>
    </xsl:template>
    
    <xsl:template match="sets:sets" mode="via">
        <sets>
            <xsl:apply-templates select="sets:set" mode="via"/>
        </sets>
    </xsl:template>
    <xsl:template match="sets:set" mode="via">
        <xsl:apply-templates select="sets:setName" mode="via"/>
    </xsl:template>
    <xsl:template match="sets:setName" mode="via">
        <setname><xsl:value-of select="."/></setname>
    </xsl:template>
    
    <!--  OASIS -->
    
    <xsl:template match="mods:mods[mods:recordInfo/mods:recordIdentifier[@source='MH:OASIS']]">
        <record>
            <xsl:apply-templates select="mods:recordInfo/mods:recordIdentifier" mode="EAD"/>
            <xsl:apply-templates select="mods:titleInfo[not(@*)][1]/mods:title" mode="EAD"/>
            <xsl:apply-templates select="(mods:name)[1]" mode="EAD"/>
            <xsl:apply-templates select="mods:originInfo" mode="EAD"/>
            <xsl:apply-templates select="mods:typeOfResource" mode="EAD"/>
            <!--<xsl:apply-templates select="mods:location/mods:url"/>-->
            <xsl:apply-templates select="mods:location/mods:url[@access = 'raw object']" mode="EAD"/>
            <xsl:apply-templates select="mods:relatedItem[@displayLabel = 'collection']" mode="EAD"/>
            <xsl:apply-templates
                select="mods:relatedItem/mods:relatedItem[@displayLabel = 'collection']" mode="EAD"/>
            <xsl:apply-templates
                select="mods:relatedItem/mods:relatedItem/mods:relatedItem[@displayLabel = 'collection']" mode="EAD"/>
            <xsl:apply-templates
                select="mods:relatedItem/mods:relatedItem/mods:relatedItem/mods:relatedItem[@displayLabel = 'collection']" mode="EAD"/>
            <xsl:apply-templates select="mods:extension/HarvardDRS:DRSMetadata" mode="EAD"/>
            <xsl:apply-templates select=".//mods:location/mods:url[@access = 'preview']" mode="EAD"/>
            <xsl:apply-templates select="mods:extension/sets:sets" mode="EAD"/>
        </record>
    </xsl:template>
    
    <xsl:template match="mods:recordIdentifier" mode="EAD">
        <identifier>
            <xsl:value-of select="."/>
        </identifier>
        <source>
            <xsl:value-of select="@source"/>
        </source>
    </xsl:template>
    
    <xsl:template match="mods:title" mode="EAD">
        <title>
            <xsl:apply-templates mode="EAD"/>
        </title>
    </xsl:template>
    
    <xsl:template match="mods:name" mode="EAD">
        <name>
            <xsl:value-of select="(mods:namePart)[1]"/>
        </name>
        <date>
            <xsl:value-of select="mods:namePart[@type]"/>
        </date>
        <role>
            <xsl:value-of select="mods:role"/>
        </role>
    </xsl:template>
    
    <xsl:template match="mods:originInfo" mode="EAD">
        <placeName>
            <xsl:value-of select="mods:place/mods:placeTerm"/>
        </placeName>
        <publisher>
            <xsl:value-of select="mods:publisher"/>
        </publisher>
        <dateCreated>
            <xsl:value-of select="mods:dateCreated[not(@*)]"/>
        </dateCreated>
    </xsl:template>
    
    <xsl:template match="mods:typeOfResource" mode="EAD">
        <typeOfResource>
            <xsl:value-of select="."/>
        </typeOfResource>
    </xsl:template>
    
    <xsl:template match="mods:url[@access = 'raw object']" mode="EAD">
        <uri>
            <xsl:value-of select="."/>
        </uri>
    </xsl:template>
    <xsl:template match="mods:url[@access = 'preview']" mode="EAD">
        <preview>
            <xsl:value-of select="."/>
        </preview>
    </xsl:template>
    
    
    <xsl:template match="mods:relatedItem[@displayLabel = 'collection']" mode="EAD">
        <coltitle>
            <xsl:value-of select="mods:titleInfo/mods:title"/>
        </coltitle>
        <colname>
            <xsl:value-of select="mods:name/mods:namePart"/>
        </colname>
        <coldate>
            <xsl:value-of select="mods:originInfo/mods:dateCreated[not(@point)]"/>
        </coldate>
        <colid>
            <xsl:value-of select="mods:recordInfo/mods:recordIdentifier"/>
        </colid>
    </xsl:template>
    <xsl:template match="HarvardDRS:DRSMetadata" mode="EAD">
        <xsl:apply-templates select="HarvardDRS:accessFlag" mode="EAD"/>
    </xsl:template>
    
    <xsl:template match="HarvardDRS:accessFlag" mode="EAD">
        <accessFlag>
            <xsl:value-of select="."/>
        </accessFlag>
    </xsl:template>
    
    <xsl:template match="sets:sets" mode="EAD">
        <sets>
            <xsl:apply-templates select="sets:set" mode="EAD"/>
        </sets>
    </xsl:template>
    <xsl:template match="sets:set" mode="EAD">
        <xsl:apply-templates select="sets:setName" mode="EAD"/>
    </xsl:template>
    <xsl:template match="sets:setName" mode="EAD">
        <setname><xsl:value-of select="."/></setname>
    </xsl:template>
    
</xsl:stylesheet>
