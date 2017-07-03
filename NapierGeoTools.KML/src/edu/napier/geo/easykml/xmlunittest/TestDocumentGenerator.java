package edu.napier.geo.easykml.xmlunittest;

import java.awt.Color;
import java.util.ArrayList;

import javax.print.attribute.SetOfIntegerSyntax;

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
import edu.napier.geo.easykml.KML_Object.gx_TourPrimitive.TourControl;
import edu.napier.geo.easykml.KML_Object.gx_TourPrimitive.Wait;
import edu.napier.geo.easykml.KML_Object.stylesector.Style;
import edu.napier.geo.easykml.KML_Object.subStyle.colorStyle.IconStyle;
import edu.napier.geo.easykml.KML_Object.subStyle.colorStyle.LabelStyle;
import edu.napier.geo.easykml.KML_Object.subStyle.colorStyle.LineStyle;
import edu.napier.geo.easykml.KML_Object.subStyle.colorStyle.PolyStyle;
import edu.napier.geo.easykml.KML_Object.timePrimitive.Time;
import edu.napier.geo.easykml.KML_Object.timePrimitive.TimeSpan;
import edu.napier.geo.easykml.KML_Object.timePrimitive.TimeStamp;
import edu.napier.geo.easykml.facade.EasyKMLCreator;
import edu.napier.geo.easykml.helperClasses.AltitudeModes;

public class TestDocumentGenerator {

	public static void main(String[] args) throws Exception {
		EasyKMLCreator simpleKML = new EasyKMLCreator();

		
//		// create Styles
		Style style = new Style();
		style.setId("TestStyle");
//		IconStyle iconStyle = new IconStyle();
//		iconStyle.setId("iconstyle");
//		iconStyle.setColor(Color.WHITE);
//		iconStyle.setColorModeActivated(false);
//		iconStyle.setHeading(10.0f);
//		iconStyle.setScale(1.0f);
//		iconStyle.setIconHttpAddress("test");
//		style.setIconStyle(iconStyle);
//
	LineStyle lineStyle = new LineStyle();
	lineStyle.setId("linestyle");
		lineStyle.setColor(Color.WHITE);
		lineStyle.setColorModeActivated(false);
	lineStyle.setGxOuterColor(Color.WHITE);
		lineStyle.setGxOuterWidth(1.0f);
		lineStyle.setGxPhysicalWidth(1.0f);
		lineStyle.setGxLabelVisibility(true);
		lineStyle.setWidth(1.0f);

		style.setLineStyle(lineStyle);
//
//		PolyStyle polyStyle = new PolyStyle();
//		polyStyle.setId("polystyle");
//		polyStyle.setColor(Color.WHITE);
//		polyStyle.setColorModeActivated(false);
//		polyStyle.setFillPolygon(true);
//		polyStyle.setOutLine(true);
//		style.setPolyStyle(polyStyle);
//
//		LabelStyle labelStyle = new LabelStyle();
//		labelStyle.setId("labelstyle");
//		labelStyle.setColorModeActivated(true);
//		labelStyle.setScale(1.5f);
//		labelStyle.setColor(Color.WHITE);
//		style.setLabelStyle(labelStyle);
//
//		simpleKML.createStyle(style);

//		// create placemark
//
//		Point point = new Point(new Location(37.42228990140251,-122.0822035425683,0.0));
//		point.setId("point");
//		point.setAltitudeMode(AltitudeModes.CLAMP_TO_GROUND);
//		point.setExtruded(false);
//		Placemark pm = new Placemark(point);
//		
//		simpleKML.createPlacemark(pm);
		
		// create featureTest

		Point point = new Point(new Location(37.42228990140251,-122.0822035425683,0.0));
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
		//Style style = new Style();
		//style.setId("TestStyle");
		IconStyle iconStyle = new IconStyle();
		iconStyle.setId("iconstyle");
		iconStyle.setColor(Color.WHITE);
		iconStyle.setColorModeActivated(false);
		iconStyle.setHeading(10.0f);
		iconStyle.setScale(1.0f);
		iconStyle.setIconHttpAddress("test");
		style.setIconStyle(iconStyle);
		pm.setStyle(style);
		simpleKML.createPlacemark(pm);

		// create path
		ArrayList<Location> coordinates = new ArrayList<>();
		coordinates.add(new Location(37.42228990140251, -122.0822035425683, 0.0));
		coordinates.add(new Location(37.42228990140251, -125.0822035425683, 0.0));
		coordinates.add(new Location(50.42228990140251, -126.0822035425683, 0.0));

		LineString lineString = new LineString(coordinates);
		lineString.setId("linestring");
		lineString.setGxAltitudeOffset(0.0);
		lineString.setExtruded(true);
		lineString.setTessellated(false);
		lineString.setAltitudeMode(AltitudeModes.CLAMP_TO_GROUND);
		lineString.setGxDrawOrder(0);

		Placemark path = new Placemark(lineString);

		simpleKML.createPlacemark(path);

		// create Polygone
		ArrayList<Location> outerBoundary = new ArrayList<>();
		outerBoundary.add(new Location(37.818844, -122.366278, 30.0));
		outerBoundary.add(new Location(37.819267, -122.365248, 30.0));
		outerBoundary.add(new Location(37.819861, -122.365640, 30.0));
		outerBoundary.add(new Location(37.819429, -122.366669, 30.0));
		outerBoundary.add(new Location(37.818844, -122.366278, 30.0));

		ArrayList<Location> innerBoundary = new ArrayList<>();
		innerBoundary.add(new Location(37.818977, -122.366212, 30.0));
		innerBoundary.add(new Location(37.819294, -122.365424, 30.0));
		innerBoundary.add(new Location(37.819731, -122.365704, 30.0));
		innerBoundary.add(new Location(37.819402, -122.366488, 30.0));
		innerBoundary.add(new Location(37.818977, -122.366212, 30.0));

		Polygon polygon = new Polygon(new LinearRing(outerBoundary));
		polygon.addInnerBoundry(new LinearRing(innerBoundary));
		polygon.setExtruded(true);
		polygon.setTessellated(false);
		polygon.setAltitudeMode(AltitudeModes.CLAMP_TO_GROUND);
		polygon.setId("polygon");

		Placemark polygonPM = new Placemark(polygon);

		simpleKML.createPlacemark(polygonPM);

		// create Tour

		Placemark placemark2 = new Placemark("New Placemark", "blabla",
				new Point(new Location(-43.61725568991338, 170.1426332867125, 0.0)));
		placemark2.setId("pin2");
		placemark2.setStyleURL("TestStyle");
		simpleKML.createPlacemark(placemark2);



		Playlist playlist = new Playlist();

		SoundCue soundCue = new SoundCue("test");
		soundCue.setDelayedStart(1.0);
		soundCue.setId("soundcue");

		Camera camera1 = new Camera();
		camera1.setCoordinates(new Location(-43.671, 170.157, 9700.0));
		camera1.setHeading(-6.0);
		camera1.setTilt(33.0);
		camera1.setId("camera");
		FlyTo flyTo1 = new FlyTo(5.0, camera1);
		flyTo1.setId("flyto");
		flyTo1.setFlyToMode(FlyTo.FLYTOMODE_BOUNCE);

		Wait wait1 = new Wait(2.0);
		wait1.setId("wait");

		TourControl tourControl = new TourControl();
		tourControl.setId("tourcontrol");

		playlist.addPrimitiveAction(soundCue);
		playlist.addPrimitiveAction(flyTo1);
		playlist.addPrimitiveAction(wait1);
		playlist.addPrimitiveAction(tourControl);

		
		Tour tour = new Tour(playlist);


		simpleKML.createTour(tour);

		// save Document
		simpleKML.saveKMLDocument(
				"createdFeature");
		

	}

	
}
