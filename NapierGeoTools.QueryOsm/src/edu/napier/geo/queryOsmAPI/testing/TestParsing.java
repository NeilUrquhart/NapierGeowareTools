package edu.napier.geo.queryOsmAPI.testing;

import edu.napier.geo.queryOsmAPI.model.Element;
import edu.napier.geo.queryOsmAPI.model.OSMParser;
import edu.napier.geo.queryOsmAPI.osmFacade.QueryOsmFacade;

/** 
 * @author Johannes Nguyen 
 * A class thats tests the parsing function using a selfmade OSM file
 */
public class TestParsing {
	public static void main(String[] args) {

		System.out.println("___________________________");
		QueryOsmFacade facade = new QueryOsmFacade();
		facade.parseOSMFile("src/edu/napier/geo/queryOsmAPI/testing/SelfmadeOsmForTestParsing.osm");
		OSMParser parser1 = facade.getParser();
		
		System.out.println("Number of Elements added to the list: "+ parser1.getElements().size());
		parser1.printStatistics(parser1.getElements());
		
		
		System.out.println("___________________________");
		System.out.println("parsed Tags in the list:");
		facade.printTagsInConsole();
	}
}
