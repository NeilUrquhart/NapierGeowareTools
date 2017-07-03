package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

public class Disruption implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2970422519281775298L;
	private String category;
	private String type;
	private String categoryDescription;
	private String description;
	private String additionalInfo;
	private String created;
	private String lastUpdate;
	private AffectedRoute affectedRoutes[];
	private AffectedStop affectedStops[];
	private boolean isBlocking;
	private boolean isWholeLine;
	private String closureText;

	public Disruption(String category, String type, String categoryDescription, String description,
			String additionalInfo, String created, String lastUpdate, AffectedRoute[] affectedRoutes,
			AffectedStop[] affectedStops, boolean isBlocking, boolean isWholeLine, String closureText) {
		this.category = category;
		this.type = type;
		this.categoryDescription = categoryDescription;
		this.description = description;
		this.additionalInfo = additionalInfo;
		this.created = created;
		this.lastUpdate = lastUpdate;
		this.affectedRoutes = affectedRoutes;
		this.affectedStops = affectedStops;
		this.isBlocking = isBlocking;
		this.isWholeLine = isWholeLine;
		this.closureText = closureText;
	}

	public String getCategory() {
		return category;
	}

	public String getType() {
		return type;
	}

	public String getCategoryDescription() {
		return categoryDescription;
	}

	public String getDescription() {
		return description;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public String getCreated() {
		return created;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

	public AffectedRoute[] getAffectedRoutes() {
		return affectedRoutes;
	}

	public AffectedStop[] getAffectedStops() {
		return affectedStops;
	}

	public boolean isBlocking() {
		return isBlocking;
	}

	public boolean isWholeLine() {
		return isWholeLine;
	}

	public String getClosureText() {
		return closureText;
	}
	
}