curl -X POST -H 'Content-type:application/json' --data-binary '{
    "add-field": [
    {
        "name": "004",
        "type": "string",
        "indexed": true,
        "stored": true
    },
    {
        "name": "originalMarc",
        "type": "string",
        "indexed": false,
        "stored": true
    }
]}' http://localhost:8983/solr/holdings/schema
