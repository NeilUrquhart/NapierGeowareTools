package edu.napier.geo.easykml.KML_Object.subStyle.colorStyle;

import edu.napier.geo.easykml.helperClasses.KMLNotation;
import edu.napier.geo.easykml.helperClasses.TreeNode;

public class PolyStyle extends ColorStyle {
	
	/*
	 * Information area (Source: KML Reference Google):
	 * 
	 * <fill> 
	 * Boolean value. Specifies whether to fill the polygon.
	 * 
	 * <outline> 
	 * Boolean value. Specifies whether to outline the polygon. Polygon outlines use the current LineStyle.
	 */
	
	private Boolean fillPolygon;
	private Boolean outLine;
	
	/**
	 * PolyStyle specifies how polygons are drawn.
	 * For more information visit: 
	 * {@link: https://developers.google.com/kml/documentation/kmlreference#polystyle}
	 */
	public PolyStyle() {
		// TODO Auto-generated constructor stub
	}
	
	public String isFillPolygon() {
		return (fillPolygon) ? "1" : "0";
	}
	public void setFillPolygon(Boolean fillPolygon) {
		this.fillPolygon = fillPolygon;
	}
	public String isOutLine() {
		return (outLine) ? "1" : "0";
	}
	public void setOutLine(Boolean outLine) {
		this.outLine = outLine;
	} 
	
	
	public TreeNode<KMLNotation> getLinkedOutput (){
		
		TreeNode<KMLNotation> root = super.getLinkedOutput();
	
		if(this.fillPolygon != null)root.addChild(new KMLNotation("fill", this.isFillPolygon(), false));
		if(this.outLine != null)root.addChild(new KMLNotation("outline", this.isOutLine(), false));

		
		return root; 
	}
	

}
