package edu.harvard.lib.librarycloud.items;

import static org.junit.Assert.*;
import edu.harvard.lib.librarycloud.items.JAXBHelper;
import edu.harvard.lib.librarycloud.items.LibraryCloudException;
import edu.harvard.lib.librarycloud.items.mods.IdentifierType;
import edu.harvard.lib.librarycloud.items.mods.ModsType;
import edu.harvard.lib.librarycloud.items.mods.RecordInfoType;
import edu.harvard.lib.librarycloud.items.mods.TitleInfoType;

import java.io.FileInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.junit.BeforeClass;
import org.junit.Test;

public class ItemTest {

	
	private static HttpSolrServer server = null;
	private static String SOLR_URL = null;
	private static String ID = "000404917-9";
	
	@BeforeClass
	public static void getSolrConnection() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("conf/application.properties"));
		} catch (Exception e) {
			fail("Couldn't load project configurationx!");
		} 
		SOLR_URL = props.getProperty("solr_url");
		System.out.println(SOLR_URL);
	}
	
	@Test
	public void unmarshallMarshallMockItem() {

		String modsString = TestRecords.recOne;
		ModsType modsType = null;
    	Marshaller marshaller = null; 
		try {
			modsType = getTestModsType(modsString);
			assertNotNull("mock mods string unmarshalled to object and is not null", modsType);

    		marshaller = JAXBHelper.context.createMarshaller();
    		StringWriter sw = new StringWriter();
    		marshaller.marshal(modsType, sw);
    		String marshalledModsString = sw.toString();
        	assertTrue("correct rec id checking marshalled (mock) ModsType",marshalledModsString.contains("<mods:recordIdentifier>000373965-1</mods:recordIdentifier>"));
		
			
			/*
			List<Object> modsElementList = modsType.getModsGroup();
			for (Object o: modsElementList) {

				if (o.getClass().getName().equals("gov.loc.mods.v3.RecordInfoType")) {
					RecordInfoType recordInfo = (RecordInfoType)o;
					RecordInfoType.RecordIdentifier recordId;
					System.out.println();

				}	
			}	
			*/
		}
		catch (JAXBException je) {
			fail("Could not unmarshall mods record" + je);
		}
		
	}

	
	
	@Test
	public void unmarshallMarshallSolrItem() {

		String modsString = TestRecords.recOne;
		ModsType modsType = null;
    	Marshaller marshaller = null; 
		try {
			modsType = getTestModsTypeFromSolr(ID);
			assertNotNull("embedded mods field from solr unmarshalled to object and is not null", modsType);

    		marshaller = JAXBHelper.context.createMarshaller();
    		StringWriter sw = new StringWriter();
    		marshaller.marshal(modsType, sw);
    		String marshalledModsString = sw.toString();
    		System.out.println(marshalledModsString);
        	assertTrue("correct rec id checking marshalled ModsType (from solr)",marshalledModsString.contains("<mods:recordIdentifier>" + ID + "</mods:recordIdentifier>"));
		
		}
		catch (JAXBException je) {
			fail("Could not unmarshall mods record" + je);
		}
		catch (SolrServerException sse) {
			fail("Problem connecting to solr" + sse);
		}		
	}
	
    private ModsType getTestModsType(String modsString) throws JAXBException {
       	Unmarshaller unmarshaller = JAXBHelper.context.createUnmarshaller();
        StringReader reader = new StringReader(modsString);
        ModsType modsType = (ModsType) ((JAXBElement)(unmarshaller.unmarshal(reader))).getValue();
        return modsType;
    }
	
	private ModsType getTestModsTypeFromSolr(String id) throws JAXBException, SolrServerException {
		SolrDocumentList docs;
		SolrDocument doc;
		ModsType modsType = new ModsType();
	    server = new HttpSolrServer(SOLR_URL);
	    SolrQuery query = new SolrQuery("recordIdentifier:" + id);	
	    QueryResponse response = server.query(query);
	    docs = response.getResults();
	    if (docs.size() == 0)
	    	throw new LibraryCloudException("Item " + id + " not found",Response.Status.NOT_FOUND);
	    else {
		    doc = docs.get(0);
		    String modsString = (String) doc.getFieldValue("originalMods");
        	modsType = getTestModsType(modsString);
	    }

		return modsType;
	}
	
    
}
