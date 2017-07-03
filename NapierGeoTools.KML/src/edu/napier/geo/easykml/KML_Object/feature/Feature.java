package edu.napier.geo.easykml.KML_Object.feature;

import edu.napier.geo.easykml.KML_Object.KML_object;
import edu.napier.geo.easykml.KML_Object.abstractView.AbstractView;
import edu.napier.geo.easykml.KML_Object.stylesector.Style;
import edu.napier.geo.easykml.KML_Object.timePrimitive.TimePrimitive;
import edu.napier.geo.easykml.helperClasses.KMLNotation;
import edu.napier.geo.easykml.helperClasses.TreeNode;

public abstract class Feature extends KML_object {
	
	private String name;
	private Boolean visibility;
	private Boolean open; 
	private String description; 
	private AbstractView abstractView;
	private TimePrimitive timePrimitive;
	private String styleURL;
	private Style style;

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String isVisibility() {
		return (visibility) ? "1" : "0";
	}
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}
	public String isOpen() {
		return (open) ? "1" : "0";
	}
	public void setOpen(boolean open) {
		this.open = open;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public AbstractView getAbstractView() {
		return abstractView;
	}
	public void setAbstractView(AbstractView abstractView) {
		this.abstractView = abstractView;
	}
	public TimePrimitive getTimePrimitive() {
		return timePrimitive;
	}
	public void setTimePrimitive(TimePrimitive timePrimitive) {
		this.timePrimitive = timePrimitive;
	}
	public String getStyleURL() {
		return styleURL;
	}
	public void setStyleURL(String styleURL) {
		this.styleURL = styleURL;
	}
	public Style getStyle() {
		return style;
	}
	public void setStyle(Style style) {
		this.style = style;
	}
	
	
	public TreeNode<KMLNotation> getLinkedOutput (){
		
		
		TreeNode<KMLNotation> root = super.getLinkedOutput();

		if(this.getName() != null)root.addChild(new KMLNotation("name", this.getName(), false));
		if(this.visibility != null)root.addChild(new KMLNotation("visibility", this.isVisibility(), false));
		if(this.open != null)root.addChild(new KMLNotation("open", this.isOpen(), false));
		if(this.getDescription() != null)root.addChild(new KMLNotation("description", this.getDescription(), false));
		if(getAbstractView() != null)root.addTreeNode(getAbstractView().getLinkedOutput()) ;
		if(getTimePrimitive() != null)root.addTreeNode(getTimePrimitive().getLinkedOutput());
		if(getStyle() != null)root.addTreeNode(getStyle().getLinkedOutput());
		if(this.getStyleURL() != null)root.addChild(new KMLNotation("styleUrl", this.getStyleURL(), false));


		return root; 
	}
}
