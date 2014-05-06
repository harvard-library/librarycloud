package edu.harvard.lib.lcloud;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
*
* BadParameterException builds an XML-formatted 400 exception containing code, message, info link
* 
*/

public class BadParameterException extends WebApplicationException {

    public BadParameterException(String message) {
        super(Response.status(Response.Status.BAD_REQUEST).
        		entity(new ErrorItem(Response.Status.BAD_REQUEST.getStatusCode(), message, "http://api-test.lib.harvard.edu:8080/v2/docs")).
        		type(MediaType.APPLICATION_XML).build());
    }
}
