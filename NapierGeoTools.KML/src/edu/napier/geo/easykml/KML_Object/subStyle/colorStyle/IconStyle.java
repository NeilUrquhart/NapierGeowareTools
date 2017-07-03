package edu.napier.geo.easykml.KML_Object.subStyle.colorStyle;

import edu.napier.geo.easykml.helperClasses.KMLNotation;
import edu.napier.geo.easykml.helperClasses.TreeNode;

public class IconStyle extends ColorStyle {

	/*
	 * Information area (Source: KML Reference Google):
	 * 
	 * <scale> Resizes the icon.
	 * 
	 * <heading> Direction (that is, North, South, East, West), in degrees.
	 * Default=0 (North). Values range from 0 to 360 degrees.
	 * 
	 * <Icon> 
	 * A custom Icon. In <IconStyle>, the only child element of <Icon> is
	 * <href>: 
	 * 		<href>: An HTTP address or a local file specification used to
	 * 		load an icon.
	 */

	private Float scale;
	private Float heading; // Value range from 0 to 360
	private String iconHttpAddress;

	/**
	 * IconStyle specifies how point Placemarks are drawn.
	 * For more information visit: 
	 * {@link: https://developers.google.com/kml/documentation/kmlreference#iconstyle}
	 */
	public IconStyle() {
		// TODO Auto-generated constructor stub
	}
	
	public Float getScale() {
		return scale;
	}

	public void setScale(Float scale) {
		this.scale = scale;
	}

	public Float getHeading() {
		return heading;
	}

	public void setHeading(Float heading) {
		if (heading > 360 || heading < 0) this.heading = 0.0f;
		else this.heading = heading;
	}

	public String getIconHttpAddress() {
		return iconHttpAddress;
	}

	public void setIconHttpAddress(String iconHttpAddress) {
		this.iconHttpAddress = iconHttpAddress;
	}

	public TreeNode<KMLNotation> getLinkedOutput (){
		
		TreeNode<KMLNotation> root = super.getLinkedOutput();
	
		if(this.getScale() != null)root.addChild(new KMLNotation("scale", Float.toString(this.getScale()), false));
		if(this.getHeading() != null)root.addChild(new KMLNotation("heading", Float.toString(this.getHeading()), false));
		if(this.getIconHttpAddress() != null)root.addChild(new KMLNotation("Icon", null, false)).addChild(new KMLNotation("href", this.getIconHttpAddress(), false));

		return root; 
	}

	
}
