package edu.napier.geo.easykml.filemanagement;


import org.jdom2.Document;



public class KMLFileCreator {

	private Document document;
	
	public KMLFileCreator() {
		initializeDocument();
	}
	
	private void initializeDocument(){
		document = new Document();
	}
	
	public Document getKMLDoument(){
		if(document != null){
			return document;
		}
		else{
			initializeDocument();
			return document;
		}
	}
	
}
