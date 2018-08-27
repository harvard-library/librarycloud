curl -X POST -H 'Content-type:application/json' --data-binary '{
  "delete-copy-field":{
    "source":"resourceType", "dest":"resourceType_keyword"
   },
  "delete-field":{
     "name":"resourceType",
  },
  "add-field":{
    "name":"_resourceType",
    "type":"string",
    "stored":true,
    "indexed":true,
    "multiValued":true
  },
  "add-copy-field":{
    "source":"_resourceType",
    "dest":"resourceType_keyword"
  }, 
}' http://$SOLR_HOST:$SOLR_PORT/solr/librarycloud/schema
