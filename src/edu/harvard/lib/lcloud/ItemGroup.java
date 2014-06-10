package edu.harvard.lib.lcloud;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;

public class ItemGroup {
	
	private List<Item> items;
	
	public ItemGroup() {
		
	}
	
	@XmlElement(name = "item")
	public List<Item> getItems() {
		return items;
	}
	
	public void setItems(List<Item> items) {
		this.items = items;
	}	

}
