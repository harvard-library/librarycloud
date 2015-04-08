package edu.harvard.lib.librarycloud.items;

import org.dublincore.Metadata;

import javax.xml.bind.annotation.XmlElement;

public class DcItem {
	
	public DcItem () {
		
	}

	private Metadata metadata;
	
	@XmlElement(name = "oai_dc", namespace = "http://www.openarchives.org/OAI/2.0/oai_dc/")
	public Metadata getMetadata() {
		
		return metadata;
	}
	
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

}