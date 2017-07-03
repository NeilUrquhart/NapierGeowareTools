package edu.napier.geo.easykml.facade;

import java.util.ArrayList;
import java.util.function.Function;

import org.jdom2.Document;

import edu.napier.geo.common.Journey;
import edu.napier.geo.common.Location;
import edu.napier.geo.common.MultiLegJourney;
import edu.napier.geo.easykml.KML_Object.feature.Placemark;
import edu.napier.geo.easykml.KML_Object.feature.Tour;
import edu.napier.geo.easykml.KML_Object.geometry.LineString;
import edu.napier.geo.easykml.KML_Object.geometry.LinearRing;
import edu.napier.geo.easykml.KML_Object.geometry.Point;
import edu.napier.geo.easykml.KML_Object.geometry.Polygon;
import edu.napier.geo.easykml.KML_Object.stylesector.Style;
import edu.napier.geo.easykml.filemanagement.KMLFileCreator;
import edu.napier.geo.easykml.filemanagement.KMLFileSaver;
import edu.napier.geo.easykml.filemanagement.KMLFileWriter;
import edu.napier.geo.easykml.helperClasses.SaveDocumentException;

public class EasyKMLCreator {

	private Document KML_Document;
	private KMLFileWriter fileWriter;

	/**
	 * Creates a new instance of the EasyKMLCreator.
	 */
	public EasyKMLCreator() {
	}

	/**
	 * createKMLDocment:
	 * 
	 * This function is used to create a JDOM XML document, which will be used
	 * to store KML code until the user saves the document by calling the
	 * function saveKMLDocument.
	 */
	private void createKMLDocument() {
		KML_Document = new KMLFileCreator().getKMLDoument();
		fileWriter = new KMLFileWriter(KML_Document);
	}

	/**
	 * createPlacemark invokes the creation of KML code for placemarks. As it
	 * expects a placemark it offers all the functionality of feature.
	 * 
	 * @param newPlacemark
	 */
	public void createPlacemark(Placemark newPlacemark) {
		if (!documentInitialized()) {
			this.createKMLDocument();
		}
		fileWriter.appendObject(newPlacemark);

	}
	
	/**
	 * createPlacemark invokes the creation of KML code for placemarks, in this case using a Location object. As it
	 * expects a placemark it offers all the functionality of feature.
	 * 
	 * @param newLocation
	 */
	public void createPlacemark(String name, Location newLocation) {
		Placemark newPlacemark = new Placemark(name, newLocation.getDescription(), new Point(newLocation));
		
		if (!documentInitialized()) {
			this.createKMLDocument();
		}
		fileWriter.appendObject(newPlacemark);

	}

	
	/**
	 * addPlacemarks invokes the creation of KML code for placemarks, in this case using a Location object. As it
	 * expects a placemark it offers all the functionality of feature.
	 * 
	 * @param newLocation
	 */
	public void addPlacemarks(ArrayList<Location> locations) {
		for (Location l : locations){
			this.createPlacemark(l.getDescription(), l);
		}

	}
	/**
	 * createSimplePoint invokes the creation of KML code for a simple point
	 * placemark. To create more advanced point placemarks, please use the
	 * createPlacemark(...)
	 * 
	 * @param newPoint
	 */
	public void createSimplePoint(Point newPoint) {
		if (!documentInitialized()) {
			this.createKMLDocument();
		}
		fileWriter.appendObject(new Placemark(newPoint));
	}

	
	/**
	 * createSimplePath invokes the creation of KML code for a simple path. To
	 * create more advanced paths, please use the createPlacemark(...)
	 * 
	 * @param newPath
	 */
	public void addJourney(Journey newJourney) {
		
		if (newJourney instanceof MultiLegJourney){
			MultiLegJourney mlj = (MultiLegJourney) newJourney;
			for (Journey leg : mlj.getLegs())
				this.addJourney(leg);
		}
		else{
			//See if the Path Arrtribute exists
			ArrayList<Location> locs = (ArrayList) newJourney.getAttribute(Journey.PATH);
			
			if (locs == null){
				//Draw a simple line from A to B
				locs = new ArrayList<Location>();
				locs.add(newJourney.getPointA());
				locs.add(newJourney.getPointB());

			}
			LineString newPath = new LineString(locs);
			createSimplePath(newPath);
		}
		
		
	}
	/**
	 * createSimplePath invokes the creation of KML code for a simple path. To
	 * create more advanced paths, please use the createPlacemark(...)
	 * 
	 * @param newPath
	 */
	public void createSimplePath(LineString newPath) {
		if (!documentInitialized()) {
			this.createKMLDocument();
		}
		fileWriter.appendObject(new Placemark(newPath));
	}

	/**
	 * createSimplePolygone invokes the creation of KML code for a simple
	 * polygon. To create more advanced polygons, please use the
	 * createPlacemark(...)
	 * 
	 * @param newPolygon
	 */
	public void createSimplePolygone(Polygon newPolygon) {
		if (!documentInitialized()) {
			this.createKMLDocument();
		}
		fileWriter.appendObject(new Placemark(newPolygon));
	}

	/**
	 * createStyle invokes the creation of KML code for styles.
	 * 
	 * @param newStyle
	 */
	public void createStyle(Style newStyle) {
		if (!documentInitialized()) {
			this.createKMLDocument();
		}
		fileWriter.appendObject(newStyle);
	}

	/**
	 * createStyle invokes the creation of KML code for Touring.
	 * 
	 * @param newTour
	 */
	public void createTour(Tour newTour) {
		if (!documentInitialized()) {
			this.createKMLDocument();
		}
		fileWriter.appendObject(newTour);
	}

	/**
	 * saveKMLDocument Invoke the saving of the document and finally produce a
	 * readable KML File. After saving a file the create methods can be used to
	 * define a new document
	 * 
	 * @param destination
	 *            - specifies the storage location of the file. Expects a string
	 *            similar to the following example:
	 *            C:\\Folder\\...\\Folder\\Filename
	 */
	// Invoke the saving of the document and finally produce a readable KML File
	public void saveKMLDocument(String destination) throws Exception{
		if (!documentInitialized()) throw new SaveDocumentException();
			new KMLFileSaver().saveFile(KML_Document, destination);

		KML_Document = null;
	}

	/**
	 * saveKMLDocument Invoke the saving of the document and finally produce a
	 * readable KML File. The file will be saved in the root path of the
	 * EasyKMLCreator. After saving a file the create methods can be used to
	 * define a new document
	 */
	public void saveKMLDocument() throws Exception{
		if (!documentInitialized()) throw new SaveDocumentException();

			new KMLFileSaver().saveFile(KML_Document, "");

		KML_Document = null;
	}

	// check if the KML document was initialized
	private boolean documentInitialized() {
		if (KML_Document != null)
			return true;
		else
			return false;
	}

}
