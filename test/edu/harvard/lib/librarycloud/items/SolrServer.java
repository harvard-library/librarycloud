package edu.harvard.lib.librarycloud.items;

import org.apache.solr.client.solrj.impl.HttpSolrClient;

import static org.mockito.Mockito.*;

public class SolrServer {
    private static HttpSolrClient server = null;

    protected static HttpSolrClient getSolrConnection() {

        if(server == null) {
            try {
                server = mock(HttpSolrClient.class, Config.getInstance().SOLR_URL);

                    } catch (Exception e) {
                System.out.println( e);
            }
        }

        return server;
    }
}
