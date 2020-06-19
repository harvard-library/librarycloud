<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet version="2.0" xmlns:xlink="http://www.w3.org/1999/xlink"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="text" encoding="utf-8"/>
	<xsl:strip-space elements="*"/>

	<xsl:param name="delim" select="','"/>
	<xsl:param name="quote" select="'&quot;'"/>
	<xsl:param name="break" select="'&#xA;'"/>

	<xsl:template match="records">
		<xsl:apply-templates/>
	</xsl:template>
	
	<xsl:template match="record[source='MH:ALEPH']|record[source='MH:ALMA']">
		<xsl:text>"</xsl:text>
		<xsl:value-of select="identifier"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="replace(title,'&quot;','&quot;&quot;')"/>	
		<xsl:text>",</xsl:text>
		<!--<xsl:text>"</xsl:text>
		<xsl:value-of select="issuance"/>	
		<xsl:text>",</xsl:text>-->
		<xsl:text>"</xsl:text>
		<xsl:value-of select="placeCode"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="isUS"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="placeName"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="publisher"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="dateIssued"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="dateStart"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="dateEnd"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="name"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="role"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="date"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="uri"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="accessFlag"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="preview"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="isCollection"/>
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:apply-templates select="sets"/>
		<xsl:text>"</xsl:text>
		<xsl:text>&#xA;</xsl:text>
	</xsl:template>

	<xsl:template match="record[source='MH:VIA']">
		<xsl:text>"</xsl:text>
		<xsl:value-of select="identifier"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="replace(title,'&quot;','&quot;&quot;')"/>	
		<xsl:text>",</xsl:text>
		<!--<xsl:text>"</xsl:text>
		<xsl:value-of select="type"/>	
		<xsl:text>",</xsl:text>-->
		<xsl:text>"</xsl:text>
		<xsl:value-of select="placeName"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="dateStart"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="name"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="role"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="date"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:apply-templates select="uri"/>
		<!--<xsl:value-of select="uri"/>-->
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="relatedItem"/>
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="relationship"/>
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="accessFlag"/>
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="preview"/>
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:apply-templates select="sets"/>
		<xsl:text>"</xsl:text>
		<xsl:text>&#xA;</xsl:text>
	</xsl:template>
	
	<xsl:template match="record[source='MH:OASIS']">
		<xsl:text>"</xsl:text>
		<xsl:value-of select="identifier"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="replace(title,'&quot;','&quot;&quot;')"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="dateCreated"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="replace(coltitle,'&quot;','&quot;&quot;')"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="colname"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="coldate"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="colid"/>	
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:apply-templates select="uri"/>
		<!--<xsl:value-of select="uri"/>-->
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="accessFlag"/>
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:value-of select="preview"/>
		<xsl:text>",</xsl:text>
		<xsl:text>"</xsl:text>
		<xsl:apply-templates select="sets"/>
		<xsl:text>"</xsl:text>
		<xsl:text>&#xA;</xsl:text>
	</xsl:template>

	<xsl:template match="uri">
		<xsl:value-of select="."/>
		<xsl:if test="not(position()=last())">
			<xsl:text>,</xsl:text>
		</xsl:if>
	</xsl:template>

	<xsl:template match="sets">
		<xsl:apply-templates select="setname"/>
	</xsl:template>
	<xsl:template match="setname">
		<xsl:value-of select="."/>
		<xsl:if test="not(position()=last())">
			<xsl:text>, </xsl:text>
		</xsl:if>
	</xsl:template>

</xsl:stylesheet>
