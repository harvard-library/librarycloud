package edu.harvard.lib.lcloud;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;


/**
*
* ResourceNotFoundException builds an XML-formatted 404 exception containing code, message, info link
* 
*/

public class LibCommException extends WebApplicationException {

    public LibCommException(String message, int status) {
        super(Response.status(status).
        		entity(new ErrorItem(status, message, "https://wiki.harvard.edu/confluence/display/LibraryStaffDoc/Library+Cloud")).
        		type(MediaType.APPLICATION_XML).build());
    }
}