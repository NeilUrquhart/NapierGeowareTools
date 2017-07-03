package edu.napier.geo.tfl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import edu.napier.geo.common.Journey;
import edu.napier.geo.common.Location;


/**
 * 2017/02/27
 * 
 * @author Jan-Niklas Keiner
 * 
 * Licensed under the Creative Commons BY-NC-SA License. 
 * https://creativecommons.org/licenses/by-nc-sa/3.0/legalcode			
 * The edu.napier.geo.publicTransport.ResponseTfl Package Code is based on a JSON to POJO converter 
 * (https://timboudreau.com/blog/json/read) under this license.
 * 
 *         Uses GSON, which is licensed under the Apache 2.0 License:
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 * 
 *         Objects of this class can get request TfL API and convert the
 *         response (JSON) to Pojo (plain old java object) and get information
 *         out of it. Furthermore it stores some information of each journey in
 *         the informationStorage object, which is stored locally and persistent
 *         (ObjectFileInput/Output stream) is imported again each time an object
 *         of this class is created (if there is an informationStorage.ser
 *         file). It uses Google GSON for converting JSON to POJO. It provides
 *         methods to get information out of the information storage. It
 *         provides methods to get information out of the last response Object,
 *         which was created by GSON out of a JSON from TfL. 
 *         
 *         
 *         
 *         
 *          
 */

/**
 * This API uses only Latitude and Longitude for Locations and no Altitude.
 *
 */

public class TransportForLondon {
	private ArrayList<Journey> responseJavaObject;
	private String _tflKey="";
	
	
	public TransportForLondon(String tflKey){
		this._tflKey = tflKey;
	}
	/**
	 * /**
	 * 
	 * @param string
	 *            of the JSON response from the API of the TfL public Transport
	 *            API
	 * @return ResponseTfL object of all information in the JSON
	 * 
	 *         creates Response object out of JSON String and gives the new
	 *         object to the informationStorage to update it.
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws JsonSyntaxException
	 */
	private ArrayList<Journey> getResponseObjectFromJSONString(String string,Location start, Location end)
			throws JsonSyntaxException, FileNotFoundException, IOException {

		if (string != null) {
			System.out.println(string);
			//ResponseTfl obj = gson.fromJson(string, ResponseTfl.class);
			return RequestAndGetJsonFromServer.parse(string,start, end);
		
		}
		System.out.println("responseString=null, no JSON code responded!");
		throw new IllegalArgumentException();

	}

	

	/**
	 * Gives back an Array of all TfLJourneys of the actual ResponseTfL Object
	 * 
	 * @return an Array of TfLJourney Objects of all TfLJourneys of the actual
	 *         ResponseTfL Object
	 */
	public ArrayList<Journey> getAllJourneys() {
		if (this.getResponseJavaObject() != null)
			return this.getResponseJavaObject();
		return null;
	}

	


	/**
	 * Gives back a TfLJourney of the response object with a given index.
	 * 
	 * @param journeyNumber
	 *            index of the Journey in the response Object
	 * @return a TfLJourney Object of the response object with a given index.
	 */
	public Journey getJourney(int journeyNumber) {
		if (this.getResponseJavaObject() != null) {
			return this.responseJavaObject.get(journeyNumber);
		}
		return null;
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
	public void createResponseJavaObject(Location from, Location to, ArrayList<String> userPreferences)
			throws Exception {
		if (from != null && to != null)
			this.setResponseJavaObject(getResponseObjectFromJSONString(RequestAndGetJsonFromServer.getJSON(from, to, userPreferences),from,to));
	}



	public ArrayList<Journey> getResponseJavaObject() {
		return responseJavaObject;
	}


	/**
	 * sets the ResponseJavaObject of this object to the given ResponseTfl
	 * Object
	 * 
	 * @param responseJavaObject
	 *            Object of ResponseTfl type (Response from TfL (JSON) converted
	 *            to POJO object)
	 */
	public void setResponseJavaObject(ArrayList<Journey> responseJavaObject) {
		this.responseJavaObject = responseJavaObject;
	}

	

}