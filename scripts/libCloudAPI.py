# 
# Parsing Harvard's LibraryCloud API for recordIdentifiers
#
import urllib.request 
import xml.dom.minidom

def printIDs(doc):  
  # check that the record exists in the API
  # query = doc.getElementsByTagName("query")
  numFound = doc.getElementsByTagName("numFound")
  total = numFound[0].firstChild.nodeValue
  if total == "0":
    query = doc.getElementsByTagName("query")
    query = query[0].firstChild.nodeValue
    print ("NO_RECORDS_FOUND_FOR_QUERY:_" + query)
  
  # get mods:mods records
  modsRecords = doc.getElementsByTagName("mods:mods")
  # loop through each mods:mods record
  for modsRecord in modsRecords:
    # get mods:recordIdentifiers in modsRecord
    recordIdentifiers = modsRecord.getElementsByTagName("mods:recordIdentifier")
    # loop through each mods:recordIdentifier
    for recordIdentifier in recordIdentifiers:
      # return recordIdentifiers only with source of: VIA, ALEPH, or OASIS 
      if recordIdentifier.getAttribute("source") in ("MH:VIA","MH:ALEPH","MH:OASIS","MH:ALMA"):
        # set recordIdentifier as first thing to print in results 
        results = recordIdentifier.firstChild.nodeValue
        # get typeOfResource elements
        collectionRecords = modsRecord.getElementsByTagName("mods:typeOfResource")
        # loop through typeOfResource elements to identify which are collection records
        results = results + "\t"
        for collectionRecord in collectionRecords:
            # get typeOfResource elements with attribute collection="yes"
            if collectionRecord.getAttribute("collection") == "yes":
              results = results + "Collection_Record"
        # get mods:url elements
        modsURLs = modsRecord.getElementsByTagName("mods:url")
        rawObject = 0
        preview = 0
        # loop through modsURL elements
        for modsURL in modsURLs:
          # check for raw objects, set to 1 if any found
          if modsURL.getAttribute("access") == "raw object":
            rawObject = 1
          # check for previews that are NOT empty, set to 1 if any found
          if modsURL.getAttribute("access") == "preview" and modsURL.firstChild is not None:
            preview = 1
        # if no rawObject found:
        results = results + "\t"
        if rawObject == 0:
          # print "NO RAW OBJECT"
          results = results + "NO_RAW_OBJECT"
        else: 
          results = results
        # if a rawObject is found but no preview is found:
        results = results + "\t"
        if preview == 0:
          # print "NO THUMBNAIL"
          results = results + "NO_THUMBNAIL"
        else: 
          results = results

        # get HarvardDRS:accessFlag
        accessFlagCheck = "NO_VALUE_FOUND"
        accessFlags = modsRecord.getElementsByTagName("HarvardDRS:accessFlag")
        results = results + "\t"
        for accessFlag in accessFlags:
            accessFlagCheck = accessFlag.firstChild.nodeValue
        results = results + accessFlagCheck 
          
        print (results)

def main():
  # open file of catalog IDs to search for in API, 
  # IDs need to be one per line and formatted correctly before running the script:
  #     ALEPH: 9 digits with leading zeros
  #     VIA: Wxxxx_ or Sxxxx_ 
  f=open("harvestFile.txt", "r")

  #print header row
  print ("RecordID\tCollection?\tRaw?\tThumb?\tAccessFlag")

  if f.mode == 'r':
    content = f.readlines()
    # remove whitespace characters like `\n` at the end of each line
    content = [x.strip() for x in content]

  for x in content:
    # define a variable to hold the source URL
    urlData = "https://api.lib.harvard.edu/v2/items?"
    # add recordIdentifier=ID* and increase limit to 250
    
    # use for all records except JSTOR Forum URNs
    urlData = urlData + "recordIdentifier=" + x + "*" + "&limit=250"
    
    # use for JSTOR Forum URNs
    # urlData = urlData + "q=" + x + "&limit=250"

    # use for searching strings rather than IDs
    # urlData = urlData + x

    # open the URL and read the data
    req = urllib.request.Request(urlData, headers={'User-Agent': 'Mozilla/5.0'})
    webUrl = urllib.request.urlopen(req)
    # print ("result code: " + str(webUrl.getcode()))
    if (webUrl.getcode() != 200):
      print ("Received an error from server, cannot retrieve results " + str(webUrl.getcode()))
      
    # put urlData in DOM
    doc = xml.dom.minidom.parse(webUrl)
    
    # print results
    printIDs (doc)


if __name__ == "__main__":
  main()