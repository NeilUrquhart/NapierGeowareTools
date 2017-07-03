package ClientClasses;

import java.awt.Color;

import edu.napier.geo.common.Location;
import edu.napier.geo.easykml.KML_Object.abstractView.LookAt;
import edu.napier.geo.easykml.KML_Object.feature.Placemark;
import edu.napier.geo.easykml.KML_Object.geometry.Point;
import edu.napier.geo.easykml.KML_Object.stylesector.Style;
import edu.napier.geo.easykml.KML_Object.subStyle.colorStyle.IconStyle;
import edu.napier.geo.easykml.KML_Object.timePrimitive.Time;
import edu.napier.geo.easykml.KML_Object.timePrimitive.TimeSpan;
import edu.napier.geo.easykml.KML_Object.timePrimitive.TimeStamp;
import edu.napier.geo.easykml.facade.EasyKMLCreator;
import edu.napier.geo.easykml.helperClasses.AltitudeModes;

public class PerformanceTest {

	public static void main(String[] args) {

		EasyKMLCreator easyKMLCreator = new EasyKMLCreator();

		Point point = new Point(new Location(37.42228990140251, -122.0822035425683, 0.0));
		point.setId("point");
		point.setAltitudeMode(AltitudeModes.CLAMP_TO_GROUND);
		point.setExtruded(false);
		Placemark pm = new Placemark(point);
		pm.setId("idtest");
		pm.setName("featureTest");
		pm.setVisibility(true);
		pm.setOpen(false);
		pm.setDescription("test");
		LookAt look = new LookAt();
		Time t = new Time();
		t.setYear(1994);
		look.setTimePrimitive(new TimeStamp(t));
		look.setCoordinates(new Location(37.81, -122.363, 2000));
		look.setRange(500.0);
		look.setTilt(45.0);
		look.setHeading(0.0);
		look.setAltitudeMode(AltitudeModes.RELATIVE_TO_GROUND);
		pm.setAbstractView(look);
		Time t2 = new Time();
		t2.setYear(1995);
		pm.setTimePrimitive(new TimeSpan(t, t2));
		pm.setStyleURL("TestStyle");
		Style style = new Style();
		style.setId("TestStyle");
		IconStyle iconStyle = new IconStyle();
		iconStyle.setId("iconstyle");
		iconStyle.setColor(Color.WHITE);
		iconStyle.setColorModeActivated(false);
		iconStyle.setHeading(10.0f);
		iconStyle.setScale(1.0f);
		iconStyle.setIconHttpAddress("test");
		style.setIconStyle(iconStyle);
		pm.setStyle(style);

		long startTime = System.currentTimeMillis();
		int i;
		for (i = 0; i < 200000; i++) {
			if(i%10000 == 0 )System.err.println("Iteration: "+i);
			easyKMLCreator.createPlacemark(pm);
		}
		long endTime = System.currentTimeMillis();

		System.out.println("Time to create " + i + " placemarks: " + (endTime - startTime) + " milliseconds.");

		startTime = System.currentTimeMillis();
		try {
			easyKMLCreator.saveKMLDocument(
					"C:\\Users\\Jannik\\Google Drive\\Uni\\2. `Trimester\\Hon Project\\PerformanceTest");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		endTime = System.currentTimeMillis();

		System.out.println(
				"Time to save the file with " + i + " placemarks: " + (endTime - startTime) + " milliseconds.");

	}

}
