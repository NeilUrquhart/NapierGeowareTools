package edu.napier.geo.easykml.KML_Object;

import java.util.ArrayList;

import edu.napier.geo.easykml.KML_Object.gx_TourPrimitive.TourPrimitive;
import edu.napier.geo.easykml.helperClasses.KMLNotation;
import edu.napier.geo.easykml.helperClasses.TreeNode;

public class Playlist extends KML_object {

	private ArrayList<TourPrimitive> actionList = new ArrayList<>();

	/**
	 * The PlayList class may contain a infinite number of TourPrimitve objects,
	 * which are supposed to be executed in the Tour. 
	 * PlayList is part of the Google extension pack.
	 */
	public Playlist() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns the ArrayList which contains all the TourPrimitive objects that
	 * are supposed to be executed in the Tour
	 * 
	 * @return actionList
	 */
	public ArrayList<TourPrimitive> getActionList() {
		return actionList;
	}

	/**
	 * Adds a TourPrimitice instance to the playlist.
	 * 
	 * @param primitiveAction
	 */
	public void addPrimitiveAction(TourPrimitive primitiveAction) {
		actionList.add(primitiveAction);
	}

	public TreeNode<KMLNotation> getLinkedOutput() {

		TreeNode<KMLNotation> root = super.getLinkedOutput();

		root.data.setgExtenstion(true);

		if (actionList.size() > 0) {
			for (TourPrimitive tourPrimitive : actionList) {
				root.addTreeNode(tourPrimitive.getLinkedOutput());
			}
		}

		return root;
	}

}
