package edu.napier.geo.easykml.KML_Object.feature;

import edu.napier.geo.easykml.KML_Object.Playlist;
import edu.napier.geo.easykml.helperClasses.KMLNotation;
import edu.napier.geo.easykml.helperClasses.TreeNode;

public class Tour extends Feature {
	
	private Playlist playlist;
	
	/**
	 * A Tour is a child class from feature which contain a single Playlist instance.
	 * For more information visit: 
	 * {@link: https://developers.google.com/kml/documentation/kmlreference#gxtour}
	 * 
	 * Tour is part of the Google extension pack.
	 * @param playlist
	 */
	public Tour(Playlist playlist) {
		this.playlist = playlist;
	}
	
	public Playlist getPlaylist() {
		return playlist;
	}
	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}
	
	public TreeNode<KMLNotation> getLinkedOutput (){
		
		TreeNode<KMLNotation> root = super.getLinkedOutput();

		root.data.setgExtenstion(true);
		
		if(playlist != null)root.addTreeNode(playlist.getLinkedOutput());

		return root; 
	}
	
}
