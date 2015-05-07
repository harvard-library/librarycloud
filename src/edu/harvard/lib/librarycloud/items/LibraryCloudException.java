package edu.harvard.lib.librarycloud.items;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
*
* LibraryCloudException builds an XML/JSON-formatted exception containing code, message, info link
* 
*/

public class LibraryCloudException extends WebApplicationException {

    public LibraryCloudException(String message, Response.Status status) {
        super(Response.status(status).
        		entity(new ErrorItem(status.getStatusCode(), message, "https://wiki.harvard.edu/confluence/display/LibraryStaffDoc/Library+Cloud")).build());
    }
}