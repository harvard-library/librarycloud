sdir="`dirname \"$0\"`"

(cd $sdir && ./librarycloud_solr_nuke.sh )
(cd $sdir && ./librarycloud_solr_upload_config.sh )
(cd $sdir && ./librarycloud_solr_create_collection.sh )
echo "UPDATE LC SCHEMA"
(cd $sdir && ./librarycloud_solr_update_schema_2018-04-27.sh )
(cd $sdir && ./librarycloud_solr_update_schema_2018-05-09.sh )
(cd $sdir && ./librarycloud_solr_update_schema_2018-05-11.sh )
echo "UPDATE EXTENSIONS SCHEMA"
(cd $sdir && ./extensions_solr_add-fields.sh )
echo "UPDATE HOLDINGS SCHEMA"
(cd $sdir && ./holdings_solr_add-fields.sh )
