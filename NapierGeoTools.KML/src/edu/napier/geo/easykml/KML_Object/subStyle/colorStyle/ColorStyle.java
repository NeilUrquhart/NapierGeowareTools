package edu.napier.geo.easykml.KML_Object.subStyle.colorStyle;

import java.awt.Color;

import edu.napier.geo.easykml.KML_Object.KML_object;
import edu.napier.geo.easykml.helperClasses.KMLNotation;
import edu.napier.geo.easykml.helperClasses.TreeNode;

public abstract class ColorStyle extends KML_object{

	/*
	 * information area (Source: KML Reference Google): Every style object can
	 * choose a specific color. The attribute colormode = random is used to
	 * select a random color.
	 */

	private Color color;
	private Boolean colorModeActivated; // false = normal true = random
	private String colorMode;
	private Integer transparency = 100; // default value 100 percent. The object is
									// completely visible
	
	
	public ColorStyle getColorStyleObject(){
		return this;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Boolean isColorModeActivated() {
		return colorModeActivated;
	}

	public void setColorModeActivated(Boolean colorModeActivated) {
		if (colorModeActivated == true)
			this.colorMode = "random";
		else
			this.colorMode = "normal";

		this.colorModeActivated = colorModeActivated;
	}

	public String getColorMode() {
		return colorMode;
	}

	public Integer getTransparency() {
		return transparency;
	}

	public void setTransparency(Integer transparency) {
		if (transparency > 100 || transparency < 0)
			this.transparency = 100; // default

		else
			this.transparency = transparency;
	}

	/*
	 * <color> 
	 * Color and opacity (alpha) values are expressed in hexadecimal
	 * notation. The range of values for any one color is 0 to 255 (00 to ff).
	 * For alpha, 00 is fully transparent and ff is fully opaque. The order of
	 * expression is aabbggrr, where aa=alpha (00 to ff); bb=blue (00 to ff);
	 * gg=green (00 to ff); rr=red (00 to ff). For example, if you want to apply
	 * a blue color with 50 percent opacity to an overlay, you would specify the
	 * following: <color>7fff0000</color>, where alpha=0x7f, blue=0xff,
	 * green=0x00, and red=0x00.
	 */
	public String colorInHexstring() {
		if (this.color != null) {
			int cache = (int) (Math.round(this.transparency * 2.55));

			String hexString = Integer.toHexString(cache) + String.format("%02x", color.getBlue() & 0x00FF)
					+ String.format("%02x", color.getGreen() & 0x00FF) + String.format("%02x", color.getRed() & 0x00FF);
			return hexString;
		}
		return "ffffffff";
	}
	
	public TreeNode<KMLNotation> getLinkedOutput (){
		
		TreeNode<KMLNotation> root = super.getLinkedOutput();
	
		if(this.getColor() != null)root.addChild(new KMLNotation("color", colorInHexstring(), false));
		if(this.getColorMode() != null)root.addChild(new KMLNotation("colorMode", this.getColorMode(), false));


		return root; 
	}

}
