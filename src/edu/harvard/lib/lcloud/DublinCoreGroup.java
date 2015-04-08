package edu.harvard.lib.lcloud;

import org.dublincore.Metadata;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

public class DublinCoreGroup {
	private List<Metadata> items;
	
	public DublinCoreGroup() {
	}
	
	@XmlElement(name = "dc", namespace="http://www.openarchives.org/OAI/2.0/oai_dc/")
	public List<Metadata> getItems() {
		return items;
	}
	
	public void setItems(List<Metadata> items) {
		this.items = items;
	}	
}
