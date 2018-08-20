curl -X POST -H 'Content-type:application/json' --data-binary '{
    "add-field": [
        {
            "name": "resourceType",
            "type": "string",
            "indexed": true,
            "stored": true
        },
        {
            "name": "publisher",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "originDate",
            "type": "string",
            "indexed": true,
            "stored": true
        },
        {
            "name": "physicalDescription",
            "type": "string",
            "indexed": true,
            "stored": true
        },
        {
            "name": "source",
            "type": "string",
            "indexed": true,
            "stored": true
        },
        {
            "name": "originalMods",
            "type": "string",
            "indexed": false,
            "stored": true
        },
        {
            "name": "isCollection",
            "type": "string",
            "indexed": true,
            "stored": true
        },
        {
            "name": "isManuscript",
            "type": "string",
            "indexed": true,
            "stored": true
        },
        {
            "name": "isOnline",
            "type": "string",
            "indexed": true,
            "stored": true
        },
        {
            "name": "stackscore",
            "type": "string",
            "indexed": true,
            "stored": true
        },
        {
            "name": "inDRS",
            "type": "string",
            "indexed": true,
            "stored": true
        },
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
            "name": "genre",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "edition",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "title",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "abstractTOC",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "languageCode",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "name",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "role",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "originPlace",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "dateIssued",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "dateCreated",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "dateCaptured",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "copyrightDate",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "classification",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "identifier",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "physicalLocation",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "shelfLocator",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "url",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "urn",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "issuance",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "relatedItem",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "subject",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "subject.hierarchicalGeographic",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "subject.topic",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "subject.geographic",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "subject.temporal",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "subject.titleInfo",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "subject.name",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "subject.genre",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "subject.hierarchicalGeographic.country",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "subject.hierarchicalGeographic.continent",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "subject.hierarchicalGeographic.province",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "subject.hierarchicalGeographic.region",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "subject.hierarchicalGeographic.state",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "subject.hierarchicalGeographic.territory",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "subject.hierarchicalGeographic.county",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "subject.hierarchicalGeographic.city",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "subject.hierarchicalGeographic.citySection",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "subject.hierarchicalGeographic.island",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "subject.hierarchicalGeographic.area",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "subject.hierarchicalGeographic.extraterrestrialArea",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "stackscore_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": false,
            "omitNorms": true
        },
        {
            "name": "collectionTitle",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "collectionTitle_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "collectionId",
            "type": "string",
            "indexed": true,
            "stored": true,
            "multiValued": true
        },
        {
            "name": "collectionId_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "inDRS_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": false,
            "omitNorms": true
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
            "name": "resourceType_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "genre_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "publisher_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "originDate_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "edition_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "physicalDescription_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "source_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "recordIdentifier_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "subject_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "subject.hierarchicalGeographic_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "isCollection_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "isManuscript_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "isOnline_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "title_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "abstractTOC_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "languageCode_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "name_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "role_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "originPlace_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "dateIssued_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "dateCreated_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "dateCaptured_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "copyrightDate_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "classification_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "identifier_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "physicalLocation_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "shelfLocator_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "url_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "urn_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "issuance_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "relatedItem_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "subject.topic_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "subject.geographic_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "subject.temporal_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "subject.titleInfo_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "subject.name_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "subject.genre_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "subject.hierarchicalGeographic.country_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "subject.hierarchicalGeographic.continent_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "subject.hierarchicalGeographic.province_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "subject.hierarchicalGeographic.region_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "subject.hierarchicalGeographic.state_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "subject.hierarchicalGeographic.territory_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "subject.hierarchicalGeographic.county_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "subject.hierarchicalGeographic.city_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "subject.hierarchicalGeographic.citySection_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "subject.hierarchicalGeographic.island_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "subject.hierarchicalGeographic.area_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
            "omitNorms": true
        },
        {
            "name": "subject.hierarchicalGeographic.extraterrestrialArea_keyword",
            "type": "text_en",
            "indexed": true,
            "stored": false,
            "multiValued": true,
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
            "source": "stackscore",
            "dest": "stackscore_keyword"
        },
        {
            "source": "collectionTitle",
            "dest": "collectionTitle_keyword"
        },
        {
            "source": "collectionId",
            "dest": "collectionId_keyword"
        },
        {
            "source": "inDRS",
            "dest": "inDRS_keyword"
        },
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
            "source": "resourceType",
            "dest": "resourceType_keyword"
        },
        {
            "source": "genre",
            "dest": "genre_keyword"
        },
        {
            "source": "publisher",
            "dest": "publisher_keyword"
        },
        {
            "source": "originDate",
            "dest": "originDate_keyword"
        },
        {
            "source": "edition",
            "dest": "edition_keyword"
        },
        {
            "source": "physicalDescription",
            "dest": "physicalDescription_keyword"
        },
        {
            "source": "source",
            "dest": "source_keyword"
        },
        {
            "source": "recordIdentifier",
            "dest": "recordIdentifier_keyword"
        },
        {
            "source": "subject",
            "dest": "subject_keyword"
        },
        {
            "source": "subject.hierarchicalGeographic",
            "dest": "subject.hierarchicalGeographic_keyword"
        },
        {
            "source": "isCollection",
            "dest": "isCollection_keyword"
        },
        {
            "source": "isManuscript",
            "dest": "isManuscript_keyword"
        },
        {
            "source": "isOnline",
            "dest": "isOnline_keyword"
        },
        {
            "source": "title",
            "dest": "title_keyword"
        },
        {
            "source": "abstractTOC",
            "dest": "abstractTOC_keyword"
        },
        {
            "source": "languageCode",
            "dest": "languageCode_keyword"
        },
        {
            "source": "name",
            "dest": "name_keyword"
        },
        {
            "source": "role",
            "dest": "role_keyword"
        },
        {
            "source": "originPlace",
            "dest": "originPlace_keyword"
        },
        {
            "source": "dateIssued",
            "dest": "dateIssued_keyword"
        },
        {
            "source": "dateCreated",
            "dest": "dateCreated_keyword"
        },
        {
            "source": "dateCaptured",
            "dest": "dateCaptured_keyword"
        },
        {
            "source": "copyrightDate",
            "dest": "copyrightDate_keyword"
        },
        {
            "source": "classification",
            "dest": "classification_keyword"
        },
        {
            "source": "identifier",
            "dest": "identifier_keyword"
        },
        {
            "source": "physicalLocation",
            "dest": "physicalLocation_keyword"
        },
        {
            "source": "shelfLocator",
            "dest": "shelfLocator_keyword"
        },
        {
            "source": "url",
            "dest": "url_keyword"
        },
        {
            "source": "urn",
            "dest": "urn_keyword"
        },
        {
            "source": "issuance",
            "dest": "issuance_keyword"
        },
        {
            "source": "relatedItem",
            "dest": "relatedItem_keyword"
        },
        {
            "source": "subject.topic",
            "dest": "subject.topic_keyword"
        },
        {
            "source": "subject.geographic",
            "dest": "subject.geographic_keyword"
        },
        {
            "source": "subject.temporal",
            "dest": "subject.temporal_keyword"
        },
        {
            "source": "subject.titleInfo",
            "dest": "subject.titleInfo_keyword"
        },
        {
            "source": "subject.name",
            "dest": "subject.name_keyword"
        },
        {
            "source": "subject.genre",
            "dest": "subject.genre_keyword"
        },
        {
            "source": "subject.hierarchicalGeographic.country",
            "dest": "subject.hierarchicalGeographic.country_keyword"
        },
        {
            "source": "subject.hierarchicalGeographic.continent",
            "dest": "subject.hierarchicalGeographic.continent_keyword"
        },
        {
            "source": "subject.hierarchicalGeographic.province",
            "dest": "subject.hierarchicalGeographic.province_keyword"
        },
        {
            "source": "subject.hierarchicalGeographic.region",
            "dest": "subject.hierarchicalGeographic.region_keyword"
        },
        {
            "source": "subject.hierarchicalGeographic.state",
            "dest": "subject.hierarchicalGeographic.state_keyword"
        },
        {
            "source": "subject.hierarchicalGeographic.territory",
            "dest": "subject.hierarchicalGeographic.territory_keyword"
        },
        {
            "source": "subject.hierarchicalGeographic.county",
            "dest": "subject.hierarchicalGeographic.county_keyword"
        },
        {
            "source": "subject.hierarchicalGeographic.city",
            "dest": "subject.hierarchicalGeographic.city_keyword"
        },
        {
            "source": "subject.hierarchicalGeographic.citySection",
            "dest": "subject.hierarchicalGeographic.citySection_keyword"
        },
        {
            "source": "subject.hierarchicalGeographic.island",
            "dest": "subject.hierarchicalGeographic.island_keyword"
        },
        {
            "source": "subject.hierarchicalGeographic.area",
            "dest": "subject.hierarchicalGeographic.area_keyword"
        },
        {
            "source": "subject.hierarchicalGeographic.extraterrestrialArea",
            "dest": "subject.hierarchicalGeographic.extraterrestrialArea_keyword"
        },
        {
            "source": "*",
            "dest": "keyword"
        }
    ]
}}' http://localhost:8983/solr/librarycloud/schema
