package facade;

import java.util.HashMap;
import java.util.List;

import edu.napier.geo.common.Journey;
import edu.napier.geo.common.Location;

/**
 * Journey class for the use with GraphHopper
 * 
 * @author Jannik Enenkel
 *
 */

public class GHJourney extends Journey {
	
	private HashMap<String, Object> options;
	//protected List<Location> waypoints; // contains routing information

	public GHJourney(Location a, Location b) {
		super(a, b,"GraphHopper");
	}

	
	/**
	 * 
	 * @param options the options map
	 */
	public void setOptions(HashMap<String, Object> options){
		this.options = options;
		
	}
	
	/**
	 * 
	 * @return the options map
	 */
	public HashMap<String, Object> getOptions(){
		return options;
	}		
	
	/**
	 * 
	 * @return a list of locations that specify the nodes between start and finish
	 */
	//public List<Location> getWaypoints() {
	//	return waypoints;
//	}

	/**
	 * 
	 * @param waypoints set the waypoints list
	 */
	//public void setWaypoints(List<Location> waypoints) {
	//	this.waypoints = waypoints;
	//}

}
