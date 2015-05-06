package edu.harvard.lib.librarycloud.items;

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
        		entity(new ErrorItem(Response.Status.NOT_FOUND.getStatusCode(), message, "https://wiki.harvard.edu/confluence/display/LibraryStaffDoc/Library+Cloud")).build());
    }
}