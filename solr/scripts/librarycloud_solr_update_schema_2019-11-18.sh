curl -X POST -H 'Content-type:application/json' --data-binary '{
  "add-field":{
     "name":"drsObjectId",
     "type":"string",
     "stored":true,
     "indexed":true,
     "multiValued": true
      },
  "add-field":{
     "name":"drsObjectId_keyword",
     "type":"text_en",
     "stored":false,
     "indexed":true,
     "omitNorms":true,
     "multiValued": true
      },
  "add-field":{
     "name":"drsFileId",
     "type":"string",
     "stored":true,
     "indexed":true,
     "multiValued": true
      },
  "add-field":{
     "name":"drsFileId_keyword",
     "type":"text_en",
     "stored":false,
     "indexed":true,
     "omitNorms":true,
     "multiValued": true
      },
  "add-field":{
     "name":"contentModelCode",
     "type":"string",
     "stored":true,
     "indexed":true,
     "multiValued": true
      },
  "add-field":{
     "name":"contentModelCode_keyword",
     "type":"text_en",
     "stored":false,
     "indexed":true,
     "omitNorms":true,
     "multiValued": true
      },  
  "add-field":{
     "name":"maxImageDeliveryDimension",
     "type":"string",
     "stored":true,
     "indexed":true,
     "multiValued": true
      },
  "add-field":{
     "name":"maxImageDeliveryDimension_keyword",
     "type":"text_en",
     "stored":false,
     "indexed":true,
     "omitNorms":true,
     "multiValued": true
      },
  "add-field":{
     "name":"mimeType",
     "type":"string",
     "stored":true,
     "indexed":true,
     "multiValued": true
      },
  "add-field":{
     "name":"mimeType_keyword",
     "type":"text_en",
     "stored":false,
     "indexed":true,
     "omitNorms":true,
     "multiValued": true
      },
  "add-field":{
     "name":"ownerSuppliedName",
     "type":"string",
     "stored":true,
     "indexed":true,
     "multiValued": true
      },
  "add-field":{
     "name":"ownerSuppliedName_keyword",
     "type":"text_en",
     "stored":false,
     "indexed":true,
     "omitNorms":true,
     "multiValued": true
      },  
  "add-field":{
     "name":"suppliedFilename",
     "type":"string",
     "stored":true,
     "indexed":true,
     "multiValued": true
      },
  "add-field":{
     "name":"suppliedFilename_keyword",
     "type":"text_en",
     "stored":false,
     "indexed":true,
     "omitNorms":true,
     "multiValued": true
      },
  "add-field":{
     "name":"viewText",
     "type":"string",
     "stored":true,
     "indexed":true,
     "multiValued": true
      },
  "add-field":{
     "name":"viewText_keyword",
     "type":"text_en",
     "stored":false,
     "indexed":true,
     "omitNorms":true,
     "multiValued": true
      },
  "add-field":{
     "name":"harvardMetadataLabel",
     "type":"string",
     "stored":true,
     "indexed":true,
     "multiValued": true
      },
  "add-field":{
     "name":"harvardMetadataLabel_keyword",
     "type":"text_en",
     "stored":false,
     "indexed":true,
     "omitNorms":true,
     "multiValued": true
      },
  "add-field":{
     "name":"harvardMetadataIdentifier",
     "type":"string",
     "stored":true,
     "indexed":true,
     "multiValued": true
      },
  "add-field":{
     "name":"harvardMetadataIdentifier_keyword",
     "type":"text_en",
     "stored":false,
     "indexed":true,
     "omitNorms":true,
     "multiValued": true
	 },
  "add-field":{
     "name":"harvardMetadataType",
     "type":"string",
     "stored":true,
     "indexed":true,
     "multiValued": true
      },
  "add-field":{
     "name":"harvardMetadataType_keyword",
     "type":"text_en",
     "stored":false,
     "indexed":true,
     "omitNorms":true,
     "multiValued": true
      },
  "add-field":{
    "name":"insertionDate",
    "type":"tdate",
    "stored":true,
    "indexed":true,
    "multiValued": true
   },
  "add-copy-field":{
     "source":"drsObjectId",
     "dest":"drsObjectId_keyword"
     },
  "add-copy-field":{
     "source":"drsFileId",
     "dest":"drsFileId_keyword"
    },
  "add-copy-field":{
     "source":"contentModelCode",
     "dest":"contentModelCode_keyword"
    },
  "add-copy-field":{
     "source":"maxImageDeliveryDimension",
     "dest":"maxImageDeliveryDimension_keyword"
    },
  "add-copy-field":{
     "source":"mimeType",
     "dest":"mimeType_keyword"
    },
  "add-copy-field":{
     "source":"ownerSuppliedName",
     "dest":"ownerSuppliedName_keyword"
    },
  "add-copy-field":{
     "source":"suppliedFilename",
     "dest":"suppliedFilename_keyword"
    },
  "add-copy-field":{
     "source":"viewText",
     "dest":"viewText_keyword"
    },
  "add-copy-field":{
     "source":"harvardMetadataIdentifier",
     "dest":"harvardMetadataIdentifier_keyword"
    },
  "add-copy-field":{
     "source":"harvardMetadataLabel",
     "dest":"harvardMetadataLabel_keyword"
    },
  "add-copy-field":{
     "source":"harvardMetadataType",
     "dest":"harvardMetadataType_keyword"
    }
}' http://$SOLR_HOST:$SOLR_PORT/solr/librarycloud/schema
