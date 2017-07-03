package edu.napier.geo.queryOsmAPI.testing;
import edu.napier.geo.queryOsmAPI.model.OSMParser;
import edu.napier.geo.queryOsmAPI.osmFacade.QueryOsmFacade;

/** 
 * @author Johannes Nguyen 
 * A class that tests the tags print function
 */
public class TestPrintTags {
	public static void main(String[] args) {

		System.out.println("___________________________");
		QueryOsmFacade facade = new QueryOsmFacade();
		facade.parseOSMFile("src/edu/napier/geo/queryOsmAPI/testing/EdinburghDowntown.osm");
		OSMParser parser1 = facade.getParser();
		
		facade.printTagsInConsole();
		//Need to refresh project explorer to see tagsOut.txt
		facade.printTagsToTxtFile("src/edu/napier/geo/queryOsmAPI/testing/tagsOut.txt"); 		
	}	
}
