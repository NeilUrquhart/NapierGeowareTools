package edu.napier.geo.tfl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.HashMap;

import edu.napier.geo.common.Journey;
import edu.napier.geo.common.JourneyFactory;
import edu.napier.geo.common.Location;

/**
 * 
 * @author Jan-Niklas Keiner - Modified by Neil Urquhart
 * 2017/03/10
 *
 *Fascade Class for the Public Transport API
 */

public class TransportForLondonFacade implements JourneyFactory{

	private TransportForLondon publicTransport;
	private JourneyFactory _alternative;

	//Constructor
	/**
	 * Constructor to create an object of this class
	 */
	public TransportForLondonFacade(String TfLkey) {
		this.publicTransport = new TransportForLondon(TfLkey);
	}



	// Creating Response Java Object from request
	/**
	 * This method creates a POJO (Plain old java object) ResponseTfl for
	 * Journeys from and to a Location and with an ArrayList of Strings with the
	 * user preferences (must be in the format, that TfL requires:
	 * "variable=value" for most variables. All Variables that TfL provides can
	 * be used. Then it sets the response object of this object to the new given
	 * response object from TfL.
	 * 
	 * @param from
	 *            Location object
	 * @param to
	 *            Location object
	 * @param userPreferences
	 *            Strings must be in the format that the TfL API requires
	 *            ("variable=value" for most of the variables).
	 * @throws Exception
	 *             Exception of setResponseJavaObject()
	 */
	public void createResponseJavaObject(Location from, Location to,
			ArrayList<String> userPreferences) throws Exception {
		if (this.publicTransport != null)
			publicTransport.createResponseJavaObject(from, to, userPreferences);
	}

	/**
	 * creates the POJO (plain old Java object) response object for a Journey
	 * from and to a given Location. Then it sets the response object of this
	 * object to the new given response object from TfL.
	 * 
	 * @param from
	 *            Location object
	 * @param to
	 *            Location object
	 * @throws Exception
	 *             Exception of setResponseJavaOBject()
	 */
	public void createResponseJavaObject(Location from, Location to)
			throws Exception {
		ArrayList<String> prefs = new ArrayList<String>();
		
		if (this.publicTransport != null)
			publicTransport.createResponseJavaObject(from, to,prefs);
	}




	public ArrayList<Journey> getResponseJavaObject() {
		if (this.publicTransport != null)
			return publicTransport.getResponseJavaObject();
		return null;
	}

	
  

	public ArrayList<Journey> getAllJourneys() {
		if (this.publicTransport != null)
			return publicTransport.getResponseJavaObject();
		return null;
	}

	//Methods for Journey Factory

	@Override
	public Journey[] getJourney(Location start, Location finish) {
		try{
			createResponseJavaObject(start, finish);
		}catch(Exception e){
			e.printStackTrace();
			return _alternative.getJourney(start, finish);
			
		}
		
	
		for (Journey j: getAllJourneys()){
			j.setSource("Routing generated by the Transport For London API.");
		}
		
		if (getAllJourneys().size() ==0)
			return _alternative.getJourney(start, finish);
		return getAllJourneys().toArray(new Journey[0]);
	}

	@Override
	public Journey[] getJourney(Location start, Location finish,HashMap<String, Object> options) {
		// TODO Auto-generated method stub
		return getJourney(start,finish);
	}

	@Override
	public void setAlternative(JourneyFactory alternative) {
		_alternative = alternative;
		
	}

}
