package edu.napier.geo.examples;
import java.util.HashMap;

import edu.napier.geo.common.Journey;
import edu.napier.geo.common.JourneyFactory;
import edu.napier.geo.common.Location;
import facade.GHFacade;
import facade.GHJourney;
import facade.CustomCarFlagEncoder;
import facade.GHOptions;

import com.graphhopper.routing.util.BikeFlagEncoder;
import com.graphhopper.routing.util.FlagEncoder;

public class GraphHopper_Example {
	public static void main(String[] args){
		//Create some locations
		
		Location merchiston = new Location(55.933326,-3.213896,0.0);
		merchiston.setDescription("Edinburgh Napier University, Merchiston Campus");
		merchiston.setSource("NeilU");
				
		Location sighthill = new Location(55.924959,-3.289067,0.0);
		merchiston.setDescription("Edinburgh Napier University, Merchiston Campus");
		merchiston.setSource("NeilU");
				
		//Create a GraphHopper Facade
		GHFacade gh = new GHFacade();
		//Setup GraphHopper using the optionsMap	
		String osmfile = "edinburgh_scotland.osm";
		String folderPath = "./osmdata2/";

		FlagEncoder car = gh.getEncoder(gh.CAR);
		FlagEncoder bike = gh.getEncoder(gh.BIKE);
		
		FlagEncoder[] encoders = {car,bike};
		HashMap<String , Object> options = gh.getOptionsMap();
		options.put("pathToOSM", osmfile);
		options.put("pathToFolder", folderPath);
		options.put("profilesForGraph",encoders);
		options.put("profilesForRequest", car);
		options.put("enableCH",false);
		options.put("maxVisitedNodes",100000);
		options.put("includeElevation", false);
		
		
		
		//Now demonstrate using the Factory....
		JourneyFactory jf = gh;
		
		options.put("algorithm",gh.DIJKSTRABI);
		Journey[] aJourney = jf.getJourney(merchiston, sighthill, options);
		System.out.println("J1 =" + aJourney[0] + "Dist =" + aJourney[0].getDistanceKM());
		
		options.put("algorithm",gh.ASTAR);
		aJourney = jf.getJourney(merchiston, sighthill,options);
		System.out.println("J2 = "+ aJourney[0] + "Dist =" + aJourney[0].getDistanceKM());
		
		//Now alter mode
		
		aJourney = jf.getJourney(merchiston, sighthill, options);
		System.out.println("J1 =" + aJourney[0] + "Dist =" + aJourney[0].getTravelTimeMS());
		
		options.put("profilesForRequest", bike);
		
		aJourney = jf.getJourney(merchiston, sighthill,options);
		System.out.println("J2 = "+ aJourney[0] + "Dist =" + aJourney[0].getTravelTimeMS());
	}
}
