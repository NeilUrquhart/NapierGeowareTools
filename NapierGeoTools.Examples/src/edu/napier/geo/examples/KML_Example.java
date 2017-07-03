package edu.napier.geo.examples;
import edu.napier.geo.common.Journey;
import edu.napier.geo.common.Location;
import edu.napier.geo.common.Useful;
import edu.napier.geo.easykml.KML_Object.feature.Placemark;
import edu.napier.geo.easykml.KML_Object.geometry.KML_Geometry;
import edu.napier.geo.easykml.KML_Object.geometry.LineString;
import edu.napier.geo.easykml.KML_Object.geometry.Point;
import edu.napier.geo.easykml.facade.EasyKMLCreator;


public class KML_Example {

	public static void main(String[] args) {
		EasyKMLCreator kml = new EasyKMLCreator();
		
		Location merchiston = new Location(55.933326,-3.213896,0.0);
		merchiston.setDescription("Edinburgh Napier University, Merchiston Campus");
		merchiston.setSource("NeilU");
		
		Location sighthill = new Location(55.924959,-3.289067,0.0);
		merchiston.setDescription("Edinburgh Napier University, Merchiston Campus");
		merchiston.setSource("NeilU");
		
		kml.createPlacemark("Edinburgh Napier University",merchiston);
		kml.createPlacemark("Edinburgh Napier University",sighthill);
		
		Journey myJourney = new Journey(merchiston, sighthill, "");
		kml.addJourney(myJourney);
		
		try{
		kml.saveKMLDocument("test.kml");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error saving KML");
		}
	}

}
