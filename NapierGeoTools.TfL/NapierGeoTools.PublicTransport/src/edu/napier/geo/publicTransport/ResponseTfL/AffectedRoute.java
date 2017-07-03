package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

public class AffectedRoute implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1302793574501099922L;
	private String id;
	private String lineId;
	private String routeCode;
	private String name;
	private String direction;
	private String lineString;
	private String originationName;
	private String destinationName;
	private RouteSectionNaptanEntrySequence routeSectionNaptanEntrySequence[];

	public AffectedRoute(String id, String lineId, String routeCode, String name, String direction,
			String lineString, String originationName, String destinationName,
			RouteSectionNaptanEntrySequence[] routeSectionNaptanEntrySequence) {
		this.id = id;
		this.lineId = lineId;
		this.routeCode = routeCode;
		this.name = name;
		this.direction = direction;
		this.lineString = lineString;
		this.originationName = originationName;
		this.destinationName = destinationName;
		this.routeSectionNaptanEntrySequence = routeSectionNaptanEntrySequence;
	}


	public String getId() {
		return id;
	}

	public String getLineId() {
		return lineId;
	}

	public String getRouteCode() {
		return routeCode;
	}

	public String getName() {
		return name;
	}

	public String getDirection() {
		return direction;
	}

	public String getLineString() {
		return lineString;
	}

	public String getOriginationName() {
		return originationName;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public RouteSectionNaptanEntrySequence[] getRouteSectionNaptanEntrySequence() {
		return routeSectionNaptanEntrySequence;
	}
	
	
}