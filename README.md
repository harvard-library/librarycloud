librarycloud
====

Library Cloud API

This is an alpha release Library Cloud API v2 (see http://librarycloud.harvard.edu/ for current library cloud). Currently the api contains "item" data, which is bibliographic data in modified MODS format ( see http://www.loc.gov/standards/mods ). Eventually other data types such as "collection" will be added. This is a java application using a jersey implementation of the jax-rs api for RESTful web services. It runs in a servlet container (such as tomcat) and relies on an instance of solr with fields mapped from MODS. Currently these fields are centered on marc bibliiographic data, but will eventually be extended to handle other data such as visual resources and EAD finding aid component data.

API documentation and link forthcoming

Intstallation
----------------

More installation directions forthcoming, but for now, this will get you started ...

Running ant from the root directory will create a librarycloud war file and deploy to the specified servlet container;

Specify appserver.home and catalina.home in project.properties

Be sure to set your JAVA_HOME environment variable

Solr war, config files including schema.xml, and sample data forthcoming

solr url must be specified in conf/application.properties (default is localhost:8983/solr)


