package facade;

import java.util.HashSet;
import java.util.Set;

import com.graphhopper.routing.util.FootFlagEncoder;

/**
 * custom routing profile for anything similar to walking.
 * See FootFlagEncoder for possible customization.
 * http://wiki.openstreetmap.org/wiki/Map_Features contains a list of all tags.
 * 
 * @author Jannik Enenkel
 *
 */

public class CustomFootFlagEncoder extends FootFlagEncoder{

	private String name = "customFoot";
	
	public CustomFootFlagEncoder() {
		super(4, 1);
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
	
	public Set<String> getRestrictedValues(){
		return restrictedValues;
	}
	
	public Set<String> getIntendedValues(){
		return intendedValues;
	}
	
	public HashSet<String> getSidewalksNoValues(){
		return sidewalksNoValues;
	}
	
	public HashSet<String> getSidewalkValues(){
		return sidewalkValues;
	}
	
	public Set<String> getpotentialBarriers(){
		return potentialBarriers;
	}
}
