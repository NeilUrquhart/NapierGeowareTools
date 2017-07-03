package facade;

import java.util.HashSet;
import java.util.Set;

import com.graphhopper.routing.util.BikeCommonFlagEncoder;

/**
 * custom routing profile for anything similar to a bike.
 * See BikeCommonFlagEncoder for possible customization.
 * http://wiki.openstreetmap.org/wiki/Map_Features contains a list of all tags.
 * 
 * @author Jannik Enenkel
 *
 */

public class CustomBikeFlagEncoder extends BikeCommonFlagEncoder{

	private String name = "customBike";	

	public CustomBikeFlagEncoder(){
		super(4, 2, 1);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public Set<String> getRestrictedValues() {
		return restrictedValues;
	}

	public Set<String> getIntendedValues() {
		return intendedValues;
	}
	
	public HashSet<String> getOppositeLanes(){
		return oppositeLanes;
	}
	
	public Set<String> getPotentialBarriers() {
		return potentialBarriers;
	}

	public Set<String> getAbsoluteBarriers() {
		return absoluteBarriers;
	}
	
	public Set<String> getUnpavedSurfaceTags() {
		return unpavedSurfaceTags;
	}
	
	public void setMaxPossibleSpeed(int maxPossibleSpeed){
		this.maxPossibleSpeed = maxPossibleSpeed;
	}
}
