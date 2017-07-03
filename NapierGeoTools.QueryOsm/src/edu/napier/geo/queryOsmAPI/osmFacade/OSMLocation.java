package edu.napier.geo.queryOsmAPI.osmFacade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import edu.napier.geo.common.Location;
import edu.napier.geo.queryOsmAPI.model.Node;

public class OSMLocation extends Location {
	private HashMap<String, String> tags=null;
	
	
	public OSMLocation(Node n) {
		super(n.getLat(),n.getLon(), "OSM Node:" +n.getId());
		
		this.tags = (HashMap<String, String>) n.getTags();
		String description = this.tags.get("name")  ;
		this.setDescription(description);
		
	}
	
	public HashMap<String, String> getTags(){
		return tags;
	}
	
	public String toString(){
		String buffer = super.toString() + "\n";
		for(String k: tags.keySet()){
			buffer = buffer + k + ":" +tags.get(k) +"\n";
		}
		return buffer;
	}

}
