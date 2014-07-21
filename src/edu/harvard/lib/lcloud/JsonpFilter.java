/*   Please see LICENSE.txt
		 **********************************************************************/
/**
 * @author Bobbi Fox
 *
 *   Project:  ecru
 *   
 *   Handles jsonP  requests
 *
 *  NOTE that this is a work derived from previous work at the Unversity
 *  of California (see licence below
 *
 */


/**
 *  Copyright 2009, 2010 The Regents of the University of California
 *  Licensed under the Educational Community License, Version 2.0
 *  (the "License"); you may not use this file except in compliance
 *  with the License. You may obtain a copy of the License at
 *
 *  http://www.osedu.org/licenses/ECL-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an "AS IS"
 *  BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 *  or implied. See the License for the specific language governing
 *  permissions and limitations under the License.
 *  
 *  [adopted into Course Reserves Unleashed! by Bobbi Fox on 23 August 2013 from
 *  the blog posting http://josh.media.berkeley.edu/?p=78 
 *  Modified so that the inner static class ByteArrayServletOutputStream is
 *  always used if the request might be a legitimate jsonp one
 *  ]
 *  
 *
 */
package edu.harvard.lib.lcloud;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**********************************************************************
 *   Please see LICENSE.txt
 **********************************************************************/
/**
 * @author Bobbi Fox
 *
 *   Project:  library cloud (taken as is from ecru project)
* Adds padding to json responses when the 'jsonp' parameter is specified.
*/
public class JsonpFilter implements Filter {
 
  /** The logger */
  private static final Logger logger = LoggerFactory.getLogger(JsonpFilter.class);

  /** The querystring parameter that indicates the response should be padded */
  public static final String CALLBACK_PARAM = "jsonp";

  /** The regular expression to ensure that the callback is safe for display to a browser */
  public static final Pattern SAFE_PATTERN = Pattern.compile("[a-zA-Z0-9\\.\\_]+");

  /** The default padding to use if the specified padding contains invalid characters */
  public static final String DEFAULT_CALLBACK = "handleMatterhornData";

  /** The content type for jsonp is "application/x-javascript", not "application/json". */
  public static final String JS_CONTENT_TYPE = "application/x-javascript";

  /** The character encoding. */
  public static final String CHARACTER_ENCODING = "UTF-8";

  /** The post padding, which is always ');' no matter what the pre-padding looks like */
  public static final String POST_PADDING = ");";


  private ServletContext servletContext = null;
  private FilterConfig filterConfig = null;

@Override
public void destroy() {
	// TODO Auto-generated method stub
	
}

@Override
public void init(FilterConfig config) throws ServletException {
	this.servletContext = config.getServletContext();
	this.filterConfig = config;
}

@Override
 public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
         ServletException {

   // Cast the request and response to HTTP versions
   HttpServletRequest request = (HttpServletRequest) req;
   HttpServletResponse originalResponse = (HttpServletResponse) resp;

   // Determine whether the response must be wrapped
   String callbackValue = request.getParameter(CALLBACK_PARAM);
   if (callbackValue == null || callbackValue.isEmpty()) {
     logger.debug("No json padding requested from {}", request);
     chain.doFilter(request, originalResponse);
   } else {
     logger.debug("Json padding '{}' requested from {}", callbackValue, request);
    // System.err.println("json request with Accept header of " + request.getHeader("Accept"));
     // Ensure the callback value contains only safe characters
     if (!SAFE_PATTERN.matcher(callbackValue).matches()) {
       callbackValue = DEFAULT_CALLBACK;
     }

     // Write the padded response
     String preWrapper = callbackValue + "(";
     HttpServletResponseContentWrapper wrapper = new HttpServletResponseContentWrapper(originalResponse, preWrapper);

     chain.doFilter(request, wrapper);
     wrapper.setContentType(resp.getContentType());
     
     wrapper.flushWrapper();
   }
 }

 /**
 * A response wrapper that allows for json padding.
 */
 static class HttpServletResponseContentWrapper extends HttpServletResponseWrapper {

   protected ByteArrayServletOutputStream buffer;
   protected PrintWriter bufferWriter;
   protected boolean committed = false;
   protected boolean enableWrapping = false;
   protected boolean isJson = false;
   protected boolean isError = false;
   protected String preWrapper;

   public HttpServletResponseContentWrapper(HttpServletResponse response, String preWrapper) {
     super(response);
     this.preWrapper = preWrapper;
     this.buffer = new ByteArrayServletOutputStream();
   }

   @Override
   public void setStatus(int sc) {
	   if (sc != SC_OK && sc != SC_CREATED && sc != SC_NO_CONTENT) {
		  isError = true;
	   }
	   super.setStatus(sc);
   }

public void flushWrapper() throws IOException {

       if (bufferWriter != null)
         bufferWriter.close();
       if (buffer != null)
         buffer.close();
 
       byte[] content = wrap(buffer.toByteArray());
       if (enableWrapping  && !isError)
    	   getResponse().setContentType(JS_CONTENT_TYPE);
       getResponse().setContentLength(content.length);

       getResponse().setCharacterEncoding(CHARACTER_ENCODING);
       getResponse().getOutputStream().write(content);
       getResponse().flushBuffer();
       committed = true;

   }

   public byte[] wrap(byte[] content) throws IOException {
     StringBuilder  sb = (enableWrapping && !isError)?new StringBuilder(preWrapper):new StringBuilder();
     sb.append(new String(content, CHARACTER_ENCODING));
     if (enableWrapping && !isError)
    	 sb.append(POST_PADDING);
     return sb.toString().getBytes(CHARACTER_ENCODING);
   }

   @Override
   public String getContentType() {
     return enableWrapping ? JS_CONTENT_TYPE : getResponse().getContentType();
   }

   /**
   * If the content type is set to JSON, we enable wrapping. Otherwise, we leave it disabled.
   * 
   * {@inheritDoc}
   * 
   * @see javax.servlet.ServletResponseWrapper#setContentType(java.lang.String)
   */
   @Override
   public void setContentType(String type) {
     enableWrapping = MediaType.APPLICATION_JSON.equals(type);
     isJson = enableWrapping;
   }

   @Override
   public ServletOutputStream getOutputStream() throws IOException {
     //return enableWrapping ? buffer : getResponse().getOutputStream();
	   return buffer;
   }

   @Override
   public PrintWriter getWriter() throws IOException {
//     if (enableWrapping) {
       if (bufferWriter == null) {
         bufferWriter = new PrintWriter(new OutputStreamWriter(buffer, this.getCharacterEncoding()));
       }
       return bufferWriter;
 //    } else {
 //      return getResponse().getWriter();
 //    }
   }

   @Override
   public void setBufferSize(int size) {
     if (enableWrapping) {
       buffer.enlarge(size);
     } else {
       getResponse().setBufferSize(size);
     }
   }

   @Override
   public int getBufferSize() {
     return enableWrapping ? buffer.size() : getResponse().getBufferSize();
   }

   @Override
   public void flushBuffer() throws IOException {
     if( ! enableWrapping) getResponse().flushBuffer();
   }

   @Override
   public boolean isCommitted() {
     return enableWrapping ? committed : getResponse().isCommitted();
   }

   @Override
   public void reset() {
     getResponse().reset();
     buffer.reset();
   }

   @Override
   public void resetBuffer() {
     getResponse().resetBuffer();
     buffer.reset();
   }
 }

 static class ByteArrayServletOutputStream extends ServletOutputStream {

   protected byte buf[];

   protected int count;

   public ByteArrayServletOutputStream() {
     this(32);
   }

   public ByteArrayServletOutputStream(int size) {
     if (size < 0) {
       throw new IllegalArgumentException("Negative initial size: " + size);
     }
     buf = new byte[size];
   }

   public synchronized byte toByteArray()[] {
     return Arrays.copyOf(buf, count);
   }

   public synchronized void reset() {
     count = 0;
   }

   public synchronized int size() {
     return count;
   }

   public void enlarge(int size) {
     if (size > buf.length) {
       buf = Arrays.copyOf(buf, Math.max(buf.length << 1, size));
     }
   }

   @Override
   public synchronized void write(int b) throws IOException {
     int newcount = count + 1;
     enlarge(newcount);
     buf[count] = (byte) b;
     count = newcount;
   }
 }
}