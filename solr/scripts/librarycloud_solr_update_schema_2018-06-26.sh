curl -X POST -H 'Content-type:application/json' --data-binary '{
  "add-field-type": {
    "name": "text_blob",
    "class": "solr.TextField",
    },
  "replace-field":{
    "name":"originalMods",
    "type":"text_blob",
    "stored":true,
    "indexed":false
     }
}' http://$SOLR_HOST:$SOLR_PORT/solr/librarycloud/schema
