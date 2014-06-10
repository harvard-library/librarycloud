/**********************************************************************
 * Copyright (c) 2012 by the President and Fellows of Harvard College
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or (at
 * your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307
 * USA.
 *
 * Contact information
 *
 * Office for Information Systems
 * Harvard University Library
 * Harvard University
 * Cambridge, MA  02138
 * (617)495-3724
 * hulois@hulmail.harvard.edu
 **********************************************************************/

package edu.harvard.lib.lcloud;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import gov.loc.mods.v3.ModsType;

/**
*
* JAXBHelper is a singleton class that is used for jaxb marshalling and unmarshalling of 
* mods and search results (other classes could be added if needed)
* using a singleton provides vastly quicker marshalling/unmarshalling
* 
* @author Michael Vandermillen
*
*/
public class JAXBHelper {
	
    static final JAXBContext context = initContext();

    private static JAXBContext initContext()  {
    	JAXBContext context = null;
    	try {
    		context = JAXBContext.newInstance(ModsType.class,SearchResults.class,SearchResultsSlim.class);
    	} catch (JAXBException je) {
    		System.out.println(je);
    	}
    	return context;
    }
	
}
