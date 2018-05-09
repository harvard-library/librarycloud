curl -X POST -H 'Content-type:application/json' --data-binary '{
  "add-field":{
     "name":"language",
     "type":"string",
     "stored":true,
     "indexed":true,
     "multiValued": true
      },
  "add-field":{
     "name":"language_keyword",
     "type":"text_en",
     "stored":false,
     "indexed":true,
     "omitNorms":true,
     "multiValued": true
      },
  "add-copy-field":{
     "source":"language",
     "dest":"language_keyword"
     }
}' http://$SOLR_HOST:$SOLR_PORT/solr/librarycloud/schema
