Running Solr and Zookeeper dependencies
====
The Librarycloud Items API requires Solr 7 to be running in cloud mode.

Solr 7 in turn requires Zookeeper.

Neither Solr nor Zookeeper need run on the same host as the Items API or one another.

# Install and start Zookeeper #

Download the latest release of zookeeper and place it in a sensible system
location (or in a subdirectory of LIBRARYCLOUD_HOME).

Start Zookeeper:

```
/path/to/zookeeper/bin/zkCli.sh
```

# Install and start Solr #

Download the latest release of Solr 7.x and place it in a sensible system
location (or in a subdirectory of LIBRARYCLOUD_HOME).

Start Solr:

```
/path/to/solr-dist/bin/solr start -c -z ZK_HOST:ZK_PORT -p SOLR_PORT
```

# Upload the configset and create the collection #

In the home directory of LibraryCloud Items API:

```
export SOLR_HOST=yoursolrhost
export SOLR_PORT=yoursolrport
/path/to/librarycloud/solr/scripts/librarycloud_solr_upload_config.sh
/path/to/librarycloud/solr/scripts/librarycloud_solr_create_collection.sh
```

Confirm collection was added to Solr and has its schema:

```
curl http://$SOLR_HOST:$SOLR_PORT/v2/c/librarycloud/schema
```

The Solr backend must contain a collection called 'holdings' and a collection called 'extensions'. At a minimum, these can be empty, schemaless collections that have had fields added to them using the scripts below:

```
/path/to/librarycloud/solr/scripts/extensions_solr_add-fields.sh
/path/to/librarycloud/solr/scripts/holdingss_solr_add-fields.sh
```

# Point the LibraryCloud ItemAPI at the Solr backend #

Edit ```~/webapps/WEB-INF/classes/application.properties``` and set ```solr_url=http://{YOUR_SOLR_HOST}:{YOUR_SOLR_PORT}/solr/librarycloud```. Then redeploy.
