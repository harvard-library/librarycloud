curl -X POST -H 'Content-type:application/json' --data-binary '{
  "add-field":{
     "name":"setSpec",
     "type":"string",
     "stored":true,
     "indexed":true,
     "multiValued": true
      },
  "add-field":{
     "name":"setSpec_keyword",
     "type":"text_en",
     "stored":false,
     "indexed":true,
     "omitNorms":true,
     "multiValued": true
      },
  "add-copy-field":{
     "source":"setSpec",
     "dest":"setSpec_keyword"
     }
}' http://$SOLR_HOST:$SOLR_PORT/solr/librarycloud/schema
