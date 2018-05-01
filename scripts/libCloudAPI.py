# 
# Parsing Harvard's LibraryCloud API for recordIdentifiers
#
import urllib.request 
import xml.dom.minidom

def printIDs(doc):
  # get mods:mods records
  modsRecords = doc.getElementsByTagName("mods:mods")
  # loop through each mods:mods record
  for modsRecord in modsRecords:
    # get mods:recordIdentifiers in modsRecord
    recordIdentifiers = modsRecord.getElementsByTagName("mods:recordIdentifier")
    # loop through each mods:recordIdentifier
    for recordIdentifier in recordIdentifiers:
      # return recordIdentifiers only with source of: VIA, ALEPH, or OASIS 
      if recordIdentifier.getAttribute("source") in ("MH:VIA","MH:ALEPH","MH:OASIS"):
        # set recordIdentifier as first thing to print in results 
        results = recordIdentifier.firstChild.nodeValue
        # get typeOfResource elements
        collectionRecords = modsRecord.getElementsByTagName("mods:typeOfResource")
        # loop through typeOfResource elements to identify which are collection records
        for collectionRecord in collectionRecords:
            # get typeOfResource elements with attribute collection="yes"
            if collectionRecord.getAttribute("collection") == "yes":
              results = results + "\tCollection Record"
            else: 
              results = results + "\t"
        # get mods:url elements
        modsURLs = modsRecord.getElementsByTagName("mods:url")
        rawObject = 0
        preview = 0
        # loop through modsURL elements
        for modsURL in modsURLs:
          # check for raw objects, set to 1 if any found
          if modsURL.getAttribute("access") == "raw object":
            rawObject = 1
          # check for previews, set to 1 if any found
          if modsURL.getAttribute("access") == "preview":
            preview = 1
        # if no rawObject found:
        if rawObject == 0:
          # print "NOT DIGITIZED"
          results = results + "\tNOT DIGITIZED"
        else: 
          results = results + "\t"
        # if a rawObject is found but no preview is found:
        if preview == 0 and rawObject == 1:
          # print "NO THUMBNAIL"
          results = results + "\tNO THUMBNAIL"
        else: 
          results = results + "\t"
          
        print (results)

def main():
  # open file of catalog IDs to search for in API, 
  # IDs need to be one per line and formatted correctly before running the script:
  #     ALEPH: 9 digits with leading zeros
  #     VIA: Wxxxx_ or Sxxxx_ 
  f=open("harvestFile.txt", "r")
  if f.mode == 'r':
    content = f.readlines()
    # remove whitespace characters like `\n` at the end of each line
    content = [x.strip() for x in content]

  for x in content:
    # define a variable to hold the source URL
    urlData = "https://api.lib.harvard.edu/v2/items?"
    # add recordIdentifier=ID* and increase limit to 250
    urlData = urlData + "recordIdentifier=" + x + "*" + "&limit=250"

    # open the URL and read the data
    webUrl = urllib.request.urlopen(urlData)
    # print ("result code: " + str(webUrl.getcode()))
    if (webUrl.getcode() != 200):
      print ("Received an error from server, cannot retrieve results " + str(webUrl.getcode()))
    
    # put urlData in DOM
    doc = xml.dom.minidom.parse(urllib.request.urlopen(urlData))
    # print results
    printIDs (doc)


if __name__ == "__main__":
  main()
