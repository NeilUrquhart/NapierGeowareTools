package edu.napier.geo.easykml.helperClasses;

import java.util.ArrayList;

import edu.napier.geo.common.Location;

public class StringBuilder {

	public static String buildCoordinateString(ArrayList<Location> coordinates) {
		String coordinateString = "";

		for (Location coordinatesInstance : coordinates) {
			coordinateString += coordinatesInstance.getLon() + "," + coordinatesInstance.getLat() + ","
					+ coordinatesInstance.getAlt() + " ";
		}

		return coordinateString;
	}

	public static String buildCoordinateString(Location coordinates) {
		String coordinateString = "";

		coordinateString += coordinates.getLon() + "," + coordinates.getLat() + "," + coordinates.getAlt() + " ";

		return coordinateString;
	}

}
