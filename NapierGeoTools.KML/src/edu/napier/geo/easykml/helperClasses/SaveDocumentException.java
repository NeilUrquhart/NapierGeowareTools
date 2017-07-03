package edu.napier.geo.easykml.helperClasses;

public class SaveDocumentException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5010392191248398891L;

	//Parameterless Constructor
    public SaveDocumentException() {
    	super("At least one KML objects needs to be created to save a document."); 
    }

}
