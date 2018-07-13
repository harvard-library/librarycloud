curl -X POST -H 'Content-type:application/json' --data-binary '{
  "add-field":{
    "name":"fileDeliveryURL",
    "type":"string",
    "stored":true,
    "indexed":true,
    "multiValued":true
  },
  "delete-field":{
    "name":"lastModifiedDate",
   }
  "add-field":{
    "name":"lastModifiedDate",
    "type":"pdate",
    "stored":true,
    "indexed":true
   }
}' http://$SOLR_HOST:$SOLR_PORT/solr/librarycloud/schema
