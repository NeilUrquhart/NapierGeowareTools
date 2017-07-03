package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

public class CycleHireDockingStationData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7124678843468596343L;
	private long originNumberOfBikes;
	private long destinationNumberOfBikes;
	private long originNumberOfEmptySlots;
	private long destinationNumberOfEmptySlots;
	private String originId;
	private String destinationId;

	public CycleHireDockingStationData(long originNumberOfBikes, long destinationNumberOfBikes,
			long originNumberOfEmptySlots, long destinationNumberOfEmptySlots, String originId, String destinationId) {
		this.originNumberOfBikes = originNumberOfBikes;
		this.destinationNumberOfBikes = destinationNumberOfBikes;
		this.originNumberOfEmptySlots = originNumberOfEmptySlots;
		this.destinationNumberOfEmptySlots = destinationNumberOfEmptySlots;
		this.originId = originId;
		this.destinationId = destinationId;
	}

	public long getOriginNumberOfBikes() {
		return originNumberOfBikes;
	}

	public long getDestinationNumberOfBikes() {
		return destinationNumberOfBikes;
	}

	public long getOriginNumberOfEmptySlots() {
		return originNumberOfEmptySlots;
	}

	public long getDestinationNumberOfEmptySlots() {
		return destinationNumberOfEmptySlots;
	}

	public String getOriginId() {
		return originId;
	}

	public String getDestinationId() {
		return destinationId;
	}

}