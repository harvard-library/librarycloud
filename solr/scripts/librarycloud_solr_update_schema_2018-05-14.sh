curl -X POST -H 'Content-type:application/json' --data-binary '{
  "add-field":{
     "name":"text",
     "type":"text_general",
     "stored":false,
     "indexed":true,
     "multiValued": true
      }
}' http://$SOLR_HOST:$SOLR_PORT/solr/librarycloud/schema
