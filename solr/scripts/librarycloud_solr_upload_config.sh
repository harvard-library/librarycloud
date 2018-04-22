sdir="`dirname \"$0\"`"

(cd $sdir/../solr/librarycloud/conf && zip -r - *) | curl -X POST --header "Content-Type:application/octet-stream" --data-binary @- "http://$SOLR_HOST:$SOLR_PORT/solr/admin/configs?action=UPLOAD&name=librarycloud"
