librarycloud
====

Library Cloud API

This is an alpha release Library Cloud API v2 (see http://librarycloud.harvard.edu/ for current library cloud). Currently the api contains "item" data, which is bibliographic data in modified MODS format ( see http://www.loc.gov/standards/mods ). Eventually other data types such as "collection" will be added. This is a java application using a jersey implementation of the jax-rs api for RESTful web services. It runs in a servlet container (such as tomcat) and relies on an instance of solr with fields mapped from MODS. Currently these fields are centered on marc bibliiographic data, but will eventually be extended to handle other data such as visual resources and EAD finding aid component data.

API documentation and link forthcoming

Installation
----------------

Prerequisites:
Download and install java 7. Be sure to set your JAVA_HOME environment variable.
Download and intsall a servlet container such as tomcat 7 (http://tomcat.apache.org/download-70.cgi)

Build and Deploy:

Running "ant" from the root directory (which contains build.xml) will compile source, create a librarycloud war file and deploy to the servlet container (such as tomcat - not included) location. To specify the servlet container location, set appserver.home in project.properties (you must change the ~/project.properties prior to build).

This application depends on a Solr backend. See [README_SOLR.md](README_SOLR.md) for information on installing and configuring a Solr backend.

The solr.url is specified in application.properties; the default is http://localhost:8983/solr/librarycloud, and will work with the included solr instance. To use your own solr 4x instance, copy the schema.xml from ~solr/librarycloud/conf/ directory to your solr core (such as librarycloud) conf directory (please see solr documentation for details on setting up cores), and change the solr.url property in application.properties if necessary (you must change the conf/application.properties prior to build; it will be copied to the WEB-INF/classes directory).

Now start your servlet container, such as tomcat, and go to http://localhost:8080/librarycloud/v2/items.

# API installation on clean RHEL server

Edit ```~/project.properties``` and set ```appserver_home=/var/lib/tomcat/```

Edit ```~/webapps/WEB-INF/classes/application.properties``` and set ```solr_url=http://localhost:8983/solr/librarycloud```

Run the following commands:

        sudo yum install tomcat tomcat-webapps tomcat-admin-webapps
        sudo chkconfig tomcat on
        sudo service tomcat start
        cd ~/librarycloud
        sudo ant
