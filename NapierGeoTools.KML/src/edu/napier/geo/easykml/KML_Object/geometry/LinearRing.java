package edu.napier.geo.easykml.KML_Object.geometry;

import java.util.ArrayList;

import edu.napier.geo.common.Location;
import edu.napier.geo.easykml.helperClasses.AltitudeModes;
import edu.napier.geo.easykml.helperClasses.KMLNotation;
import edu.napier.geo.easykml.helperClasses.StringBuilder;
import edu.napier.geo.easykml.helperClasses.TreeNode;

public class LinearRing extends KML_Geometry {

	private Double gxAltitudeOffset;
	private Boolean extruded;
	private Boolean tessellated;
	private String altitudeMode;
	private ArrayList<Location> locations;

	/**
	 * A LinearRing defines a closed line string which is typically used in
	 * polygons to define the inner or outer boundaries. For more information
	 * visit:
	 * {@link: https://developers.google.com/kml/documentation/kmlreference#linearring}
	 * 
	 * @param locations
	 */
	public LinearRing(ArrayList<Location> locations) {
		this.locations = locations;
	}

	public Double getGxAltitudeOffset() {
		return gxAltitudeOffset;
	}

	public void setGxAltitudeOffset(Double gxAltitudeOffset) {
		this.gxAltitudeOffset = gxAltitudeOffset;
	}

	public String isExtruded() {
		return (extruded) ? "1" : "0";
	}

	public void setExtruded(Boolean extruded) {
		this.extruded = extruded;
	}

	public String isTessellated() {
		return (tessellated) ? "1" : "0";
	}

	public void setTessellated(Boolean tessellated) {
		this.tessellated = tessellated;
	}

	public String getAltitudeMode() {
		return altitudeMode;
	}

	public void setAltitudeMode(String altitudeMode) {
		this.altitudeMode = altitudeMode;
	}

	public ArrayList<Location> getCoordinates() {
		return locations;
	}

	public void setCoordinates(ArrayList<Location> locations) {
		this.locations = locations;
	}

	public TreeNode<KMLNotation> getLinkedOutput() {

		TreeNode<KMLNotation> root = super.getLinkedOutput();

		if (gxAltitudeOffset != null)
			root.addChild(new KMLNotation("altitudeOffset", Double.toString(this.getGxAltitudeOffset()), true));
		if (this.extruded != null)
			root.addChild(new KMLNotation("extrude", this.isExtruded(), false));
		if (this.tessellated != null)
			root.addChild(new KMLNotation("tessellate", this.isTessellated(), false));
		if (this.getAltitudeMode() != null)
			root.addChild(new KMLNotation("altitudeMode", this.getAltitudeMode(), AltitudeModes.belongsToExtension(this.getAltitudeMode())));
		if (this.getCoordinates() != null)
			root.addChild(new KMLNotation("coordinates", StringBuilder.buildCoordinateString(this.locations), false));

		return root;
	}

}
