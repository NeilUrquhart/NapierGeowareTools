package edu.napier.geo.easykml.KML_Object.timePrimitive;

import edu.napier.geo.easykml.helperClasses.KMLNotation;
import edu.napier.geo.easykml.helperClasses.TreeNode;

public class TimeStamp extends TimePrimitive{

	private Time timeStamp;

	
	/**
	 * TimeStamp represents a single moment in time.
	 * For more information visit: 
	 * {@link: https://developers.google.com/kml/documentation/kmlreference#timestamp}
	 */
	public TimeStamp(Time timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public Time getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Time timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public TreeNode<KMLNotation> getLinkedOutput (){
		
		TreeNode<KMLNotation> root = super.getLinkedOutput();

		if(this.getTimeStamp() != null)root.addChild(new KMLNotation("when", this.getTimeStamp().getFormattedString(), false));

		return root; 
	}
	
}
