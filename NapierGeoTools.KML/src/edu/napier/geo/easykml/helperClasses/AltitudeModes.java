package edu.napier.geo.easykml.helperClasses;

public class AltitudeModes {

	/*
	 * Information Area (Source: KML Reference Google Developers):
	 * 
	 * absolute - 
	 * Sets the altitude of the coordinate relative to sea level,
	 * regardless of the actual elevation of the terrain beneath the element.
	 * For example, if you set the altitude of a coordinate to 10 meters with an
	 * absolute altitude mode, the icon of a point placemark will appear to be
	 * at ground level if the terrain beneath is also 10 meters above sea level.
	 * If the terrain is 3 meters above sea level, the placemark will appear
	 * elevated above the terrain by 7 meters. A typical use of this mode is for
	 * aircraft placement.
	 * 
	 * clampToGround - (default) 
	 * Indicates to ignore an altitude specification
	 * (for example, in the <coordinates> tag).
	 * 
	 * relativeToGround - 
	 * Sets the altitude of the element relative to the
	 * actual ground elevation of a particular location. For example, if the
	 * ground elevation of a location is exactly at sea level and the altitude
	 * for a point is set to 9 meters, then the elevation for the icon of a
	 * point placemark elevation is 9 meters with this mode. However, if the
	 * same coordinate is set over a location where the ground elevation is 10
	 * meters above sea level, then the elevation of the coordinate is 19
	 * meters. A typical use of this mode is for placing telephone poles or a
	 * ski lift.
	 * 
	 * 
	 * A KML extension in the Google extension namespace, allowing altitudes
	 * relative to the sea floor. Values are:
	 * 
	 * relativeToSeaFloor - 
	 * Interprets the <altitude> as a value in meters above
	 * the sea floor. If the point is above land rather than sea, the <altitude>
	 * will be interpreted as being above the ground.
	 * 
	 * clampToSeaFloor - 
	 * The <altitude> specification is ignored, and the point
	 * will be positioned on the sea floor. If the point is on land rather than
	 * at sea, the point will be positioned on the ground.
	 */

	public final static String ABSOLUT = "absolut";

	public final static String CLAMP_TO_GROUND = "clampToGround";

	public final static String CLAMP_TO_SEA_FLOOR = "clampToSeaFloor";

	public final static String RELATIVE_TO_GROUND = "relativeToGround";

	public final static String RELATIVE_TO_SEA_FLOOR = "relativeToSeaFloor";
	
	
	public final static KMLNotation getAltElement(String mode){
		if(mode == RELATIVE_TO_SEA_FLOOR || mode == CLAMP_TO_SEA_FLOOR)
			return new KMLNotation("altitudeMode", mode, true);
		
		return new KMLNotation("altitudeMode", mode, false);
	}
	
	public final static boolean belongsToExtension(String mode){
		if(mode == RELATIVE_TO_SEA_FLOOR || mode == CLAMP_TO_SEA_FLOOR)
			return true;
		
		return false;
	}

}
