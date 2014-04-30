package com.oreilly.javaxslt.util;

import java.io.*;
import java.util.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

/**
* A utility class that caches XSLT stylesheets in memory.
*
* @author Eric M. Burke
* @version $Id: StylesheetCache.java,v 1.1 2014/04/11 15:14:19 michaelv Exp $
*/
public class StylesheetCache {
    // map xslt file names to MapEntry instances
    // (MapEntry is defined below)
    private static Map<String, MapEntry> cache = new HashMap<String, MapEntry>();

    /**
* Flush all cached stylesheets from memory, emptying the cache.
*/
    public static synchronized void flushAll() {
        cache.clear();
    }

    /**
* Flush a specific cached stylesheet from memory.
*
* @param xsltFileName the file name of the stylesheet to remove.
*/
    public static synchronized void flush(String xsltFileName) {
        cache.remove(xsltFileName);
    }

    /**
* Obtain a new Transformer instance for the specified XSLT file name.
* A new entry will be added to the cache if this is the first request
* for the specified file name.
*
* @param xsltFileName the file name of an XSLT stylesheet.
* @return a transformation context for the given stylesheet.
*/
    public static synchronized Transformer newTransformer(String xsltFileName)
            throws TransformerConfigurationException {
        File xsltFile = new File(xsltFileName);

        // determine when the file was last modified on disk
        long xslLastModified = xsltFile.lastModified();
        MapEntry entry = (MapEntry) cache.get(xsltFileName);

        if (entry != null) {
            // if the file has been modified more recently than the
            // cached stylesheet, remove the entry reference
            if (xslLastModified > entry.lastModified) {
                entry = null;
            }
        }

        // create a new entry in the cache if necessary
        if (entry == null) {
            Source xslSource = new StreamSource(xsltFile);

            //TransformerFactory transFact = TransformerFactory.newInstance();
            TransformerFactory transFact = TransformerFactory.newInstance("net.sf.saxon.TransformerFactoryImpl",null);

            Templates templates = transFact.newTemplates(xslSource);

            entry = new MapEntry(xslLastModified, templates);
            cache.put(xsltFileName, entry);
        }

        return entry.templates.newTransformer();
    }

    // prevent instantiation of this class
    private StylesheetCache() {
    }

    /**
* This class represents a value in the cache Map.
*/
    static class MapEntry {
        long lastModified; // when the file was modified
        Templates templates;

        MapEntry(long lastModified, Templates templates) {
            this.lastModified = lastModified;
            this.templates = templates;
        }
    }
}