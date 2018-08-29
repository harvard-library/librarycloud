package edu.harvard.lib.librarycloud.items;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBException;

import java.lang.Long;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandlerFactory;

import java.io.Serializable;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrDocument;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import static org.mockito.Mockito.*;

import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentCaptor;


import edu.harvard.lib.librarycloud.items.HttpUrlStreamHandler;
import edu.harvard.lib.librarycloud.items.*;


class ItemDAOTests {


    String convertToSolrQuery(MultivaluedMap<String, String> queryParams) throws Exception {
        ItemDAO itemdao = new ItemDAO();

        QueryResponse mockQueryResponse = mock(QueryResponse.class);
        SolrDocument testSolrDocument = new SolrDocument();
        testSolrDocument.addField("originalMods", "<mods xmlns=\"http://www.loc.gov/mods/v3\"></mods>");
        SolrDocumentList testSolrDocumentList = new SolrDocumentList();
        testSolrDocumentList.add(testSolrDocument);

        when(mockQueryResponse.getResults()).thenReturn(testSolrDocumentList);
        HttpSolrClient server = SolrServer.getSolrConnection();

        when(server.getBaseURL()).thenReturn("http://foo.com");

        ArgumentCaptor<SolrQuery> argument = ArgumentCaptor.forClass(SolrQuery.class);
        when(server.query(any(SolrQuery.class))).thenReturn(mockQueryResponse);

        SearchResultsMods results = null;

        try {
            results = itemdao.getModsResults(queryParams);
            System.out.println("--"+results.getSolrDocs().toString());
        } catch (JAXBException je) {
            System.out.println("error");
            je.printStackTrace();
        }

        verify(server).query(argument.capture());
        reset(server);
        return(argument.getValue().toString());
    }

    //Tests showing how the app translates its own query params into
    //solr queries
    @Test
    void basicKeywordQueryTest() throws Exception {
        MultivaluedMap<String, String> queryParams = new MultivaluedHashMap<>();
        queryParams.add("q", "foo");
        String solrQueryString = this.convertToSolrQuery(queryParams);
        assertEquals("q=keyword:foo", solrQueryString);
    }

    @Test
    void BooleanOrQueryTest() throws Exception {
        MultivaluedMap<String, String> queryParams = new MultivaluedHashMap<>();
        queryParams.add("q", "apples OR oranges");
        String solrQueryString = this.convertToSolrQuery(queryParams);
        assertEquals("q=keyword:(apples+OR+oranges)", solrQueryString);
    }

    @Test
    void BooleanAndQueryTest() throws Exception {
        MultivaluedMap<String, String> queryParams = new MultivaluedHashMap<>();
        queryParams.add("q", "apples AND oranges");
        String solrQueryString = this.convertToSolrQuery(queryParams);
        assertEquals("q=keyword:(apples+AND+oranges)", solrQueryString);
    }

    @Test
    void BooleanNotQueryTest() throws Exception {
        MultivaluedMap<String, String> queryParams = new MultivaluedHashMap<>();
        queryParams.add("q", "apples NOT pears");
        String solrQueryString = this.convertToSolrQuery(queryParams);
        assertEquals("q=keyword:(apples+NOT+pears)", solrQueryString);
    }

    @Test
    void BooleanAndThenNotQueryTest() throws Exception {
        MultivaluedMap<String, String> queryParams = new MultivaluedHashMap<>();
        queryParams.add("q", "(apples AND oranges) NOT pears");
        String solrQueryString = this.convertToSolrQuery(queryParams);
        assertEquals("q=keyword:((apples+AND+oranges)+NOT+pears)", solrQueryString);
    }

    //LTCLOUD-759
    @Test
    void SeriesTitleTest() throws Exception {
        MultivaluedMap<String, String> queryParams = new MultivaluedHashMap<>();
        queryParams.add("seriesTitle", "The Rambler");
        String solrQueryString = this.convertToSolrQuery(queryParams);
        assertEquals("q=relatedItem_keyword:The+Rambler", solrQueryString);
    }

    @Test
    void facetQueryTest() throws Exception {
        MultivaluedMap<String, String> queryParams = new MultivaluedHashMap<>();
        queryParams.add("facet", "repository");
        String solrQueryString = this.convertToSolrQuery(queryParams);
        assertEquals("facet=true&facet.field=repository&q=*:*", solrQueryString);
    }

    @Test
    void doesntCareIfYouSayFacetsTest() throws Exception {
        MultivaluedMap<String, String> queryParams = new MultivaluedHashMap<>();
        queryParams.add("facets", "repository");
        String solrQueryString = this.convertToSolrQuery(queryParams);
        assertEquals("facet=true&facet.field=repository&q=*:*", solrQueryString);
    }
}
