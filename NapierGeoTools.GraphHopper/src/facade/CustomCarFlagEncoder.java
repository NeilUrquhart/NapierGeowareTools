package facade;

import java.util.Map;
import java.util.Set;

import com.graphhopper.routing.util.CarFlagEncoder;

/**
 * custom routing profile for anything similar to a car. See CarFlagEncoder for
 * possible customization. http://wiki.openstreetmap.org/wiki/Map_Features
 * contains a list of all tags.
 * 
 * @author Jannik Enenkel
 *
 */

public class CustomCarFlagEncoder extends CarFlagEncoder {

	private String name = "customCar";

	public CustomCarFlagEncoder() {
		super(5, 5, 0);
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

	public Set<String> getPotentialBarriers() {
		return potentialBarriers;
	}

	public Set<String> getAbsoluteBarriers() {
		return absoluteBarriers;
	}

	public Map<String, Integer> getTrackTypeSpeedMap() {
		return trackTypeSpeedMap;
	}

	public Set<String> getBadSurfaceSpeedMap() {
		return badSurfaceSpeedMap;
	}

	public void setBadSurfaceSpeed(int badSurfaceSpeed) {
		this.badSurfaceSpeed = badSurfaceSpeed;
	}

	public void setDestinationSpeed(int destinationSpeed) {
		this.destinationSpeed = destinationSpeed;
	}

	public void setMaxPossibleSpeed(int maxPossibleSpeed) {
		this.maxPossibleSpeed = maxPossibleSpeed;
	}

	public Map<String, Integer> getDefaultSpeedMap() {
		return defaultSpeedMap;
	}

}
