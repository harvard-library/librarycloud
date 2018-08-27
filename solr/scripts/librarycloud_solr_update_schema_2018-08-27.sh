curl -X POST -H 'Content-type:application/json' --data-binary '{
  "add-field":{
    "name":"priorRecordIdentifier",
    "type":"string",
    "stored":true,
    "indexed":true,
    "multiValued":true
  },
  "add-field":{
    "name":"priorRecordIdentifier_keyword",
    "type":"text_en",
    "stored":false,
    "indexed":true,
    "multiValued":true
  },
  "add-copy-field":{
    "source":"priorRecordIdentifier",
    "dest":"priorRecordIdentifier_keyword"
  }, 
}' http://$SOLR_HOST:$SOLR_PORT/solr/librarycloud/schema
