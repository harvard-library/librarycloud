package edu.harvard.lib.lcloud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Properties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.BeforeClass;
import org.junit.Test;

import gov.loc.mods.v3.ModsType;

/** *
* @author 
*/
public class ClientTest {
	
	private Client client;
	private WebTarget target;
	private static String URL;
	private static String ID = "000404917-9";
	private static String BADID = "NO_SUCH_ID";
	private static String QUERYPARAM = "?q=peanuts";
	private static String BADQUERYPARAM = "?nosuchindex=peanuts";
	
	@BeforeClass
	public static void setUpClient() {

		Properties props = new Properties();
		try {
			props.load(new FileInputStream("conf/application.properties"));
		} catch (Exception e) {
			fail("Couldn't load project configuration!");
		} 
		URL = props.getProperty("server_url");

	}
	
	
    @Test
    public void testItemHeaders() {
    	target = getTarget(ID);	
    	Response response = target.request(MediaType.APPLICATION_XML).get();
    	assertEquals(Status.OK.getStatusCode(), response.getStatus());
    	assertEquals(MediaType.APPLICATION_XML_TYPE, response.getMediaType());
    }
 
    @Test
    public void testSearchResultsHeaders() {
    	target = getTarget(QUERYPARAM);	
    	Response response = target.request(MediaType.APPLICATION_XML).get();
    	assertEquals(Status.OK.getStatusCode(), response.getStatus());
    	assertEquals(MediaType.APPLICATION_XML_TYPE, response.getMediaType());
    }
    	
    @Test
    public void testModsType() {    	

    	target = getTarget(ID);	
    	
    	// read the response as a string and look for the rec id
    	String modsString = target.request(MediaType.APPLICATION_XML).get(String.class);
    	assertTrue("correct rec id checking response as string",modsString.contains("<recordIdentifier>" + ID + "</recordIdentifier>"));
        
    	// read the response as a ModsType object, and marshall to an XML string
        GenericType<JAXBElement<ModsType>> generic = new GenericType<JAXBElement<ModsType>>() {};
    	JAXBElement<ModsType> jaxbMods = target.request(MediaType.APPLICATION_XML).get(generic);
    	ModsType modsType = jaxbMods.getValue();
    	
    	Marshaller marshaller = null; 
    	try {
    		marshaller = JAXBHelper.context.createMarshaller();
    		StringWriter sw = new StringWriter();
    		marshaller.marshal(modsType, sw);
    		String marshalledModsString = sw.toString();
    		System.out.println("MARSH: " + marshalledModsString);
        	assertTrue("correct rec id checking marshalled ModsType response",marshalledModsString.contains("<mods:recordIdentifier>" + ID + "</mods:recordIdentifier>"));
    	} catch (JAXBException jaxbe) {
    		fail("Could not marshal mods object to string");
    	}
 	
    }  

    
    @Test
    public void testItemNotFoundError() {
    	target = getTarget(BADID);	
    	Response response = target.request(MediaType.APPLICATION_XML).get();
    	assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }
  
    @Test
    public void testBadParamError() {
    	target = getTarget(BADQUERYPARAM);	
    	Response response = target.request(MediaType.APPLICATION_XML).get();
    	assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }
    
    private Client createClient() {
        return ClientBuilder
                .newBuilder()
                .build();
    }
    
    private WebTarget getTarget(String id) {
    	String fullUrl = URL + id;
    	fullUrl = fullUrl.replace("/?","?");
        client = createClient();
        target = client
                .target(fullUrl);
        return target;
    }
    
}
