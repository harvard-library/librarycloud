package edu.harvard.lib.lcloud;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
*
* ResourceNotFoundException builds an XML-formatted 404 exception containing code, message, info link
* 
*/

public class ResourceNotFoundException extends WebApplicationException {

    public ResourceNotFoundException(String message) {
        super(Response.status(Response.Status.NOT_FOUND).
        		entity(new ErrorItem(Response.Status.NOT_FOUND.getStatusCode(), message, "http://api-test.lib.harvard.edu:8080/v2/docs")).
        		type(MediaType.APPLICATION_XML).build());
    }
}