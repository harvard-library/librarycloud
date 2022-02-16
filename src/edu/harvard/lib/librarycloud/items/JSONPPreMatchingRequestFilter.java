package edu.harvard.lib.librarycloud.items;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities; 
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Filter to ensure that JSON requests are treated as JSONP
 * requests if they include the "callback" parameter, even if
 * they include a ".json" extension or Accept header of 
 * "application/json".
 */

@PreMatching
@Provider
@Priority(Priorities.USER)
public class JSONPPreMatchingRequestFilter implements ContainerRequestFilter {
 	protected Logger log = LogManager.getLogger(JSONPPreMatchingRequestFilter.class); 	

    @Override
    public void filter(ContainerRequestContext requestContext)
                        throws IOException {

        MultivaluedMap<String,String> params = requestContext.getUriInfo().getQueryParameters();
        if (params.containsKey("callback")) {
        	String accept = requestContext.getHeaders().getFirst("Accept");
        	if (accept == null || accept.equals("*/*") || accept.equals("application/json")) {
    			requestContext.getHeaders().putSingle("Accept", "application/javascript");	
        	}
        }
    }
}