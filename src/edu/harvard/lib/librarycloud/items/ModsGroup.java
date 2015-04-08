package edu.harvard.lib.librarycloud.items;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import gov.loc.mods.v3.ModsType;

public class ModsGroup {
	
	private List<ModsType> items;
	
	public ModsGroup() {
	}
	
	@XmlElement(name = "mods", namespace="http://www.loc.gov/mods/v3")
	public List<ModsType> getItems() {
		return items;
	}
	
	public void setItems(List<ModsType> items) {
		this.items = items;
	}	
}
