curl -X POST -H 'Content-type:application/json' --data-binary '{
  "add-field":{
    "name":"repositoryLongForm",
    "type":"string",
    "indexed":true,
    "stored":true,
    "multiValued": true,
  },
  "add-copy-field":{
    "source":"repositoryLongForm",
    "dest":"repository_keyword"
  },
}' http://$SOLR_HOST:$SOLR_PORT/solr/librarycloud/schema
