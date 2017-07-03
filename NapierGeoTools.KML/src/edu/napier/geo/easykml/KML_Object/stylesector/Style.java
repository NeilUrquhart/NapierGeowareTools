package edu.napier.geo.easykml.KML_Object.stylesector;

import edu.napier.geo.easykml.KML_Object.KML_object;
import edu.napier.geo.easykml.KML_Object.subStyle.colorStyle.IconStyle;
import edu.napier.geo.easykml.KML_Object.subStyle.colorStyle.LabelStyle;
import edu.napier.geo.easykml.KML_Object.subStyle.colorStyle.LineStyle;
import edu.napier.geo.easykml.KML_Object.subStyle.colorStyle.PolyStyle;
import edu.napier.geo.easykml.helperClasses.KMLNotation;
import edu.napier.geo.easykml.helperClasses.TreeNode;

public class Style extends KML_object{
	
	private IconStyle iconStyle;
	private LabelStyle labelStyle;
	private LineStyle lineStyle;
	private PolyStyle polyStyle;
	

	/**
	 * Style may contain the substyles IconStyle, LabelStyle, LineStyle and PolyStyle.
	 * For more information visit: 
	 * {@link: https://developers.google.com/kml/documentation/kmlreference#style}
	 */
	public Style() {
		// TODO Auto-generated constructor stub
	}
	
	public IconStyle getIconStyle() {
		return iconStyle;
	}
	public void setIconStyle(IconStyle iconStyle) {
		this.iconStyle = iconStyle;
	}
	public LabelStyle getLabelStyle() {
		return labelStyle;
	}
	public void setLabelStyle(LabelStyle labelStyle) {
		this.labelStyle = labelStyle;
	}
	public LineStyle getLineStyle() {
		return lineStyle;
	}
	public void setLineStyle(LineStyle lineStyle) {
		this.lineStyle = lineStyle;
	}
	public PolyStyle getPolyStyle() {
		return polyStyle;
	}
	public void setPolyStyle(PolyStyle polyStyle) {
		this.polyStyle = polyStyle;
	}
	
	public TreeNode<KMLNotation> getLinkedOutput (){
		
		TreeNode<KMLNotation> root = super.getLinkedOutput();
			
		if(iconStyle != null)root.addTreeNode(iconStyle.getLinkedOutput());
		if(labelStyle != null)root.addTreeNode(labelStyle.getLinkedOutput());
		if(lineStyle != null)root.addTreeNode(lineStyle.getLinkedOutput());
		if(polyStyle != null)root.addTreeNode(polyStyle.getLinkedOutput());

		return root; 
	}
	
}
