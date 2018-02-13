curl -X POST -H 'Content-type:application/json' --data-binary '{
    "add-field": [
        {
            "name": "accessFlag",
            "type": "string",
            "indexed": true,
            "stored": true
        },
        {
            "name": "contentModel",
            "type": "string",
            "indexed": true,
            "stored": true
        },
        {
            "name": "uriType",
            "type": "string",
            "indexed": true,
            "stored": true
        },
        {
            "name": "ownerCode",
            "type": "string",
            "indexed": true,
            "stored": true
        },
        {
            "name": "ownerCodeDisplayName",
            "type": "string",
            "indexed": true,
            "stored": true
        },
        {
            "name": "metsLabel",
            "type": "string",
            "indexed": true,
            "stored": true
        },
        {
            "name": "lastModifiedDate",
            "type": "string",
            "indexed": true,
            "stored": true
        },
		{
            "name": "accessFlag_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": false,
            "omitNorms": true
        },
        {
            "name": "contentModel_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": false,
            "omitNorms": true
        },
        {
            "name": "uriType_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": false,
            "omitNorms": true
        },
        {
            "name": "ownerCode_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": false,
            "omitNorms": true
        },
        {
            "name": "ownerCodeDisplayName_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": false,
            "omitNorms": true
        },
        {
            "name": "metsLabel_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": false,
            "omitNorms": true
        },
        {
            "name": "lastModifiedDate_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": false,
            "omitNorms": true
        },
        {
            "name": "urn",
            "type": "string",
            "indexed": true,
            "stored": true
        },
        {
            "name": "fileDeliveryURL",
            "type": "string",
            "indexed": true,
            "stored": true
        },
        {
            "name": "thumbnailURL",
            "type": "string",
            "indexed": true,
            "stored": true
        },
	{
            "name": "urn_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": false,
            "omitNorms": true
        },
        {
            "name": "keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        }
    ],
    "add-copy-field": [
        {
            "source": "accessFlag",
            "dest": "accessFlag_keyword"
        },
        {
            "source": "contentModel",
            "dest": "contentModel_keyword"
        },
        {
            "source": "uriType",
            "dest": "uriType_keyword"
        },
        {
            "source": "ownerCode",
            "dest": "ownerCode_keyword"
        },
        {
            "source": "ownerCodeDisplayName",
            "dest": "ownerCodeDisplayName_keyword"
        },
        {
            "source": "metsLabel",
            "dest": "metsLabel_keyword"
        },
        {
            "source": "lastModifiedDate",
            "dest": "lastModifiedDate_keyword"
        },
        {
            "source": "*",
            "dest": "keyword"
        },
        {
            "source": "urn",
            "dest": "urn_keyword"
        }
    ]
}}' http://localhost:8983/solr/extensions/schema
