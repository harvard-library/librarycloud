curl -X POST -H 'Content-type:application/json' --data-binary '{
     "add-field-type":{
        "name":"date_range",
        "class":"solr.DateRangeField"
        }

}' http://$SOLR_HOST:$SOLR_PORT/solr/librarycloud/schema

curl -X POST -H 'Content-type:application/json' --data-binary '{
     "add-field":{
        "name":"dateRange",
        "type":"date_range",
        "stored":true,
        "indexed":true,
        "multiValued": false
      }

}' http://$SOLR_HOST:$SOLR_PORT/solr/librarycloud/schema
