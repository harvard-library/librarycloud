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

import org.apache.solr.client.solrj.impl.HttpSolrServer;
/**
*
*
* SolrServer is a singleton class for retrieving the solr connection
*
*/
public class SolrServer {
	private static HttpSolrServer server = null;
	
	protected static HttpSolrServer getSolrConnection() {
		try {
			server = new HttpSolrServer(Config.getInstance().SOLR_URL);
		} catch (Exception e) {
			// TO DO - error handling
			System.out.println( e);
		}
		return server;
	}
}
