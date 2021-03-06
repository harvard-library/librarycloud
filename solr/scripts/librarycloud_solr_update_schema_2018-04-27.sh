curl -X POST -H 'Content-type:application/json' --data-binary '{
  "add-field":{
     "name":"setName",
     "type":"string",
     "stored":true,
     "indexed":true,
     "multiValued": true
      },
  "add-field":{
     "name":"setName_keyword",
     "type":"text_en",
     "stored":false,
     "indexed":true,
     "omitNorms":true,
     "multiValued": true
      },
  "add-field":{
     "name":"setSystemId",
     "type":"string",
     "stored":true,
     "indexed":true,
     "multiValued": true
      },
  "add-field":{
     "name":"setSystemId_keyword",
     "type":"text_en",
     "stored":false,
     "indexed":true,
     "omitNorms":true,
     "multiValued": true
      },
  "add-copy-field":{
     "source":"setName",
     "dest":"setName_keyword"
     },
  "add-copy-field":{
     "source":"setSystemId",
     "dest":"setSystemId_keyword"
    }
}' http://$SOLR_HOST:$SOLR_PORT/solr/librarycloud/schema
