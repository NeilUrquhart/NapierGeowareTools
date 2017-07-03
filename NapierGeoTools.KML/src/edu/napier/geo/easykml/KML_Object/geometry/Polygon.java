package edu.napier.geo.easykml.KML_Object.geometry;

import java.util.ArrayList;

import edu.napier.geo.easykml.helperClasses.AltitudeModes;
import edu.napier.geo.easykml.helperClasses.KMLNotation;
import edu.napier.geo.easykml.helperClasses.TreeNode;

public class Polygon extends KML_Geometry {

	/*
	 * Information Area (Source: KML Reference Google Developers):
	 * 
	 * A Polygon is defined by an outer boundary and 0 or more inner boundaries.
	 * The boundaries, in turn, are defined by LinearRings. When a Polygon is
	 * extruded, its boundaries are connected to the ground to form additional
	 * polygons, which gives the appearance of a building or a box. Extruded
	 * Polygons use <PolyStyle> for their color, color mode, and fill.
	 * 
	 * The <coordinates> for polygons must be specified in counterclockwise
	 * order. Polygons follow the "right-hand rule," which states that if you
	 * place the fingers of your right hand in the direction in which the
	 * coordinates are specified, your thumb points in the general direction of
	 * the geometric normal for the polygon. (In 3D graphics, the geometric
	 * normal is used for lighting and points away from the front face of the
	 * polygon.) Since Google Earth fills only the front face of polygons, you
	 * will achieve the desired effect only when the coordinates are specified
	 * in the proper order. Otherwise, the polygon will be gray.
	 * 
	 */

	private Boolean extruded;
	private Boolean tessellated;
	private String altitudeMode;
	private LinearRing outerBoundry;
	private ArrayList<LinearRing> innerBoundryList = new ArrayList<>();

	/**
	 * A polygon is a figure that is bounded by a finite chain of straight lines
	 * closing in a loop. To define a polygon at one LinearRing is needed to
	 * define the outer boundary of the polygon. Furthermore n LinearRings may
	 * be added to define optional inner Boundaries. visit:
	 * {@link: https://developers.google.com/kml/documentation/kmlreference#polygon}
	 * 
	 * @param outerBoundry
	 */
	public Polygon(LinearRing outerBoundry) {
		this.outerBoundry = outerBoundry; 
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

	public LinearRing getOuterBoundry() {
		return outerBoundry;
	}

	public void setOuterBoundry(LinearRing outerBoundry) {
		this.outerBoundry = outerBoundry;
	}

	public ArrayList<LinearRing> getInnerBoundryList() {
		return innerBoundryList;
	}

	public void addInnerBoundry(LinearRing innerBoundry) {
		if (this.innerBoundryList == null) {
			this.innerBoundryList = new ArrayList<>();
		}
		this.innerBoundryList.add(innerBoundry);
	}

	public void setInnerBoundryList(ArrayList<LinearRing> innerBoundryList) {
		this.innerBoundryList = innerBoundryList;
	}

	public TreeNode<KMLNotation> getLinkedOutput() {

		TreeNode<KMLNotation> root = super.getLinkedOutput();

		if (this.extruded != null)
			root.addChild(new KMLNotation("extrude", this.isExtruded(), false));
		if (this.tessellated != null)
			root.addChild(new KMLNotation("tessellate", this.isTessellated(), false));
		if (this.getAltitudeMode() != null)
			root.addChild(new KMLNotation("altitudeMode", this.getAltitudeMode(), AltitudeModes.belongsToExtension(this.getAltitudeMode())));
		if (this.getOuterBoundry() != null)
			root.addChild(new KMLNotation("outerBoundaryIs", null, false)).addTreeNode(outerBoundry.getLinkedOutput());
		for (LinearRing linearRing : innerBoundryList) {
			TreeNode<KMLNotation> rootInner = root.addChild(new KMLNotation("innerBoundaryIs", null, false));
			rootInner.addTreeNode(linearRing.getLinkedOutput());
		}

		return root;
	}

}
