package edu.harvard.lib.lcloud;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
*
* ErrorItem creates a java object providing useful error info that can be formatted and
* thrown as an exception to clients; idea taken from: 
* http://slackspace.de/articles/jersey-how-to-provide-meaningful-exception-messages/
* 
*/

@XmlRootElement(name = "error")
@XmlType(propOrder={"status", "message","moreInfo"})
public class ErrorItem {

    private Integer status;
    private String message;
    private String moreInfo;
    
    ErrorItem() {}

    public ErrorItem(Integer status, String message, String moreInfo) {
        this.status = status;
        this.message = message;
        this.moreInfo = moreInfo;
    }
       
        public Integer getStatus() {
        	return status;
        }
        
        public void setStatus(Integer status) {
        	this.status = status;
        }
  
        public String getMessage() {
        	return message;
        }
        
        public void setMessage(String message) {
        	this.message = message;
        }        
        
        public String getMoreInfo() {
        	return moreInfo;
        }
        
        public void setMoreInfo(String moreInfo) {
        	this.moreInfo = moreInfo;
        }     

}

