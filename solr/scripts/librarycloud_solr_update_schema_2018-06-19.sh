curl -X POST -H 'Content-type:application/json' --data-binary '{
  "add-field":{
    "name":"digitalFormat",
    "type":"string",
    "stored":true,
    "indexed":true,
    "multiValued": true
     },
  "add-field":{
     "name":"digitalFormat_keyword",
     "type":"text_en",
     "stored":false,
     "indexed":true,
     "omitNorms":true,
     "multiValued": true
      },
  "add-copy-field":{
     "source":"digitalFormat",
     "dest":"digitalFormat_keyword"
     }

}' http://$SOLR_HOST:$SOLR_PORT/solr/librarycloud/schema
