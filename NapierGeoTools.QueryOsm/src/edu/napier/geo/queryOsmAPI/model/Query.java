package edu.napier.geo.queryOsmAPI.model;

/** 
 * @author Johannes Nguyen 
 * A class to conduct the queries
 */
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import edu.napier.geo.common.Location;
import edu.napier.geo.queryOsmAPI.osmFacade.OSMLocation;


public class Query {
	BoundingBox range;
	String tagForSearch;
	double radius; 							//in kilometers
	Location currentLocation;
	OSMParser parser;
	BoundingBox box;
	
	/**
	 * Constructor
	 * @param tagForSearch A keyword (or tag) for the search
	 * @param currentLocation Starting Location as the center of the search radius
	 * @param parser Parser object that contains the created Java objects after parsing an OSM file
	 */
	public Query (String tagForSearch, Location currentLocation, OSMParser parser){
		this.radius = 5.0;					//default 5km
		this.tagForSearch = tagForSearch;
		this.currentLocation = currentLocation;
		this.parser = parser;
	}
	
	/**
	 * Constructor
	 * @param radius Search radius in kilometres
	 * @param tagForSearch A keyword (or tag) for the search
	 * @param currentLocation Starting Location as the center of the search radius
	 * @param parser Parser object that contains the created Java objects after parsing an OSM file
	 */
	public Query (double radius, String tagForSearch, Location currentLocation, OSMParser parser){
		this.radius = radius;
		this.tagForSearch = tagForSearch;
		this.currentLocation = currentLocation;
		this.parser = parser;
	}
	
	/**
	 * Constructor
	 * @param box A BoundingBox Object that defines the corners of a rectangle-shaped area 
	 * @param tagForSearch A keyword (or tag) for the search
	 * @param parser Parser object that contains the created Java objects after parsing an OSM file
	 */
	public Query (BoundingBox box, String tagForSearch, OSMParser parser){
		this.box = box;
		this.tagForSearch = tagForSearch;
		this.parser = parser;
	}
    
	/**
	 * Method that performs a query within a search radius
	 * @return ArrayList containing found locations
	 */
	public ArrayList <Location> conductQuery(){
		ArrayList <Location> locationList = new ArrayList <Location>();
		TreeMap<String, ArrayList<String>> tagList = parser.getTagList().getListOfTags();
		Map<String,Element> nodeList = parser.getElements();
		
		for( String s : tagList.get(tagForSearch)){
			Node n = (Node) nodeList.get(s);
			OSMLocation l = new OSMLocation (n);
			Boolean withinRange = withinRange(radius, currentLocation, n);
			if (withinRange == true)
			locationList.add(l);
			
		}
		
		return locationList;
	}
	
	/**
	 * Method that performs a query within a rectangle-shaped area 
	 * @param box A BoundingBox Object that defines the corners of a rectangle-shaped area 
	 * @return ArrayList containing found locations
	 */
	public ArrayList <Location> conductQueryWithBoundingBox(BoundingBox box){
		ArrayList <Location> locationList = new ArrayList <Location>();
		TreeMap<String, ArrayList<String>> tagList = parser.getTagList().getListOfTags();
		Map<String,Element> nodeList = parser.getElements();
		
		for( String s : tagList.get(tagForSearch)){
			Node n = (Node) nodeList.get(s);
			OSMLocation l = new OSMLocation (n);
			Boolean withinRange = withinBoundingBox(box, n);
			if (withinRange == true)
			locationList.add(l);
			
		}
		
		return locationList;
	}
	
	/**
	 * Method that checks whether a node is within the specified search radius
	 * @param radius Search radius in kilometres
	 * @param currentLocation Starting Location as the center of the search radius
	 * @param node A java node object  
	 * @return A boolean whether a node is within the specified search radius
	 */
	public Boolean withinRange(double radius, Location currentLocation, Node node){
		// calculation using Haversine formula
		double lat1 = currentLocation.getLat();
		double lon1 = currentLocation.getLon();
		double lat2 = node.getLat();
		double lon2 = node.getLon();
		
		double distance = haversine(lat1, lon1, lat2, lon2);
		System.out.println("Distance between currentLocation and foundLocation: "+distance + " NodeID: "+node.getId());
		
		if (distance <= radius)
			return true;
		
		else 
			return false;
	}
	

	 public static final double R = 6372.8; // In kilometers
	 /**
	  * Harversine distance calculation
	  * @param lat1 latitude of position 1
	  * @param lon1 longitude of position 1
	  * @param lat2 latitude of position 2
	  * @param lon2 longitude of position 2
	  * @return distance between two positions
	  */
	 public static double haversine(double lat1, double lon1, double lat2, double lon2) {
	        double dLat = Math.toRadians(lat2 - lat1);
	        double dLon = Math.toRadians(lon2 - lon1);
	        lat1 = Math.toRadians(lat1);
	        lat2 = Math.toRadians(lat2);
	 
	        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
	        double c = 2 * Math.asin(Math.sqrt(a));
	        return R * c;
	    }
	 
	 /**
	  * Method that checks whether a node is within the rectangle-shaped area
	  * @param box A BoundingBox Object that defines the corners of a rectangle-shaped area 
	  * @param node A java node object   
	  * @return A boolean whether a node is within the rectangle-shaped area 
	  */
 public Boolean withinBoundingBox (BoundingBox box, Node node ){
	 OSMLocation nodeLocation = new OSMLocation(node);
	 if(nodeLocation.getLat() <=  box.maxlat && nodeLocation.getLat()>= box.getMinlat() 
			 && nodeLocation.getLon()<= box.getMaxlon() && nodeLocation.getLon()>= box.getMinlon())
		 return true;
	 
	 else
		 return false;
 }
	
	public BoundingBox getRange() {
		return range;
	}

	public void setRange(BoundingBox range) {
		this.range = range;
	}

	public String getSearchForTag() {
		return tagForSearch;
	}

	public void setSearchForTag(String searchForTag) {
		this.tagForSearch = searchForTag;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public Location getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Location currentLocation) {
		this.currentLocation = currentLocation;
	}

	
	
}
