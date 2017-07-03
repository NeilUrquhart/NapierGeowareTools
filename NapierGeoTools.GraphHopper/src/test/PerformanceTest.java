package test;

import java.util.HashMap;

import com.graphhopper.routing.util.CarFlagEncoder;
import com.graphhopper.routing.util.FlagEncoder;

import edu.napier.geo.common.Location;
import facade.GHFacade;
import facade.GHJourney;

public class PerformanceTest {

	public long test1() {

		String osmFile = "C:\\Users\\Jannik\\Documents\\Uni\\6. Sem (Schottland)\\Honours Project\\Tomcat\\map (1).osm";
		String folderPath = "C:\\Users\\Jannik\\Desktop\\gh\\test1";
		Location a = new Location(55.9494, -3.1929, null);
		Location b = new Location(55.9547, -3.1937, null);

		GHFacade facade = new GHFacade();
		HashMap<String, Object> options = facade.getOptionsMap();
		FlagEncoder car = (CarFlagEncoder) facade.getEncoder(GHFacade.CAR);
		FlagEncoder[] encoders = { car };

		options.put("profilesForGraph", encoders);
		options.put("enableCH", false);
		options.put("maxVisitedNodes", 10000);
		options.put("includeElevation", true);
		options.put("algorithm", GHFacade.ASTAR);
		options.put("profileForRequest", GHFacade.CAR);
		options.put("weighting", GHFacade.FASTEST);
		options.put("pathToOSM", osmFile);
		options.put("pathToFolder", folderPath);

		GHJourney journey = (GHJourney) facade.setupJourney(a, b, options);

		long timeStart = (long) System.currentTimeMillis();
		journey = facade.route(journey);
		long timeEnd = (long) System.currentTimeMillis();
		
		System.out.println("Test 1: "+journey.getDistanceKM());
		
		return timeEnd - timeStart;
	}

	public long test2() {
		String osmFile = "C:\\Users\\Jannik\\Documents\\Uni\\6. Sem (Schottland)\\Honours Project\\Tomcat\\map (2).osm";
		String folderPath = "C:\\Users\\Jannik\\Desktop\\gh\\test2";
		Location a = new Location(55.9565, -3.2065, null);
		Location b = new Location(55.9497, -3.1798, null);
//		Location a = new Location(55.9494, -3.1929, null);
//		Location b = new Location(55.9547, -3.1937, null);

		GHFacade facade = new GHFacade();
		HashMap<String, Object> options = facade.getOptionsMap();
		FlagEncoder car = (CarFlagEncoder) facade.getEncoder(GHFacade.CAR);
		FlagEncoder[] encoders = { car };

		options.put("profilesForGraph", encoders);
		options.put("enableCH", false);
		options.put("maxVisitedNodes", 10000);
		options.put("includeElevation", true);
		options.put("algorithm", GHFacade.ASTAR);
		options.put("profileForRequest", GHFacade.CAR);
		options.put("weighting", GHFacade.FASTEST);
		options.put("pathToOSM", osmFile);
		options.put("pathToFolder", folderPath);

		GHJourney journey = (GHJourney) facade.setupJourney(a, b, options);

		long timeStart = (long) System.currentTimeMillis();
		journey = facade.route(journey);
		long timeEnd = (long) System.currentTimeMillis();
		
		System.out.println("Test 2: "+journey.getDistanceKM());
		
		return timeEnd - timeStart;
	}

	public long test3() {
		String osmFile = "C:\\Users\\Jannik\\Documents\\Uni\\6. Sem (Schottland)\\Honours Project\\Tomcat\\map (3).osm";
		String folderPath = "C:\\Users\\Jannik\\Desktop\\gh\\test3";
		Location a = new Location(55.94649, -3.21684, null);
		Location b = new Location(55.9375, -3.3652, null);
//		Location a = new Location(55.9494, -3.1929, null);
//		Location b = new Location(55.9547, -3.1937, null);

		GHFacade facade = new GHFacade();
		HashMap<String, Object> options = facade.getOptionsMap();
		FlagEncoder car = (CarFlagEncoder) facade.getEncoder(GHFacade.CAR);
		FlagEncoder[] encoders = { car };

		options.put("profilesForGraph", encoders);
		options.put("enableCH", false);
		options.put("maxVisitedNodes", 100000);
		options.put("includeElevation", true);
		options.put("algorithm", GHFacade.ASTAR);
		options.put("profileForRequest", GHFacade.CAR);
		options.put("weighting", GHFacade.FASTEST);
		options.put("pathToOSM", osmFile);
		options.put("pathToFolder", folderPath);

		GHJourney journey = (GHJourney) facade.setupJourney(a, b, options);

		long timeStart = (long) System.currentTimeMillis();
		journey = facade.route(journey);
		long timeEnd = (long) System.currentTimeMillis();
		
		System.out.println("Test 3: "+journey.getDistanceKM());
		
		return timeEnd - timeStart;
	}

	public long test4() {
		String osmFile = "C:\\Users\\Jannik\\Documents\\Uni\\6. Sem (Schottland)\\Honours Project\\Tomcat\\map (4).osm";
		String folderPath = "C:\\Users\\Jannik\\Desktop\\gh\\test4";
		Location a = new Location(55.9415, -3.2135, null);
		Location b = new Location(56.3683, -3.4305, null);
//		Location a = new Location(55.9494, -3.1929, null);
//		Location b = new Location(55.9547, -3.1937, null);

		GHFacade facade = new GHFacade();
		HashMap<String, Object> options = facade.getOptionsMap();
		FlagEncoder car = (CarFlagEncoder) facade.getEncoder(GHFacade.CAR);
		FlagEncoder[] encoders = { car };

		options.put("profilesForGraph", encoders);
		options.put("enableCH", false);
		options.put("maxVisitedNodes", 500000);
		options.put("includeElevation", true);
		options.put("algorithm", GHFacade.ASTAR);
		options.put("profileForRequest", GHFacade.CAR);
		options.put("weighting", GHFacade.FASTEST);
		options.put("pathToOSM", osmFile);
		options.put("pathToFolder", folderPath);

		GHJourney journey = (GHJourney) facade.setupJourney(a, b, options);

		long timeStart = (long) System.currentTimeMillis();
		journey = facade.route(journey);
		long timeEnd = (long) System.currentTimeMillis();
		
		System.out.println("Test 4: "+journey.getDistanceKM());
		
		return timeEnd - timeStart;
	}
}
