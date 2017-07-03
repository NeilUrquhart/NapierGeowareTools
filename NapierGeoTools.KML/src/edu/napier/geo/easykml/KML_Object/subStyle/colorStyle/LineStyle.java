package edu.napier.geo.easykml.KML_Object.subStyle.colorStyle;

import java.awt.Color;

import edu.napier.geo.easykml.helperClasses.KMLNotation;
import edu.napier.geo.easykml.helperClasses.TreeNode;

public class LineStyle extends ColorStyle {

	/*
	 * Information area (Source: KML Reference Google):
	 * 
	 * <width> Width of the line, in pixels.
	 * 
	 * <gx:outerColor> Color of the portion of the line defined by
	 * <gx:outerWidth>. Note that the <gx:outerColor> and <gx:outerWidth>
	 * elements are ignored when <LineStyle> is applied to <Polygon> and
	 * <LinearRing>.
	 * 
	 * <gx:outerWidth> A value between 0.0 and 1.0 that specifies the proportion
	 * of the line that uses the <gx:outerColor>. Only applies to lines setting
	 * width with <gx:physicalWidth>; it does not apply to lines using <width>.
	 * See also <gx:drawOrder> in <LineString>. A draw order value may be
	 * necessary if dual-colored lines are crossing each other—for example, for
	 * showing freeway interchanges.
	 * 
	 * <gx:physicalWidth> Physical width of the line, in meters.
	 * 
	 * <gx:labelVisibility> A boolean defining whether or not to display a text
	 * label on a LineString. A LineString's label is contained in the <name>
	 * element that is a sibling of <LineString> (i.e. contained within the same
	 * <Placemark> element).
	 * 
	 */

	private Float width;
	private Color gxOuterColor = null;
	private Float gxOuterWidth;
	private Float gxPhysicalWidth;
	private Boolean gxLabelVisibility;
	
	/**
	 * LineStyle specifies how Lines are drawn.
	 * For more information visit: 
	 * {@link: https://developers.google.com/kml/documentation/kmlreference#linestyle}
	 */
	public LineStyle() {
		// TODO Auto-generated constructor stub
	}

	public Float getWidth() {
		return width;
	}

	public void setWidth(Float width) {
		this.width = width;
	}

	public String getGxOuterColor() {
		if (gxOuterColor != null){
		String hexString = "ff" + String.format("%02x", gxOuterColor.getBlue() & 0x00FF)
				+ String.format("%02x", gxOuterColor.getGreen() & 0x00FF)
				+ String.format("%02x", gxOuterColor.getRed() & 0x00FF);
		return hexString;
		}
		return null;
	}

	public void setGxOuterColor(Color gxOuterColor) {
		this.gxOuterColor = gxOuterColor;
	}

	public Float getGxOuterWidth() {
		return gxOuterWidth;
	}

	public void setGxOuterWidth(Float gxOuterWidth) {
		this.gxOuterWidth = gxOuterWidth;
	}

	public Float getGxPhysicalWidth() {
		return gxPhysicalWidth;
	}

	public void setGxPhysicalWidth(Float gxPhysicalWidth) {
		this.gxPhysicalWidth = gxPhysicalWidth;
	}

	public String isGxLabelVisibility() {

		return (gxLabelVisibility) ? "1" : "0";
	}

	public void setGxLabelVisibility(Boolean gxLabelVisibility) {
		this.gxLabelVisibility = gxLabelVisibility;
	}

	public TreeNode<KMLNotation> getLinkedOutput() {

		TreeNode<KMLNotation> root = super.getLinkedOutput();

		if (this.getWidth() != null)
			root.addChild(new KMLNotation("width", Float.toString(this.getWidth()), false));
		if (this.getGxOuterColor() != null)
			root.addChild(new KMLNotation("outerColor", getGxOuterColor(), true));
		if (this.getGxOuterWidth() != null)
			root.addChild(new KMLNotation("outerWidth", Float.toString(this.getGxOuterWidth()), true));
		if (this.getGxPhysicalWidth() != null)
			root.addChild(new KMLNotation("physicalWidth", Float.toString(this.getGxPhysicalWidth()), true));

		if (this.gxLabelVisibility != null)
			root.addChild(new KMLNotation("labelVisibility", this.isGxLabelVisibility(), true));

		return root;
	}

}
