package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;
import edu.napier.geo.common.Location;

public class Step implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5809255402033736238L;
	private String description;
	private String turnDirection;
	private String streetName;
	private long distance;
	private long cumulativeDistance;
	private long skyDirection;
	private String skyDirectionDescription;
	private long cumulativeTravelTime;
	private Location location;
	private PathAttribute pathAttribute;
	private String descriptionHeading;
	private String trackType;

	public Step(String description, String turnDirection, String streetName, long distance, long cumulativeDistance,
			long skyDirection, String skyDirectionDescription, long cumulativeTravelTime, double latitude,
			double longitude, PathAttribute pathAttribute, String descriptionHeading, String trackType) {
		this.description = description;
		this.turnDirection = turnDirection;
		this.streetName = streetName;
		this.distance = distance;
		this.cumulativeDistance = cumulativeDistance;
		this.skyDirection = skyDirection;
		this.skyDirectionDescription = skyDirectionDescription;
		this.cumulativeTravelTime = cumulativeTravelTime;
		this.location = new Location(latitude, longitude, "PublicTransport - Step");
		this.pathAttribute = pathAttribute;
		this.descriptionHeading = descriptionHeading;
		this.trackType = trackType;
	}

	public String getDescription() {
		return description;
	}

	public String getTurnDirection() {
		return turnDirection;
	}

	public String getStreetName() {
		return streetName;
	}

	public long getDistance() {
		return distance;
	}

	public long getCumulativeDistance() {
		return cumulativeDistance;
	}

	public long getSkyDirection() {
		return skyDirection;
	}

	public String getSkyDirectionDescription() {
		return skyDirectionDescription;
	}

	public long getCumulativeTravelTime() {
		return cumulativeTravelTime;
	}

	public Location getLocation() {
		return location;
	}

	public PathAttribute getPathAttribute() {
		return pathAttribute;
	}

	public String getDescriptionHeading() {
		return descriptionHeading;
	}

	public String getTrackType() {
		return trackType;
	}
}