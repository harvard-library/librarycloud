curl -X POST -H 'Content-type:application/json' --data-binary '{
    "add-field": [
        {
            "name": "drsObjectId",
            "type": "string",
            "indexed": true,
            "stored": true
        },
        {
            "name": "drsFileId",
            "type": "string",
            "indexed": true,
            "stored": true
        },
        {
            "name": "contentModelCode",
            "type": "string",
            "indexed": true,
            "stored": true
        },
        {
            "name": "harvardMetadataLink",
            "type": "string",
            "indexed": true,
            "stored": true,
			      "multiValued":true
        },
        {
            "name": "insertionDate",
            "type": "string",
            "indexed": true,
            "stored": true
        },
        {
            "name": "maxImageDeliveryDimension",
            "type": "string",
            "indexed": true,
            "stored": true
        },
		{
            "name": "mimeType",
            "type": "string",
            "indexed": true,
            "stored": true
        },
		{
            "name": "ownerSuppliedName",
            "type": "string",
            "indexed": true,
            "stored": true
        },
		{
            "name": "suppliedFilename",
            "type": "string",
            "indexed": true,
            "stored": true
        },
     {
             "name":"status",
             "type":"string",
             "stored":true,
             "indexed":true,
             "multiValued": false
      },
		{
            "name": "viewText",
            "type": "string",
            "indexed": true,
            "stored": true
        }
    ]
}' http://$SOLR_HOST:$SOLR_PORT/solr/extensions/schema
