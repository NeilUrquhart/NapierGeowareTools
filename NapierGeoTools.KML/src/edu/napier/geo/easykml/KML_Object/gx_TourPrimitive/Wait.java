package edu.napier.geo.easykml.KML_Object.gx_TourPrimitive;

import edu.napier.geo.easykml.helperClasses.KMLNotation;
import edu.napier.geo.easykml.helperClasses.TreeNode;

public class Wait extends TourPrimitive{
	
	private Double duration;
	
	/**
	 * Wait lets the camera remain on the last point of the tour for a specific duration.
	 * For more information visit: 
	 * {@link: https://developers.google.com/kml/documentation/kmlreference#gxwait}
	 * 
	 * SoundCue is part of the Google extension pack.
	 * @param duration
	 */
	public Wait(double duration) {
		this.duration = duration;
	}
	
	public Double getDuration() {
		return duration;
	}
	public void setDuration(Double duration) {
		this.duration = duration;
	}
	
	public TreeNode<KMLNotation> getLinkedOutput (){
		
		TreeNode<KMLNotation> root = super.getLinkedOutput();

		root.data.setgExtenstion(true);
	
		if(this.getDuration() != null)root.addChild(new KMLNotation("duration", Double.toString(this.getDuration()), true));
	
		return root; 
	}
}
