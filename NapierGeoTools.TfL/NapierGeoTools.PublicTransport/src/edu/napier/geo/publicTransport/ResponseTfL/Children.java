package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;
import edu.napier.geo.common.Location;

public class Children implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2313941451688401230L;
	private String id;
	private String url;
	private String commonName;
	private long distance;
	private String placeType;
	private AdditionalProperty additionalProperties[];
	private Children children[];
	private String[] childrenUrls;
	private Location location;

	public Children(String id, String url, String commonName, long distance, String placeType,
			AdditionalProperty[] additionalProperties, Children[] children, String[] childrenUrls, double lat,
			double lon) {
		this.id = id;
		this.url = url;
		this.commonName = commonName;
		this.distance = distance;
		this.placeType = placeType;
		this.additionalProperties = additionalProperties;
		this.children = children;
		this.childrenUrls = childrenUrls;
		this.location = new Location(lat, lon, "PublicTransport - Children");
		this.location.setDescription(commonName); // common name, because
													// user-set name in request
													// is set as commonName in
													// response
	}

	public String getId() {
		return id;
	}

	public String getUrl() {
		return url;
	}

	public String getCommonName() {
		return commonName;
	}

	public long getDistance() {
		return distance;
	}

	public String getPlaceType() {
		return placeType;
	}

	public AdditionalProperty[] getAdditionalProperties() {
		return additionalProperties;
	}

	public Children[] getChildren() {
		return children;
	}

	public String[] getChildrenUrls() {
		return childrenUrls;
	}

	public Location getLocation() {
		return location;
	}

	
}