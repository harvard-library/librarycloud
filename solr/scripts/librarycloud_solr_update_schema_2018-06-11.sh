curl -X POST -H 'Content-type:application/json' --data-binary '{
  "add-field":{
     "name":"repository",
     "type":"string",
     "stored":true,
     "indexed":true,
     "multiValued": true
      },
  "add-field":{
     "name":"repository_keyword",
     "type":"text_en",
     "stored":false,
     "indexed":true,
     "omitNorms":true,
     "multiValued": true
      },
  "add-copy-field":{
     "source":"repository",
     "dest":"repository_keyword"
     }
}' http://$SOLR_HOST:$SOLR_PORT/solr/librarycloud/schema
