package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

public class PlannedWork implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4895469514888889614L;
	private String id;
	private String description;
	private String createdDateTime;
	private String lastUpdateDateTime;

	public PlannedWork(String id, String description, String createdDateTime, String lastUpdateDateTime) {
		this.id = id;
		this.description = description;
		this.createdDateTime = createdDateTime;
		this.lastUpdateDateTime = lastUpdateDateTime;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public String getCreatedDateTime() {
		return createdDateTime;
	}

	public String getLastUpdateDateTime() {
		return lastUpdateDateTime;
	}
}