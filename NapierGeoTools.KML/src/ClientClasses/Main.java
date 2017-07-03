package ClientClasses;

import java.awt.Color;
import java.util.ArrayList;

import edu.napier.geo.common.Location;
import edu.napier.geo.easykml.KML_Object.Playlist;
import edu.napier.geo.easykml.KML_Object.abstractView.Camera;
import edu.napier.geo.easykml.KML_Object.abstractView.LookAt;
import edu.napier.geo.easykml.KML_Object.feature.Placemark;
import edu.napier.geo.easykml.KML_Object.feature.Tour;
import edu.napier.geo.easykml.KML_Object.geometry.LineString;
import edu.napier.geo.easykml.KML_Object.geometry.LinearRing;
import edu.napier.geo.easykml.KML_Object.geometry.Point;
import edu.napier.geo.easykml.KML_Object.geometry.Polygon;
import edu.napier.geo.easykml.KML_Object.gx_TourPrimitive.FlyTo;
import edu.napier.geo.easykml.KML_Object.gx_TourPrimitive.SoundCue;
import edu.napier.geo.easykml.KML_Object.gx_TourPrimitive.Wait;
import edu.napier.geo.easykml.KML_Object.stylesector.Style;
import edu.napier.geo.easykml.KML_Object.subStyle.colorStyle.IconStyle;
import edu.napier.geo.easykml.KML_Object.subStyle.colorStyle.LabelStyle;
import edu.napier.geo.easykml.KML_Object.subStyle.colorStyle.LineStyle;
import edu.napier.geo.easykml.KML_Object.subStyle.colorStyle.PolyStyle;
import edu.napier.geo.easykml.KML_Object.timePrimitive.Time;
import edu.napier.geo.easykml.KML_Object.timePrimitive.TimeStamp;
import edu.napier.geo.easykml.facade.EasyKMLCreator;
import edu.napier.geo.easykml.helperClasses.AltitudeModes;

public class Main {

	public static void main(String[] args) {

		EasyKMLCreator kmlCreator = new EasyKMLCreator();

		// Location for points

		Point point = new Point(new Location(37.4220033612141, -122.084075, 50.0));
		point.setAltitudeMode(AltitudeModes.RELATIVE_TO_GROUND);

		// Simple point placemark

		kmlCreator.createSimplePoint(point);

		// Point placemark - local style.

		Style pointStyle = new Style();

		IconStyle iconStyle = new IconStyle();
		iconStyle.setIconHttpAddress("http://maps.google.com/mapfiles/kml/pal4/icon28.png");

		LabelStyle labelStyle = new LabelStyle();
		labelStyle.setColor(Color.GREEN);

		pointStyle.setIconStyle(iconStyle);
		pointStyle.setLabelStyle(labelStyle);

		Placemark arrowPoint = new Placemark("Arrow Placemark", "Point Placemark with an local style", point);
		arrowPoint.setStyle(pointStyle);

		kmlCreator.createPlacemark(arrowPoint);

		try {
			kmlCreator.saveKMLDocument("C:\\Users\\Jannik\\Documents\\Presentation\\KMLpoints");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ------------------------------------------------------------------------------------------
		// ------------------------------------------------------------------------------------------

		ArrayList<Location> coordinates = fillPathList();

		// Create simple path

		kmlCreator.createSimplePath(new LineString(coordinates));

		// Create path and global style

		Style globalStyle = new Style();

		LineStyle lineStyle = new LineStyle();
		lineStyle.setColor(Color.RED);
		lineStyle.setWidth(3.0f);

		globalStyle.setLineStyle(lineStyle);
		globalStyle.setId("Global Style");

		LineString lineString = new LineString(coordinates);

		Placemark advancedPath = new Placemark("Advanced Path", "path using a global style", lineString);
		advancedPath.setStyleURL("Global Style");

		kmlCreator.createStyle(globalStyle);
		kmlCreator.createPlacemark(advancedPath);

		try {
			kmlCreator.saveKMLDocument("C:\\Users\\Jannik\\Documents\\Presentation\\KMLpaths");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ------------------------------------------------------------------------------------------
		// ------------------------------------------------------------------------------------------

		ArrayList<Location> outerBoundary = fillOuterBoundary();
		ArrayList<Location> innerBoundary = fillInnerBoundary();

		// create simple Polygon

		kmlCreator.createSimplePolygone(new Polygon(new LinearRing(outerBoundary)));

		// create advanced Polygon

		Polygon polygon = new Polygon(new LinearRing(outerBoundary));
		polygon.addInnerBoundry(new LinearRing(innerBoundary));
		polygon.setExtruded(true);
		polygon.setAltitudeMode(AltitudeModes.RELATIVE_TO_GROUND);

		Placemark polygonPM = new Placemark("Advanced Polygon", "3D Model", polygon);

		Style localPolyStyle = createPolyStyle();
		polygonPM.setStyle(localPolyStyle);

		kmlCreator.createPlacemark(polygonPM);
		
		try {
			kmlCreator.saveKMLDocument("C:\\Users\\Jannik\\Documents\\Presentation\\KMLpolygons");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// ------------------------------------------------------------------------------------------
		// ------------------------------------------------------------------------------------------


		SoundCue soundCue = new SoundCue("C:\\Users\\Jannik\\Google Drive\\Uni\\2. `Trimester\\Hon Project\\Sound.mp3");
		soundCue.setDelayedStart(1.0);

		Camera camera1 = new Camera();
		camera1.setCoordinates(new Location(-43.671, 170.157, 9700.0));
		camera1.setHeading(-6.0);
		camera1.setTilt(33.0);
		FlyTo flyTo1 = new FlyTo(5.0, camera1);
		flyTo1.setId("FirstFly");

		Wait wait1 = new Wait(5.0);
		wait1.setId("WaitIDTest");
		
		LookAt lookAt1 = new LookAt();
		lookAt1.setId("Lookatidtest");
		lookAt1.setCoordinates(new Location(-39.279, 174.007, 0.0));
		lookAt1.setHeading(112.817);
		lookAt1.setTilt(68.065);
		lookAt1.setRange(6811.884);
		lookAt1.setAltitudeMode(AltitudeModes.RELATIVE_TO_GROUND);
		FlyTo flyTo2 = new FlyTo(3.0, lookAt1); 
		flyTo2.setFlyToMode(FlyTo.FLYTOMODE_SMOOTH);

		Wait wait2 = new Wait(1.0);
		wait2.setId("WaitIDTest");
		
		Playlist playlist = new Playlist();
		playlist.addPrimitiveAction(soundCue);
		playlist.addPrimitiveAction(flyTo1);
		playlist.addPrimitiveAction(wait1);
		playlist.addPrimitiveAction(flyTo2);
		playlist.addPrimitiveAction(wait2);


		
		Tour tour  = new Tour(playlist);
		tour.setName("Example Tour");
		
		kmlCreator.createTour(tour);
		
		try {
			kmlCreator.saveKMLDocument("C:\\Users\\Jannik\\Documents\\Presentation\\KMLtour");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ------------------------------------------------------------------------------------------
		// ------------------------------------------------------------------------------------------

		
		// create animation using times
		
		Style hikerStyle = new Style();
		
		IconStyle iconStyle2 = new IconStyle();
		iconStyle2.setIconHttpAddress("http://maps.google.com/mapfiles/ms/icons/hiker.png");
				
		hikerStyle.setIconStyle(iconStyle2);
		hikerStyle.setId("hikerStyle");
		
		kmlCreator.createStyle(hikerStyle);
		
		int year = 2000;
		Time time = new Time();
		TimeStamp timeStamp = new TimeStamp(time);
		
		for (Location location : coordinates) {
			Placemark hikerPlacemark = new Placemark(new Point(location));
			hikerPlacemark.setStyleURL("hikerStyle");
			hikerPlacemark.setTimePrimitive(timeStamp);
			time.setYear(year++);
			timeStamp.setTimeStamp(time);
			
			kmlCreator.createPlacemark(hikerPlacemark);
		}
		
		
		try {
			kmlCreator.saveKMLDocument("C:\\Users\\Jannik\\Documents\\Presentation\\KMLtime");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	private static Style createPolyStyle() {

		Style style = new Style();
		LineStyle lineStyle = new LineStyle();
		lineStyle.setColor(Color.RED);
		lineStyle.setWidth(5.0f);
		lineStyle.setTransparency(50);
		style.setLineStyle(lineStyle);

		PolyStyle polyStyle = new PolyStyle();
		polyStyle.setId("Pol");
		polyStyle.setColor(Color.GREEN);
		polyStyle.setTransparency(80);
		polyStyle.setFillPolygon(true);
		style.setPolyStyle(polyStyle);
		return style;
	}

	private static ArrayList<Location> fillOuterBoundary() {
		ArrayList<Location> outerBoundary = new ArrayList<>();

		outerBoundary.add(new Location(37.818844, -122.366278, 30.0));
		outerBoundary.add(new Location(37.819267, -122.365248, 30.0));
		outerBoundary.add(new Location(37.819861, -122.365640, 30.0));
		outerBoundary.add(new Location(37.819429, -122.366669, 30.0));
		outerBoundary.add(new Location(37.818844, -122.366278, 30.0));

		return outerBoundary;
	}

	private static ArrayList<Location> fillInnerBoundary() {
		ArrayList<Location> innerBoundary = new ArrayList<>();

		innerBoundary.add(new Location(37.818977, -122.366212, 30.0));
		innerBoundary.add(new Location(37.819294, -122.365424, 30.0));
		innerBoundary.add(new Location(37.819731, -122.365704, 30.0));
		innerBoundary.add(new Location(37.819402, -122.366488, 30.0));
		innerBoundary.add(new Location(37.818977, -122.366212, 30.0));

		return innerBoundary;
	}

	private static ArrayList<Location> fillPathList() {
		ArrayList<Location> coordinates = new ArrayList<>();

		coordinates.add(new Location(55.93336094951766, -3.213540428387887, 0));
		coordinates.add(new Location(55.93301382046595, -3.213159771502204, 0));
		coordinates.add(new Location(55.9327368282686, -3.213870975174439, 0));
		coordinates.add(new Location(55.93203407370937, -3.212964006845938, 0));
		coordinates.add(new Location(55.93183706796318, -3.212585189431202, 0));
		coordinates.add(new Location(55.93180775468549, -3.212184291032354, 0));
		coordinates.add(new Location(55.93214939919757, -3.211402135087992, 0));
		coordinates.add(new Location(55.93243585989897, -3.211531950391159, 0));
		coordinates.add(new Location(55.93283694566088, -3.211886884454949, 0));
		coordinates.add(new Location(55.93312181017509, -3.212181619397811, 0));
		coordinates.add(new Location(55.93332382683011, -3.212322970809236, 0));
		coordinates.add(new Location(55.93368495501196, -3.211395358734813, 0));

		return coordinates;
	}

}
