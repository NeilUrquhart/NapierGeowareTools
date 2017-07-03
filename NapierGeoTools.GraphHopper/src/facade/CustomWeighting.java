package facade;

import com.graphhopper.routing.util.FlagEncoder;
import com.graphhopper.routing.weighting.AbstractWeighting;
import com.graphhopper.util.EdgeIteratorState;

/**
 * This class can be modified to realize a custom weighting.
 * 
 * @author Jannik
 *
 */
public class CustomWeighting extends AbstractWeighting{

	public CustomWeighting(FlagEncoder encoder) {
		super(encoder);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getMinWeight(double distance) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double calcWeight(EdgeIteratorState edgeState, boolean reverse, int prevOrNextEdgeId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		return "customWeighting";
	}

}
