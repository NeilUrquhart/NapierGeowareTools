package edu.napier.geo.easykml.KML_Object;

import edu.napier.geo.easykml.helperClasses.KMLNotation;
import edu.napier.geo.easykml.helperClasses.TreeNode;

public abstract class KML_object {

	private String id;

	/**
	 * @return id - get id attribute, which allows unique identification of a KML
	 *         element
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id - set id attribute, which allows unique identification of a KML
	 *         element
	 */
	public void setId(String id) {
		this.id = id;
	}

	public TreeNode<KMLNotation> getLinkedOutput() {

		TreeNode<KMLNotation> root = new TreeNode<KMLNotation>(
				new KMLNotation(this.getClass().getSimpleName(), "", false));
		if (this.getId() != null)
			root.addChild(new KMLNotation("id", this.getId(), false));

		return root;
	}

}
