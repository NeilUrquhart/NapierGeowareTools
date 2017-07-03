package edu.napier.geo.easykml.KML_Object.geometry;

import java.util.ArrayList;

import edu.napier.geo.common.Location;
import edu.napier.geo.easykml.helperClasses.AltitudeModes;
import edu.napier.geo.easykml.helperClasses.KMLNotation;
import edu.napier.geo.easykml.helperClasses.StringBuilder;
import edu.napier.geo.easykml.helperClasses.TreeNode;

public class LineString extends KML_Geometry {

	private Double gxAltitudeOffset;
	private Boolean extruded;
	private Boolean tessellated;
	private String altitudeMode;
	private Integer gxDrawOrder;
	private ArrayList<Location> locations;

	/*
	 * Information Area (Source: KML Reference Google Developers):
	 * 
	 * <extrude> Boolean value. Specifies whether to connect the LinearRing to
	 * the ground. To extrude this geometry, the altitude mode must be either
	 * relativeToGround, relativeToSeaFloor, or absolute. Only the vertices of
	 * the LinearRing are extruded, not the center of the geometry. The vertices
	 * are extruded toward the center of the Earth's sphere.
	 * 
	 * <tessellate> Boolean value. Specifies whether to allow the LinearRing to
	 * follow the terrain. To enable tessellation, the value for <altitudeMode>
	 * must be clampToGround or clampToSeaFloor. Very large LinearRings should
	 * enable tessellation so that they follow the curvature of the earth
	 * (otherwise, they may go underground and be hidden).
	 * 
	 * <altitudeMode> Specifies how altitude components in the <coordinates>
	 * element are interpreted. Further information are available in the
	 * AltitudeModes class.
	 */

	/**
	 * A LineSting defines a path which is built up from a list of locations.
	 * For more information visit:
	 * {@link: https://developers.google.com/kml/documentation/kmlreference#linestring}
	 * 
	 * @param locations
	 */
	public LineString(ArrayList<Location> locations) {
		this.locations = locations;
	}

	public Double getGxAltitudeOffset() {
		return gxAltitudeOffset;
	}

	public void setGxAltitudeOffset(Double gxAltitudeOffset) {
		this.gxAltitudeOffset = gxAltitudeOffset;
	}

	public Integer getGxDrawOrder() {
		return gxDrawOrder;
	}

	public void setGxDrawOrder(Integer gxDrawOrder) {
		this.gxDrawOrder = gxDrawOrder;
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
		if (this.getGxDrawOrder() != null)
			root.addChild(new KMLNotation("drawOrder", Integer.toString(this.getGxDrawOrder()), true));
		if (this.getCoordinates() != null)
			root.addChild(new KMLNotation("coordinates", StringBuilder.buildCoordinateString(this.locations), false));

		return root;
	}

	// private void checkExtrudedPreconditions(boolean extrudedValue, String
	// altitudeMode) {
	// if (extrudedValue == true && (altitudeMode !=
	// AltitudeModes.RELATIVE_TO_GROUND
	// || altitudeMode != AltitudeModes.RELATIVE_TO_SEA_FLOOR || altitudeMode !=
	// AltitudeModes.ABSOLUT)) {
	// this.extruded = false;
	// } else {
	// this.extruded = extrudedValue;
	// }
	// }
	//
	// private void checkTesselatedPreconditions(boolean tesselatedValue, String
	// altitudeMode) {
	// if (tesselatedValue == true && (altitudeMode !=
	// AltitudeModes.CLAMP_TO_GROUND
	// || altitudeMode != AltitudeModes.CLAMP_TO_SEA_FLOOR)) {
	// this.tessellated = false;
	// } else {
	// this.extruded = tesselatedValue;
	// }
	// }

}
