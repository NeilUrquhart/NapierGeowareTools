package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;
import edu.napier.geo.common.Location;

public class StopPoint extends Location implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7716505488501264488L;
	private String naptanId;
	private String platformName;
	private String indicator;
	private String stopLetter;
	private String[] modes;
	private String icsCode;
	private String smsCode;
	private String stopType;
	private String stationNaptan;
	private String accessibilitySummary;
	private String hubNaptanCode;
	private Line lines[];
	private LineGroup lineGroup[];
	private LineModeGroup lineModeGroups[];
	private String fullName;
	private String naptanMode;
	private boolean status;
	private String id;
	private String url;
	private String commonName;
	private long distance;
	private String placeType;
	private AdditionalProperty additionalProperties[];
	private Children children[];
	private String[] childrenUrls;
	private Location location;

	public StopPoint(String naptanId, String platformName, String indicator,
			String stopLetter, String[] modes, String icsCode, String smsCode,
			String stopType, String stationNaptan, String accessibilitySummary,
			String hubNaptanCode, Line[] lines, LineGroup[] lineGroup,
			LineModeGroup[] lineModeGroups, String fullName, String naptanMode,
			boolean status, String id, String url, String commonName,
			long distance, String placeType,
			AdditionalProperty[] additionalProperties, Children[] children,
			String[] childrenUrls, double lat, double lon) {
		super(lat, lon, "PublicTransport");
		this.naptanId = naptanId;
		this.platformName = platformName;
		this.indicator = indicator;
		this.stopLetter = stopLetter;
		this.modes = modes;
		this.icsCode = icsCode;
		this.smsCode = smsCode;
		this.stopType = stopType;
		this.stationNaptan = stationNaptan;
		this.accessibilitySummary = accessibilitySummary;
		this.hubNaptanCode = hubNaptanCode;
		this.lines = lines;
		this.lineGroup = lineGroup;
		this.lineModeGroups = lineModeGroups;
		this.fullName = fullName;
		this.naptanMode = naptanMode;
		this.status = status;
		this.id = id;
		this.url = url;
		this.commonName = commonName;
		this.distance = distance;
		this.placeType = placeType;
		this.additionalProperties = additionalProperties;
		this.children = children;
		this.childrenUrls = childrenUrls;
		this.setSource("Public Transport - Stop Point");
		this.setDescription(commonName); // common
											// name,
											// because
											// user-set
											// name
											// in
											// request
											// is
											// set
											// as
											// commonName
											// in
											// response
	}

	public String getNaptanId() {
		return naptanId;
	}

	public String getPlatformName() {
		return platformName;
	}

	public String getIndicator() {
		return indicator;
	}

	public String getStopLetter() {
		return stopLetter;
	}

	public String[] getModes() {
		return modes;
	}

	public String getIcsCode() {
		return icsCode;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public String getStopType() {
		return stopType;
	}

	public String getStationNaptan() {
		return stationNaptan;
	}

	public String getAccessibilitySummary() {
		return accessibilitySummary;
	}

	public String getHubNaptanCode() {
		return hubNaptanCode;
	}

	public Line[] getLines() {
		return lines;
	}

	public LineGroup[] getLineGroup() {
		return lineGroup;
	}

	public LineModeGroup[] getLineModeGroups() {
		return lineModeGroups;
	}

	public String getFullName() {
		return fullName;
	}

	public String getNaptanMode() {
		return naptanMode;
	}

	public boolean isStatus() {
		return status;
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