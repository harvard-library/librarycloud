curl -X POST -H 'Content-type:application/json' --data-binary '{
  "add-field":{
    "name":"availableTo",
    "type":"string",
    "stored":true,
    "indexed":true,
    "multiValued":false
  }
}' http://$SOLR_HOST:$SOLR_PORT/solr/librarycloud/schema
