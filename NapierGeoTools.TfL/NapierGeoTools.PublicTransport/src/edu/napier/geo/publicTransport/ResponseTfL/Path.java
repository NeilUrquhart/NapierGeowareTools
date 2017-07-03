package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

public class Path implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 724258036827721261L;
	private String lineString;
	private StopPointInPath stopPoints[];
	private Elevation elevation[];

	public Path(String lineString, StopPointInPath[] stopPoints, Elevation[] elevation) {
		this.lineString = lineString;
		this.stopPoints = stopPoints;
		this.elevation = elevation;
	}

	public String getLineString() {
		return lineString;
	}

	public StopPointInPath[] getStopPoints() {
		return stopPoints;
	}

	public Elevation[] getElevation() {
		return elevation;
	}
}