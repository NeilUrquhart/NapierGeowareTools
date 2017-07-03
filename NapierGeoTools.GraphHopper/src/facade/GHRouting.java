package facade;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.graphhopper.GHRequest;
import com.graphhopper.GHResponse;
import com.graphhopper.PathWrapper;
import com.graphhopper.routing.util.EncodingManager;
import com.graphhopper.routing.util.FlagEncoder;
import com.graphhopper.util.Instruction;
import com.graphhopper.util.InstructionList;
import com.graphhopper.util.PointList;
import com.graphhopper.util.Translation;
import com.graphhopper.util.TranslationMap;
import com.graphhopper.util.shapes.GHPoint3D;

import edu.napier.geo.common.Journey;
import edu.napier.geo.common.Location;

/**
 * handles GraphHopper, especially routing
 * 
 * @author Jannik Enenkel
 */

public class GHRouting {

	private CustomGraphHopper hopper;
	private GHJourney journey;
	
	public GHRouting(GHJourney journey){
		this.journey = journey;
	}
	
	public GHJourney getJourney() {
		return journey;
	}

	public void setJourney(GHJourney journey) {
		this.journey = journey;
	}
	
	/**
	 * the steps to calculate a route
	 * 
	 * @return GHJourney with routing information
	 */
	public GHJourney processJourney(){
		selectOSMFile();
		setGHOptions();
		hopper.importOrLoad();
		makeRequest();
		hopper.close();
		return journey;
	}
	
	/**
	 * reads in the OSM file and specifies the location for local storage
	 */
	private void selectOSMFile() {
		hopper = new CustomGraphHopper();
		hopper.forDesktop();
		
		String pathToOSM = (String) journey.getOptions().get("pathToOSM");
		hopper.setDataReaderFile(pathToOSM);
		
		String pathToFolder = (String) journey.getOptions().get("pathToFolder");
		hopper.setGraphHopperLocation(pathToFolder);
	}

	/**
	 * Most important graphhopper options. Must be defined before generating the graph. 
	 */
	private void setGHOptions() {
		FlagEncoder[] fe = (FlagEncoder[]) journey.getOptions().get("profilesForGraph");
		try{
		hopper.setEncodingManager(new EncodingManager(fe));
		}catch(IllegalStateException ex){
			System.out.println("Attemopted to reguster mode twice : Ignored");
		}
		
		boolean enableCH = (boolean) journey.getOptions().get("enableCH");
		hopper.getCHFactoryDecorator().setEnabled(enableCH);
		
		int maxVisitedNodes = (int) journey.getOptions().get("maxVisitedNodes");
		if (!enableCH)			
			hopper.setMaxVisitedNodes(maxVisitedNodes);
		
		boolean includeElevation = (boolean) journey.getOptions().get("includeElevation");
		hopper.setElevation(includeElevation);
	}

	/**
	 * calculates the route. 
	 */
	private void makeRequest() {
		GHRequest req = getRequest();
		
		String profile = (String) journey.getOptions().get("profileForRequest");
		req.setVehicle(profile);
		
		String algorithm = (String) journey.getOptions().get("algorithm");
		req.setAlgorithm(algorithm);
		
		String weighting = (String) journey.getOptions().get("weighting");
		req.setWeighting(weighting);

		GHResponse rsp = getGHResponse(req);

		PathWrapper bestPath = rsp.getBest();

		journey.setTravelTimeMS(bestPath.getTime());
		journey.setDistanceKM(bestPath.getDistance() / 1000);
		journey.putAttribute(Journey.PATH, getWaypoints(bestPath));
		InstructionList il = bestPath.getInstructions();
		
		TranslationMap SINGLETON = new TranslationMap().doImport();
        Translation esTR = SINGLETON.getWithFallBack(Locale.UK);
        ArrayList<String> instructions = new ArrayList<String>();
            for(Instruction instruction : il) {
                instructions.add(instruction.getTurnDescription(esTR));
            }
            journey.putAttribute(Journey.INSTRUCTIONS, instructions);

		
		
		
	}

	/**
	 * helper class for using a Journey object to make a request
	 */
	private GHRequest getRequest() {
		double latFrom = journey.getPointA().getLat();
		double lonFrom = journey.getPointA().getLon();
		double latTo = journey.getPointB().getLat();
		double lonTo = journey.getPointB().getLon();

		return new GHRequest(latFrom, lonFrom, latTo, lonTo);
	}

	/**
	 * creates the response and handles possible errors in it.
	 */
	private GHResponse getGHResponse(GHRequest req) {
		GHResponse rsp = hopper.route(req);
		if (rsp.hasErrors()) {
			List<Throwable> errors = rsp.getErrors();
			for (Throwable t : errors)
				System.out.println(t.getMessage());
		}
		return rsp;
	}

	/**
	 * converts graphhopper points to location objects
	 */
	private List<Location> getWaypoints(PathWrapper path) {
		List<Location> wp = new ArrayList<Location>();
		PointList points = path.getPoints();
		for (GHPoint3D p : points) {
			wp.add(new Location(p.getLat(), p.getLon(), p.getEle()));
		}
		return wp;
	}
}
