package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

public class ValidityPeriod implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1618769253513354475L;
	private String fromDate;
	private String toDate;
	private boolean isNow;

	public ValidityPeriod(String fromDate, String toDate, boolean isNow) {
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.isNow = isNow;
	}

	public String getFromDate() {
		return fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public boolean isNow() {
		return isNow;
	}
}