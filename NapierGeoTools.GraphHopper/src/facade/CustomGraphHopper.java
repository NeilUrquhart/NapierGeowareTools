package facade;

import com.graphhopper.reader.osm.GraphHopperOSM;
import com.graphhopper.routing.util.FlagEncoder;
import com.graphhopper.routing.util.HintsMap;
import com.graphhopper.routing.weighting.Weighting;
import com.graphhopper.storage.Graph;

/**
 * To provide custom weightings this class is used in GHRouting.
 * If you have more than one custom weighting you must add its name to the method below.
 * 
 * @author Jannik Enenkel
 *
 */

public class CustomGraphHopper extends GraphHopperOSM {

	@Override
	public Weighting createWeighting(HintsMap hintsMap, FlagEncoder encoder, Graph graph) {
		String weighting = hintsMap.getWeighting().toLowerCase();
		if (weighting.equals("customWeighting"))
			return new CustomWeighting(encoder);
//		if(weighting.equals("mySecondWeighting"))
//			return new SecondWeighting(encoder);
		else
			return super.createWeighting(hintsMap, encoder, graph);
	}
}
