package edu.napier.geo.easykml.KML_Object.feature;

import edu.napier.geo.easykml.KML_Object.geometry.KML_Geometry;
import edu.napier.geo.easykml.helperClasses.KMLNotation;
import edu.napier.geo.easykml.helperClasses.TreeNode;

public class Placemark extends Feature {

	private KML_Geometry geometryObject;

	/**
	 * A Placemark is a child class from feature which has an associated
	 * geometry object. For more information visit: 
	 * {@link: https://developers.google.com/kml/documentation/kmlreference#placemark}
	 * 
	 * @param name
	 * @param description
	 * @param geometryObject
	 */
	public Placemark(String name, String description, KML_Geometry geometryObject) {
		setName(name);
		setDescription(description);
		this.geometryObject = geometryObject;
	}
	
	/**
	 * A Placemark is a child class from feature which has an associated
	 * geometry object. For more information visit: 
	 * {@link: https://developers.google.com/kml/documentation/kmlreference#placemark}
	 * 
	 * @param geometryObject
	 */
	public Placemark( KML_Geometry geometryObject) {
		this.geometryObject = geometryObject;
	}

	public KML_Geometry getPoint() {
		return geometryObject;
	}

	public void setPoint(KML_Geometry geometryObject) {
		this.geometryObject = geometryObject;
	}
	

	public TreeNode<KMLNotation> getLinkedOutput (){
		
		TreeNode<KMLNotation> root = super.getLinkedOutput();

		if(this.geometryObject != null)root.addTreeNode(geometryObject.getLinkedOutput());

		return root; 
	}
	

}
