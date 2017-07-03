package edu.napier.geo.easykml.KML_Object.gx_TourPrimitive;

import edu.napier.geo.easykml.helperClasses.KMLNotation;
import edu.napier.geo.easykml.helperClasses.TreeNode;

public class SoundCue extends TourPrimitive{


	private String soundAddress;
	private Double delayedStart;
	
	/**
	 * SoundCue contains a sound address which specifies a sound to play while a tour is running.
	 * For more information visit: 
	 * {@link: https://developers.google.com/kml/documentation/kmlreference#gxsoundcue}
	 * 
	 * SoundCue is part of the Google extension pack.
	 * @param soundAdress
	 */
	public SoundCue(String soundAdress) {
		this.soundAddress = soundAdress;
	}
	
	public String getSoundAddress() {
		return soundAddress;
	}
	public void setSoundAddress(String soundAddress) {
		this.soundAddress = soundAddress;
	}
	public Double getDelayedStart() {
		return delayedStart;
	}
	public void setDelayedStart(Double delayedStart) {
		this.delayedStart = delayedStart;
	}	
	
	public TreeNode<KMLNotation> getLinkedOutput (){
		
		TreeNode<KMLNotation> root = super.getLinkedOutput();

		root.data.setgExtenstion(true);

		
		if(this.getSoundAddress() != null)root.addChild(new KMLNotation("href", this.getSoundAddress(), false));
		if(this.getDelayedStart() != null)root.addChild(new KMLNotation("delayedStart", Double.toString(this.getDelayedStart()), true));

		return root; 
	}
}
