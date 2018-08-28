curl -X POST -H 'Content-type:application/json' --data-binary '{
  "add-field":{
    "name":"processingDate",
    "type":"pdate",
    "indexed":true,
    "stored":true
  }
}' http://$SOLR_HOST:$SOLR_PORT/solr/librarycloud/schema
