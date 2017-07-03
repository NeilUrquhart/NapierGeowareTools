package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

public class RouteSectionNaptanEntrySequence implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7946294528892609890L;
	private long ordinal;
	private StopPoint stopPoint;

	public RouteSectionNaptanEntrySequence(long ordinal, StopPoint stopPoint) {
		this.ordinal = ordinal;
		this.stopPoint = stopPoint;
	}

	public long getOrdinal() {
		return ordinal;
	}

	public StopPoint getStopPoint() {
		return stopPoint;
	}

}