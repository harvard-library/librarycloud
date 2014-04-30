package edu.harvard.lib.lcloud;

import javax.xml.bind.annotation.XmlElement;

import gov.loc.mods.v3.ModsType;

public class Item {
	
	public Item () {
		
	}

	private ModsType modsType;
	
	@XmlElement(name = "mods", namespace = "http://www.loc.gov/mods/v3")
	public ModsType getModsType() {
		
		return modsType;
	}
	
	public void setModsType(ModsType modsType) {
		this.modsType = modsType;
	}
}
