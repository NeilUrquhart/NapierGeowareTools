package facade;

/**
 * Contains most important options for the use of GraphHopper.
 * Use these Strings to fill the optionsMap
 * 
 * @author Jannik Enenkel
 *
 */
public interface GHOptions {
	
	// routing profiles
	final String CAR = "car";
	final String BIKE = "bike";
	final String CAR4WD = "car4wd";
	final String MOUNTAINBIKE = "mtb";
	final String MOTORCYCLE = "motorcycle";
	final String RACINGBIKE = "racingbike";
	final String HIKE = "hike";
	final String FOOT = "foot";
	final String BIKE2 = "bike2";
	final String CUSTOMCAR = "customCar";
	final String CUSTOMBIKE = "customBike";
	final String CUSTOMFOOT = "customFoot";
	
	// weightings
	final String FASTEST = "fastest";
	final String SHORTEST = "shortest";
	final String CURVATURE = "curvature"; //not supported in version 0.8
	final String SHORT_FASTEST = "short_fastest";
	final String CUSTOM = "customWeighting";
	
	// algorithms
	final String DIJKSTRA = "dijkstra";
	final String DIJKSTRABI = "dijkstrabi"; // can handle contraction hierarchies
	final String DIJKSTRA_ONE_TO_MANY = "dijkstra_one_to_many";
	final String ASTAR = "astar";
	final String ASTARBI = "astarbi"; // can handle contraction hierarchies
	final String ROUND_TRIP = "round_trip";
	final String ALTERNATIVE_ROUTE = "alternative_route";
}
