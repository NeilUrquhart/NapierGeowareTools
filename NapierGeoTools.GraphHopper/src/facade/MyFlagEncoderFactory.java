package facade;

import com.graphhopper.routing.util.FlagEncoder;
import com.graphhopper.routing.util.FlagEncoderFactory;
import com.graphhopper.util.PMap;

/**
 * Handles the creation of custom profiles
 * 
 * @author Jannik Enenkel
 *
 */
public class MyFlagEncoderFactory implements FlagEncoderFactory {

	@Override
	public FlagEncoder createFlagEncoder(String name, PMap configuration) {
		if(name.equals("car"))
			return FlagEncoderFactory.DEFAULT.createFlagEncoder(CAR, configuration);
		if(name.equals("bike"))
			return FlagEncoderFactory.DEFAULT.createFlagEncoder(BIKE, configuration);
		if(name.equals("car4wd"))
			return FlagEncoderFactory.DEFAULT.createFlagEncoder(CAR4WD, configuration);
		if(name.equals("mtb"))
			return FlagEncoderFactory.DEFAULT.createFlagEncoder(MOUNTAINBIKE, configuration);
		if(name.equals("motorcycle"))
			return FlagEncoderFactory.DEFAULT.createFlagEncoder(MOTORCYCLE, configuration);
		if(name.equals("racingbike"))
			return FlagEncoderFactory.DEFAULT.createFlagEncoder(RACINGBIKE, configuration);
		if(name.equals("hike"))
			return FlagEncoderFactory.DEFAULT.createFlagEncoder(HIKE, configuration);
		if(name.equals("foot"))
			return FlagEncoderFactory.DEFAULT.createFlagEncoder(FOOT, configuration);
		if(name.equals("bike2"))
			return FlagEncoderFactory.DEFAULT.createFlagEncoder(BIKE2, configuration);
		if(name.equals("customCar"))
			return new CustomCarFlagEncoder();
		if(name.equals("customBike"))
			return new CustomBikeFlagEncoder();
		if(name.equals("customFoot"))
			return new CustomFootFlagEncoder();
		return null;
	}

}
