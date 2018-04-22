curl -X POST -H 'Content-type:application/json' --data-binary '{
     "create": {
       "name" : "librarycloud",
       "config" : "librarycloud",
       "numShards" : 1
     }
}' http://$SOLR_HOST:$SOLR_PORT/v2/c
