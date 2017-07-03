package edu.napier.geo.easykml.KML_Object.gx_TourPrimitive;

import edu.napier.geo.easykml.KML_Object.abstractView.AbstractView;
import edu.napier.geo.easykml.helperClasses.KMLNotation;
import edu.napier.geo.easykml.helperClasses.TreeNode;

public class FlyTo extends TourPrimitive{
	
	public final static String FLYTOMODE_SMOOTH = "smooth"; 
	public final static String FLYTOMODE_BOUNCE = "bounce"; 


	private Double duration;
	private String flyToMode;
	private AbstractView abstractView;
	
	
	/**
	 * FlyTo specifies a point on the earth to which the camera will fly during a Tour.
	 * For more information visit: 
	 * {@link: https://developers.google.com/kml/documentation/kmlreference#gxflyto}
	 * 
	 * FlyTo is part of the Google extension pack.
	 * @param duration
	 * @param abstractView
	 */
	public FlyTo(Double duration, AbstractView abstractView) {
		this.duration = duration;
		this.abstractView = abstractView;
	}
	

	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration) {
		this.duration = duration;
	}
	public String getFlyToMode() {
		return flyToMode;
	}
	public void setFlyToMode(String flyToMode) {
		this.flyToMode = flyToMode;
	}
	public AbstractView getAbstractView() {
		return abstractView;
	}
	public void setAbstractView(AbstractView abstractView) {
		this.abstractView = abstractView;
	}
	
	public TreeNode<KMLNotation> getLinkedOutput (){
		
		TreeNode<KMLNotation> root = super.getLinkedOutput();

		root.data.setgExtenstion(true);

		
		if(this.getDuration() != null)root.addChild(new KMLNotation("duration", Double.toString(this.getDuration()), true));
		if(this.getFlyToMode() != null)root.addChild(new KMLNotation("flyToMode", this.getFlyToMode(), true));
		if(abstractView != null)root.addTreeNode(abstractView.getLinkedOutput());

		return root; 
	}
	
}
