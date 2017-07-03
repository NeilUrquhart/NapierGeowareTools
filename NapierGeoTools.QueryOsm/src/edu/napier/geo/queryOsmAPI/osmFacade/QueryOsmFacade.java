package edu.napier.geo.queryOsmAPI.osmFacade;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;



import org.xml.sax.SAXException;

import edu.napier.geo.common.Location;
import edu.napier.geo.common.Useful;
import edu.napier.geo.queryOsmAPI.model.BoundingBox;
import edu.napier.geo.queryOsmAPI.model.OSMParser;
import edu.napier.geo.queryOsmAPI.model.Query;
/** 
 * @author Johannes Nguyen, Neil Urquhart
 * A class that summarizes the most important functions of this API
 */

public class QueryOsmFacade {
	OSMParser parser = new OSMParser();
	

// The TreeMap is sorted after tags and references the corresponding node objects
	/**
	 * Method that takes an OSM file and creates all the nodes and tags as Java Objects
	 * The parser object then contains a map with all the nodes and a treemap with all the tags
	 * @param filePath Filepath of the OSM file that is to be parsed
	 */
	public void parseOSMFile (String filePath){
		
		File osmFile = new File(filePath);
		
		try {
			parser.parse(osmFile);								
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
 
	/**
	 * Method that returns a list of locations within the default radius of 5km 
	 * Before using this method it is necessary to have created a "parser" object and parse the OSM file
	 * @param tagForSearch A keyword (or tag) for the search
	 * @param currentLocation Starting Location as the center of the search radius
	 * @return ArrayList containing found locations
	 */
	public ArrayList <Location> findLocations( String tagForSearch, Location currentLocation){
		Query q = new Query (tagForSearch, currentLocation, parser);
		ArrayList <Location> locationList = q.conductQuery();
		
		return locationList;
	}
	
	/**
	 * Method that finds locations using a self defined search radius in kilometres 
	 * @param radius Search radius in kilometres 
	 * @param tagForSearch A keyword (or tag) for the search
	 * @param currentLocation Starting Location as the center of the search radius
	 * @return ArrayList containing found locations
	 */
	public ArrayList <Location> findLocationsWithRadius(Double radius, String tagForSearch, Location currentLocation){
		Query q = new Query (radius, tagForSearch, currentLocation, parser);
		ArrayList <Location> locationList = q.conductQuery();
		
		return locationList;
	}

//
	
	/**
	 * Method that finds locations within a BoundingBox
	 * @param box A BoundingBox Object that defines the corners of a rectangle-shaped area 
	 * @param tagForSearch A keyword (or tag) for the search
	 * @return ArrayList containing found locations
	 */
		public ArrayList <Location> findLocationsWithinBoundingBox (BoundingBox box, String tagForSearch){
			Query q = new Query (box, tagForSearch, parser);
			ArrayList <Location> locationList = q.conductQueryWithBoundingBox(box);
			
			return locationList;
		}
	
//Finding locations using RegEx with default radius
	/**
	 * Method that finds locations using regular expressions with the default radius of 5km
	 * @param regex Regular Expression to search for tags
	 * @param currentLocation Starting Location as the center of the search radius
	 * @return ArrayList containing found locations
	 */
	public ArrayList <Location> findLocationsWithRegEx( String regex, Location currentLocation ){
		
		//Collecting all matching tags in ArrayList
		ArrayList <String> matchingRegex = parser.getMatchingRegExStrings(parser.getTagList().getListOfTags(), regex);
		
		//Creating a super ArrayList to summarize all the found locations
		ArrayList <Location> locationList = new ArrayList <Location>();
		
		//For each tag that matches the RegEx conduct a query which each returns a list of Locations
		//then iterate through the returned list and if a location is not already included in the 
		//super ArrayList, add the following location
		for (String tagForSearch : matchingRegex){
			Query q = new Query (tagForSearch, currentLocation, parser);
			ArrayList <Location> foundLocations = q.conductQuery();
			for(Location l: foundLocations){
				if(locationList.contains(l) == false)
				locationList.add(l);
			}
		}		
		return locationList;
	}
	
//
	/**
	 * Method that finds locations using Regular Expressions with a self defined search radius
	 * @param radius Search radius in kilometres
	 * @param regex Regular Expression to search for tags
	 * @param currentLocation Starting Location as the center of the search radius
	 * @return ArrayList containing found locations
	 */
		public ArrayList <Location> findLocationsWithRegEx(Double radius, String regex, Location currentLocation){
			
			//Collecting all matching tags in ArrayList
			ArrayList <String> matchingRegex = parser.getMatchingRegExStrings(parser.getTagList().getListOfTags(), regex);
			
			//Creating a super ArrayList to summarize all the found locations
			ArrayList <Location> locationList = new ArrayList <Location>();
			
			//For each tag that matches the RegEx conduct a query which each returns a list of Locations
			//then iterate through the returned list and if a location is not already included in the 
			//super ArrayList, add the following location
			for (String tagForSearch : matchingRegex){
				Query q = new Query (radius, tagForSearch, currentLocation, parser);
				ArrayList <Location> foundLocations = q.conductQuery();
				for(Location l: foundLocations){
					if(locationList.contains(l) == false)
					locationList.add(l);
				}
			}		
			return locationList;
		}
	
		
		//Finding locations using RegEx within BoundingBox
		/**
		 * Method that finds locations using Regular Expressions within a rectangle-shaped area
		 * @param regex Regular Expression to search for tags
		 * @param box A BoundingBox Object that defines the corners of a rectangle-shaped area 
		 * @return ArrayList containing found locations
		 */
		public ArrayList <Location> findLocationsWithRegEx( String regex, BoundingBox box ){
			
			//Collecting all matching tags in ArrayList
			ArrayList <String> matchingRegex = parser.getMatchingRegExStrings(parser.getTagList().getListOfTags(), regex);
			
			//Creating a super ArrayList to summarize all the found locations
			ArrayList <Location> locationList = new ArrayList <Location>();
			
			//For each tag that matches the RegEx conduct a query which each returns a list of Locations
			//then iterate through the returned list and if a location is not already included in the 
			//super ArrayList, add the following location
			for (String tagForSearch : matchingRegex){
				Query q = new Query (box, tagForSearch, parser);
				ArrayList <Location> foundLocations = q.conductQueryWithBoundingBox(box);
				for(Location l: foundLocations){
					if(locationList.contains(l) == false)
					locationList.add(l);
				}
			}		
			return locationList;
		}	
		
		/**
		 * Method that outputs a list of existing tags to the console
		 */
		public void printTagsInConsole(){
			parser.getTagList().printTagList();
		}
		
		
		
		/**
		 * Method that outputs a list of existing tags to a txt file
		 * @param newFilePath Filepath with new filename to output and create the new txt file
		 */
		public void printTagsToTxtFile (String newFilePath){
			try {
				parser.getTagList().printTagsToTxt(newFilePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		}
		/**
		 * Method that returns a parser object containing the created Java objects after parsing an OSM file
		 * @return Parser object that contains the created Java objects after parsing an OSM file
		 */
		public OSMParser getParser() {
			return parser;
		}
	
}
