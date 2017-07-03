package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

public class PassengerFlow implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8553733278250893582L;
	private String timeSlice;
	private long value;

	public PassengerFlow(String timeSlice, long value) {
		this.timeSlice = timeSlice;
		this.value = value;
	}

	public String getTimeSlice() {
		return timeSlice;
	}

	public long getValue() {
		return value;
	}
}