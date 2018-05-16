echo "nuke librarycloud replicas, collection, and config"
curl -X DELETE "http://$SOLR_HOST:$SOLR_PORT/solr/admin/collections?action=DELETESHARD&shard=librarycloud_shard1_replica_n1&collection=librarycloud"

curl -X DELETE "http://$SOLR_HOST:$SOLR_PORT/solr/admin/collections?action=DELETE&name=librarycloud"

curl -X DELETE "http://$SOLR_HOST:$SOLR_PORT/solr/admin/configs?action=DELETE&name=librarycloud"

echo "nuke extensions replicas, collection, and config"
curl -X DELETE "http://$SOLR_HOST:$SOLR_PORT/solr/admin/collections?action=DELETESHARD&shard=extensions_shard1_replica_n1&collection=extensions"

curl -X DELETE "http://$SOLR_HOST:$SOLR_PORT/solr/admin/collections?action=DELETE&name=extensions"

curl -X DELETE "http://$SOLR_HOST:$SOLR_PORT/solr/admin/configs?action=DELETE&name=extensions.AUTOCREATED"

echo "nuke holdings replicas, collection, and config"
curl -X DELETE "http://$SOLR_HOST:$SOLR_PORT/solr/admin/collections?action=DELETESHARD&shard=holdings_shard1_replica_n1&collection=holdings"

curl -X DELETE "http://$SOLR_HOST:$SOLR_PORT/solr/admin/collections?action=DELETE&name=holdings"

curl -X DELETE "http://$SOLR_HOST:$SOLR_PORT/solr/admin/configs?action=DELETE&name=holdings.AUTOCREATED"
