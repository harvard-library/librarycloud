echo "create librarycloud collection"
curl -X POST -H 'Content-type:application/json' --data-binary '{
     "create": {
       "name" : "librarycloud",
       "config" : "librarycloud",
       "numShards" : 1
     }
}' http://$SOLR_HOST:$SOLR_PORT/v2/c

echo "create extensions collection"
curl -X POST -H 'Content-type:application/json' --data-binary '{
     "create": {
       "name" : "extensions",
       "numShards" : 1
     }
}' http://$SOLR_HOST:$SOLR_PORT/v2/c

echo "create holdings collection"
curl -X POST -H 'Content-type:application/json' --data-binary '{
     "create": {
       "name" : "holdings",
       "numShards" : 1
     }
}' http://$SOLR_HOST:$SOLR_PORT/v2/c
