package edu.napier.geo.easykml.KML_Object.abstractView;

import edu.napier.geo.common.Location;
import edu.napier.geo.easykml.helperClasses.AltitudeModes;
import edu.napier.geo.easykml.helperClasses.KMLNotation;
import edu.napier.geo.easykml.helperClasses.TreeNode;

public class LookAt extends AbstractView {

	/*
	 * <longitude> Longitude of the point the camera is looking at. Angular
	 * distance in degrees, relative to the Prime Meridian. Values west of the
	 * Meridian range from -180 to 0 degrees. Values east of the Meridian range
	 * from 0 to 180 degrees.
	 * 
	 * <latitude> Latitude of the point the camera is looking at. Degrees north
	 * or south of the Equator (0 degrees). Values range from -90 degrees to 90
	 * degrees.
	 * 
	 * <altitude> Distance from the earth's surface, in meters. Interpreted
	 * according to the LookAt's altitude mode.
	 */
	private Location coordinates;

	/*
	 * <heading> Direction (that is, North, South, East, West), in degrees.
	 * Default=0 (North). (See diagram below.) Values range from 0 to 360
	 * degrees.
	 */
	private Double heading;

	/*
	 * <tilt> Angle between the direction of the LookAt position and the normal
	 * to the surface of the earth. (See diagram below.) Values range from 0 to
	 * 90 degrees. Values for <tilt> cannot be negative. A <tilt> value of 0
	 * degrees indicates viewing from directly above. A <tilt> value of 90
	 * degrees indicates viewing along the horizon.
	 */
	private Double tilt;

	/*
	 * <range>(required) Distance in meters from the point specified by
	 * <longitude>, <latitude>, and <altitude> to the LookAt position. (See
	 * diagram below.)
	 */
	private Double range;

	/*
	 * <altitudeMode> Specifies how the <altitude> specified for the LookAt
	 * point is interpreted.
	 */
	private String altitudeMode;
	
	/**
	 * LookAt is used to define the virtual camera that views the surface. It
	 * defines its position and viewing direction in relation to the object that being viewed.
	 * For more information visit:
	 * {@link: https://developers.google.com/kml/documentation/kmlreference#lookat}
	 */
	public LookAt() {
		// TODO Auto-generated constructor stub
	}
	
	public Location getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Location coordinates) {
		this.coordinates = coordinates;
	}

	public Double getHeading() {
		return heading;
	}

	public void setHeading(Double heading) {
		if (heading <= 360 && heading >= 0)
			this.heading = heading;
	}

	public Double getTilt() {
		return tilt;
	}

	public void setTilt(Double tilt) {
		if (tilt <= 90 && tilt >= 0)
			this.tilt = tilt;
	}

	public Double getRange() {
		return range;
	}

	public void setRange(Double range) {
		this.range = range;
	}

	public String getAltitudeMode() {
		return altitudeMode;
	}

	public void setAltitudeMode(String altitudeMode) {
		this.altitudeMode = altitudeMode;
	}
	
	public TreeNode<KMLNotation> getLinkedOutput (){
		
		TreeNode<KMLNotation> root = super.getLinkedOutput();
		//root.addChild(new KML_element("id", this.getId(), false));

		if(this.getCoordinates() != null)root.addChild(new KMLNotation("longitude", Double.toString(this.getCoordinates().getLon()), false));
		if(this.getCoordinates() != null)root.addChild(new KMLNotation("latitude", Double.toString(this.getCoordinates().getLat()), false));
		if(this.getCoordinates() != null)root.addChild(new KMLNotation("altitude", Double.toString(this.getCoordinates().getAlt()), false));
		if(this.getHeading() != null)root.addChild(new KMLNotation("heading", Double.toString(this.getHeading()), false));
		if(this.getTilt() != null)root.addChild(new KMLNotation("tilt", Double.toString(this.getTilt()), false));
		if(this.getRange() != null)root.addChild(new KMLNotation("range", Double.toString(this.getRange()), false));
		if(this.getAltitudeMode() != null)root.addChild(new KMLNotation("altitudeMode", this.getAltitudeMode(), AltitudeModes.belongsToExtension(this.getAltitudeMode())));


		return root; 
	}
	

}
