package facade;

import java.util.HashMap;

import com.graphhopper.routing.util.FlagEncoder;
import com.graphhopper.util.PMap;

import edu.napier.geo.common.Journey;
import edu.napier.geo.common.JourneyFactory;
import edu.napier.geo.common.Location;

/**
 * Facade class for the simplified use of the GraphHopper routing engine
 * 
 * Possibilities:
 * - create a Journey that GH can handle (call getOptionsMap() first)
 * - process the Journey object
 * - create your own routing profile (e.g. bus)
 * - create your own weighting for routing (e.g. eco efficient) - edit CustomWeighting
 *  
 * @author Jannik Enenkel
 */


public class GHFacade implements GHOptions, JourneyFactory {

	private HashMap<String, Object> optionsMap;
	private JourneyFactory _alternative;
	
	public GHFacade() {
		optionsMap = new HashMap<String, Object>();
		optionsMap.put("pathToOSM", null); // String
		optionsMap.put("pathToFolder", null); // String
		optionsMap.put("profilesForGraph", null); // FlagEncoder[] from getEncoder()
		optionsMap.put("enableCH", null); // boolean
		optionsMap.put("maxVisitedNodes", null); // int
		optionsMap.put("includeElevation", null); // boolean
		optionsMap.put("algorithm", null); // String
		optionsMap.put("profileForRequest", null); // String equal to FlagEncoder.toString()
		optionsMap.put("weighting", null); // String
	}

	/**
	 * @return hashmap with all necessary options that must be defined to use GraphHopper on a GHJourney
	 */
	public HashMap<String, Object> getOptionsMap() {
		return optionsMap;
	}



	/**
	 * @param start: Location where the journey should start
	 * @param finish: Location where the journey should finish
	 * @param options: use getOptionsMap() to get a hashmap that this method can use
	 * @return GHJourney object necessary for routing with GraphHopper
	 */

	public GHJourney setupJourney(Location start, Location finish, HashMap<String, Object> options) {
		if (options.containsKey("pathToOSM") && options.containsKey("pathToFolder")
				&& options.containsKey("profilesForGraph") && options.containsKey("enableCH")
				&& options.containsKey("maxVisitedNodes") && options.containsKey("includeElevation")
				&& options.containsKey("profileForRequest") && options.containsKey("algorithm")
				&& options.containsKey("weighting")) {
			GHJourney j = new GHJourney(start, finish);
			j.setOptions(options);
			return j;
		} else
			throw new IllegalArgumentException("options parameter not ok");
	}

	/**
	 * @param j: GHJourney object created with getJourney()
	 * @return GHJourney that was given to the method but with its routing information
	 */
	public GHJourney route(GHJourney j) {
		GHRouting r = new GHRouting(j);
		//?
		r.processJourney();
		return j;
	}

	/**
	 * @param name: the name of the routing profile
	 * @return routing profile for the use with GraphHopper. If custom[...], the profile needs additional information
	 */
	public FlagEncoder getEncoder(String name) {
		MyFlagEncoderFactory factory = new MyFlagEncoderFactory();
		return factory.createFlagEncoder(name, new PMap());
	}

	@Override
	public Journey[] getJourney(Location start, Location finish) {
		// TODO Auto-generated method stub
		return getJourney(start, finish, optionsMap);
	}

	@Override
	public Journey[] getJourney(Location start, Location finish, HashMap<String, Object> options) {
		Journey res[] = new Journey[1];
		try{

			GHJourney journey = this.setupJourney(start, finish,  options);
			res[0] = this.route(journey);
			res[0].getDistanceKM();//Force runtime error if GH has failed
			

		}catch(Exception e){
			//Try alternative
			if (_alternative != null){
				return _alternative.getJourney(start, finish, options);
			}
			return null;
		}
		return res;
	}

	@Override
	public void setAlternative(JourneyFactory alternative) {
		_alternative = alternative;
		
	}

}
