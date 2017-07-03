package edu.napier.geo.examples;

import java.awt.Color;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import com.graphhopper.routing.util.FlagEncoder;

import edu.napier.geo.common.Journey;
import edu.napier.geo.common.JourneyFactory;
import edu.napier.geo.common.Location;
import edu.napier.geo.common.MultiLegJourney;
import edu.napier.geo.common.OSMFileUtils;
import edu.napier.geo.common.SimpleRouter;
import edu.napier.geo.common.Useful;
import edu.napier.geo.diary.CalendarEntry;
import edu.napier.geo.diary.DiaryFacade;
import edu.napier.geo.easykml.KML_Object.stylesector.Style;
import edu.napier.geo.easykml.KML_Object.subStyle.colorStyle.LineStyle;
import edu.napier.geo.easykml.facade.EasyKMLCreator;
import edu.napier.geo.queryOsmAPI.osmFacade.QueryOsmFacade;
import edu.napier.geo.tfl.TransportForLondonFacade;
import facade.GHFacade;


public class CombinedExample {

	public static void main(String[] args) {
		
		// This example shows how to bring together the various parts of NGT
		
		//Find some areas of interest in Central London
		
		Location currentLocation = new Location(51.507115,-0.127437);
		
		OSMFileUtils.setOsmFilter("/usr/local/Cellar/osmfilter/0.7/bin/osmfilter ");
		OSMFileUtils.extractData(currentLocation, "greater-london-latest.osm.pbf", "centralLondon.osm",true);
		
		QueryOsmFacade facade = new QueryOsmFacade();
		
		facade.parseOSMFile("centralLondon.osm");
		//need to be able to get OSM data
		
		System.out.println("Found: ");
		ArrayList <Location> pubs= facade.findLocationsWithRegEx("pub", currentLocation);
		//Need to add more details to location
		for(Location l : pubs){
			System.out.println(l);
		}
		
		
		EasyKMLCreator kml = new EasyKMLCreator();
		kml.addPlacemarks(pubs);
		
		
		JourneyFactory router = new SimpleRouter(20);	
		ArrayList<Location> ordered = nearestNeighbour(currentLocation, pubs,router);
		
		//Change router
		
	    router = new TransportForLondonFacade("You need to obtain a TfL API key for here....");
	    JourneyFactory gh = setupHopper();
	    router.setAlternative(gh);
	    gh.setAlternative(new SimpleRouter(20));

		//Create journey
		MultiLegJourney trip = new MultiLegJourney();
		
		Location prev = currentLocation;
		for (Location next : ordered){
			Journey[] myJourney = router.getJourney(prev, next);
			
			trip.addLeg(myJourney[0]);
			prev = next;
		}
		
		kml.addJourney(trip);
		
		try{
			kml.saveKMLDocument("london1.kml");
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("Error saving KML");
			}
		
		//Create a schedule of calendar events
		DiaryFacade schedule = new DiaryFacade();
		schedule.deleteAllEvents();//Remove any previous events loaded from file
		
		try{
		int count = 0;
		LocalDateTime myTime = LocalDateTime.of(2017, 6, 8, 11, 00);
		
		for (Journey j : trip.getLegs()){
			String description = "Visit " + count;
			if (count==0) description = "Start";
			LocalDateTime leave = myTime.plusMinutes(15);
			CalendarEntry ce = schedule.createCalendarEntry(myTime, leave, description, j.getPointA().getDescription(), j.getPointA(), null);	
			j.putAttribute(Journey.FROM_CALENDAR_ENTRY, ce);
			int s = (int)j.getTravelTimeMS()/1000;
			myTime = leave.plusSeconds(s);
			count++;
			}
		}catch(Exception e){
			System.out.println(e);
		}
		System.out.println("My Trip \n" );
		//schedule.printAllCalendarEntries();
		
		//Show directions
		for (Journey j : trip.getLegs()){
			System.out.println("Appointment " + j.getAttribute(Journey.FROM_CALENDAR_ENTRY));
			System.out.println("Journey:  From " + j.getPointA().getDescription());
			System.out.println("To " + j.getPointB().getDescription());
			System.out.println("Instructions : " + j.getAttribute(Journey.INSTRUCTIONS));
			System.out.println("Journey data source : " + j.getSource());
		}
		
	}

	private static JourneyFactory setupHopper(){
		//Create a GraphHopper Facade
				GHFacade gh = new GHFacade();
				//Setup GraphHopper using the optionsMap	
				String osmfile = "greater-london-latest.osm.pbf";
				String folderPath = "./osmdataLon/";

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
				
				return gh;
	}
	
	private static ArrayList<Location> nearestNeighbour(
			Location currentLocation, ArrayList<Location> pubs,
			JourneyFactory router) {
		//Sort into nearest neighbor order from currentLocation
		ArrayList<Location> ordered  = new ArrayList<Location>();
		Location current = currentLocation;
		Journey[] res;
		while(pubs.size() > 0){
			double bestD = Double.MAX_VALUE;
			Location next = null;
			for (Location possible: pubs){
				res = router.getJourney(current, possible);
				double tempD = Double.MAX_VALUE;
				if (res != null)
					tempD = res[0].getDistanceKM();
				
				if (tempD < bestD){
					bestD = tempD;
					next = possible;
				}
			}
			ordered.add(next);
			pubs.remove(next);
			current = next;
		}
		return ordered;
	}

}
