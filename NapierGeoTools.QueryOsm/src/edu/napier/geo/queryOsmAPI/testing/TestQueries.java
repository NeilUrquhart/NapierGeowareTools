package edu.napier.geo.queryOsmAPI.testing;

import java.util.ArrayList;

import edu.napier.geo.common.Location;
import edu.napier.geo.queryOsmAPI.model.OSMParser;
import edu.napier.geo.queryOsmAPI.osmFacade.QueryOsmFacade;
/** 
 * @author Johannes Nguyen 
 * A class that performs examples of queries
 */
public class TestQueries {

	public static void main(String[] args) {
		//Parsing
		QueryOsmFacade facade = new QueryOsmFacade();
		facade.parseOSMFile("src/edu/napier/geo/queryOsmAPI/testing/EdinburghDowntown.osm");
		OSMParser parser1 = facade.getParser();
		Location currentLocation = new Location(55.9526497,-3.2388196);
		
	
		//Testing with default Radius without regex
		System.out.println("Testing with default radius of 5km without regex:");
		ArrayList <Location> locationsFound1 = facade.findLocations("coffee", currentLocation);
		for (Location lo : locationsFound1){
			System.out.println("coffee at: " + "lat: " + lo.getLat() + " lon: "+lo.getLon()+ " source: "+lo.getSource());
		}

		//Testing with own Radius without Regex
		System.out.println("________________________________________________________");
		System.out.println("Testing with own radius of 2km without regex:");
		ArrayList <Location> locationsFound3 = facade.findLocationsWithRadius(2.0, "coffee", currentLocation);
		for (Location lo : locationsFound3){
			System.out.println("coffee at: " + "lat: " + lo.getLat() + " lon: "+lo.getLon());
		}
		
		
		//Testing with default Radius and Regex
		System.out.println("________________________________________________________");
		System.out.println("Testing with default radius of 5km and regex:");
		ArrayList <Location> locationsFound = facade.findLocationsWithRegEx( "cof*e*", currentLocation);
		for (Location lo : locationsFound){
			System.out.println("coffee at: " + "lat: " + lo.getLat() + " lon: "+lo.getLon());
		}
		

		//Testing with own radius of 2km and regex
		System.out.println("________________________________________________________");
		System.out.println("Testing with own radius of 2km and regex:");
		ArrayList <Location> locationsFound2 = facade.findLocationsWithRegEx(2.0, "cof*e*", currentLocation);
		for (Location lo : locationsFound2){
			System.out.println("coffee at: " + "lat: " + lo.getLat() + " lon: "+lo.getLon());
		}
		
		
		//Testing within Boundingbox without regex
		System.out.println("________________________________________________________");
		System.out.println("Testing within Boundingbox without regex:");
		ArrayList <Location> locationsFound4 = facade.findLocationsWithinBoundingBox(parser1.getMapRange(), "coffee");
		for (Location lo : locationsFound4){
			System.out.println("coffee at: " + "lat: " + lo.getLat() + " lon: "+lo.getLon());
		}
		
		//Testing within Boundingbox and regex:
		System.out.println("________________________________________________________");
		System.out.println("Testing within Boundingbox and regex:");
		ArrayList <Location> locationsFound5 = facade.findLocationsWithRegEx("cof*e*", parser1.getMapRange());
		for (Location lo : locationsFound5){
			System.out.println("coffee at: " + "lat: " + lo.getLat() + " lon: "+lo.getLon());
		}
		
		
		
		
		System.out.println("________________________________________________________");
		System.out.println("Number of Tags: "+ parser1.getTagList().getListOfTags().size());
		parser1.printStatistics(parser1.getElements());
		
		
		
		
	}
	
}
