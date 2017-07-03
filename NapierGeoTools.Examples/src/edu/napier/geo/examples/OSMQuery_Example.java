package edu.napier.geo.examples;
import java.util.ArrayList;

import edu.napier.geo.common.Location;
import edu.napier.geo.common.OSMFileUtils;
import edu.napier.geo.common.Useful;
import edu.napier.geo.queryOsmAPI.model.OSMParser;
import edu.napier.geo.queryOsmAPI.osmFacade.QueryOsmFacade;


public class OSMQuery_Example {

	public static void main(String[] args) {
		
		Location currentLocation = new Location(55.9286451,-3.2093457);
		
		OSMFileUtils.setOsmFilter("/usr/local/Cellar/osmfilter/0.7/bin/osmfilter ");
		OSMFileUtils.extractData(currentLocation, "edinburgh_scotland.osm", "mSide.osm",true);
		
		QueryOsmFacade facade = new QueryOsmFacade();
		
		facade.parseOSMFile("mSide.osm");
		//need to be able to get OSM data
		
		facade.printTagsInConsole();
		System.out.println("Found: ");
		ArrayList <Location> locationsFound = facade.findLocationsWithRegEx("pub", currentLocation);
		//Need to add more details to location
		for(Location l : locationsFound){
			System.out.println(l);
		}
		
	}

	
	
	

}
