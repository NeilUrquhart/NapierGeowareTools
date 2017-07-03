package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

public class TflJourney implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3904682688521542643L;
	private String startDateTime;
	private int durationMinutes;
	private String arrivalDateTime;
	private Leg legs[];

	public TflJourney(String startDateTime, int duration, String arrivalDateTime, Leg[] legs) {
		this.startDateTime = startDateTime;
		this.durationMinutes = duration;
		this.arrivalDateTime = arrivalDateTime;
		this.legs = legs;
	}

	public String getStartDateTime() {
		return startDateTime;
	}

	public int getDurationMinutes() {
		return durationMinutes;
	}

	public String getArrivalDateTime() {
		return arrivalDateTime;
	}

	public Leg[] getLegs() {
		return legs;
	}
}