package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

              
public class LineGroup implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8488252049693950358L;
	private String naptanIdReference;
	private String stationAtcoCode;
	private String[] lineIdentifier;

	public LineGroup(String naptanIdReference, String stationAtcoCode, String[] lineIdentifier) {
		this.naptanIdReference = naptanIdReference;
		this.stationAtcoCode = stationAtcoCode;
		this.lineIdentifier = lineIdentifier;
	}

	public String getNaptanIdReference() {
		return naptanIdReference;
	}

	public String getStationAtcoCode() {
		return stationAtcoCode;
	}

	public String[] getLineIdentifier() {
		return lineIdentifier;
	}
}