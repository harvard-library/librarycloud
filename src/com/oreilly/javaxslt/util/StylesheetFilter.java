package com.oreilly.javaxslt.util;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * A utility class that uses the Servlet 2.3 Filtering API to apply
 * an XSLT stylesheet to a servlet response.
 *
 * @author Eric M. Burke
 */
public class StylesheetFilter implements Filter {
    private FilterConfig filterConfig;
    private String xsltFileName;

    /**
     * This method is called once when the filter is first loaded.
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;

        // xsltPath should be something like "/WEB-INF/xslt/a.xslt"
        String xsltPath = filterConfig.getInitParameter("xsltPath");
        if (xsltPath == null) {
            throw new UnavailableException(
                    "xsltPath is a required parameter. Please "
                    + "check the deployment descriptor.");
        }

        // convert the context-relative path to a physical path name
        this.xsltFileName = filterConfig.getServletContext( )
                .getRealPath(xsltPath);

        // verify that the file exists
        if (this.xsltFileName == null ||
                !new File(this.xsltFileName).exists( )) {
            throw new UnavailableException(
                    "Unable to locate stylesheet: " + this.xsltFileName, 30);
        }
    }

    public void doFilter (ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {

        if (!(res instanceof HttpServletResponse)) {
            throw new ServletException("This filter only supports HTTP");
        }

        BufferedHttpResponseWrapper responseWrapper =
                new BufferedHttpResponseWrapper((HttpServletResponse) res);
        chain.doFilter(req, responseWrapper);

        // Tomcat 4.0 reuses instances of its HttpServletResponse
        // implementation class in some scenarios. For instance, hitting
        // reload( ) repeatedly on a web browser will cause this to happen.
        // Unfortunately, when this occurs, output is never written to the
        // BufferedHttpResponseWrapper's OutputStream. This means that the
        // XML output array is empty when this happens. The following
        // code is a workaround:
        byte[] origXML = responseWrapper.getBuffer( );
        if (origXML == null || origXML.length == 0) {
            // just let Tomcat deliver its cached data back to the client
            chain.doFilter(req, res);
            return;
        }

        try {
            // do the XSLT transformation
            Transformer trans = StylesheetCache.newTransformer(
                    this.xsltFileName);

            ByteArrayInputStream origXMLIn = new ByteArrayInputStream(origXML);
            Source xmlSource = null;
            System.out.println("XF: " + xsltFileName);
			if (xsltFileName.contains("copy.xsl")) {
				System.out.println("INCOPY");
	            //mjv added
	            BufferedReader br=new BufferedReader(new InputStreamReader(origXMLIn));
	            String line  = null;
	            String s = new String();
	            final StringBuffer buffer = new StringBuffer(2048);
	            while ((line = br.readLine()) != null) {
	                //buffer.append(line);
	                s += line;
	            }
	            //System.out.println("origXmlIn: " + s);
	            s = StringEscapeUtils.unescapeXml(s);
	            s = s.replaceAll("&","&amp;");
	            s = s.replaceAll("&amp;#","&#");
	            StringReader reader = new StringReader(s);
	            //
	            //System.out.println("origXmlIn: " + s);
	            //Source xmlSource = new StreamSource(origXMLIn);
	            xmlSource = new StreamSource(reader);
			}
			else
				xmlSource = new StreamSource(origXMLIn);
            ByteArrayOutputStream resultBuf = new ByteArrayOutputStream( );
            trans.transform(xmlSource, new StreamResult(resultBuf));

            res.setContentLength(resultBuf.size( ));
            String contentType = filterConfig.getInitParameter("contentType");
            //System.out.println("contentType: " + contentType);
            res.setContentType(contentType);
            //res.setContentType("application/xml");
            res.getOutputStream().write(resultBuf.toByteArray( ));
            res.flushBuffer( );
        } catch (TransformerException te) {
            throw new ServletException(te);
        }
    }

    /**
     * The counterpart to the init( ) method.
     */
    public void destroy( ) {
        this.filterConfig = null;
    }
}