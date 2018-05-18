/**********************************************************************
 * Copyright (c) 2012 by the President and Fellows of Harvard College
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA.
 *
 * Contact information
 *
 * Office for Information Systems
 * Harvard University Library
 * Harvard University
 * Cambridge, MA  02138
 * (617)495-3724
 * hulois@hulmail.harvard.edu
 **********************************************************************/
package edu.harvard.lib.librarycloud.items;

import java.util.List;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.lang.Integer;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.RangeFacet;
import org.apache.solr.common.SolrDocumentList;

@XmlTransient
public class SearchResults {
  private QueryResponse response;
  private Pagination pagination;
  private Facet facet;
  private DateFormat dfSolr = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
  private DateFormat dfApi = new SimpleDateFormat("yyyy");
  private Calendar cal = Calendar.getInstance();
  private Logger log = Logger.getLogger(SearchResults.class);

  SearchResults() {
  }

  public void setResponse(QueryResponse response) {
    this.response = response;
    setFacets();
  }

  public SolrDocumentList getSolrDocs()  {
    return this.response.getResults();
  }

	@XmlElement(name = "pagination")
	public Pagination getPagination() {
		return pagination;
	}

  public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

  @XmlElement(name = "facets")
	public Facet getFacet() {
		return facet;
	}

  private void setFacets() {
    List<FacetField> facets = response.getFacetFields();
    if(facets == null)
        return;
    facet = new Facet();
    List<FacetType> facetTypes = new ArrayList<FacetType>();
    for (FacetField facetField : facets) {
      List<FacetTerm> facetTerms = new ArrayList<FacetTerm>();
      FacetType facetType = new FacetType();
      facetType.setFacetName(facetField.getName());
      List<FacetField.Count> facetEntries = facetField.getValues();
      for (FacetField.Count fcount : facetEntries) {
        if (fcount.getCount() > 0) {
          FacetTerm facetTerm = new FacetTerm();
          facetTerm.setTermName(fcount.getName());
          facetTerm.setTermCount(fcount.getCount());
          facetTerms.add(facetTerm);
        }
      }
      facetType.setFacetTerms(facetTerms);
      facetTypes.add(facetType);
    }
    facet.setFacetTypes(facetTypes);

    List<RangeFacet> rangeFacets = response.getFacetRanges();

    List<FacetRange> facetRanges = new ArrayList<FacetRange>();
    for (RangeFacet rangeFacet : rangeFacets) {
      log.debug(rangeFacet.toString());
      FacetRange facetRange = new FacetRange();
      facetRange.setStart(rangeFacet.getStart());
      facetRange.setEnd(rangeFacet.getEnd());
      int gapYears = parseGap(rangeFacet.getGap());
      List<RangeFacet.Count> counts = rangeFacet.getCounts();
      for (RangeFacet.Count c : counts) {
        try {
          Date d = dfSolr.parse(c.getValue());
          String yearStart = dfApi.format(d);
          cal.setTime(d);
          cal.add(Calendar.YEAR, gapYears);
          d = cal.getTime();
          String yearEnd = dfApi.format(d);
          facetRange.addCount(yearStart+"-"+yearEnd, c.getCount());
        } catch (ParseException pe) {
          log.error(pe, pe);
        }
      }

      facetRanges.add(facetRange);
    }

    facet.setFacetRanges(facetRanges);
  }

  private int parseGap(Object gap) {
    Pattern p = Pattern.compile("[^0-9]([0-9]+)YEAR");
    Matcher m = p.matcher(gap.toString());
    if(m.matches()) {
      return Integer.parseInt(m.group(1));
    } else {
      return 0;
    }
  }
}
