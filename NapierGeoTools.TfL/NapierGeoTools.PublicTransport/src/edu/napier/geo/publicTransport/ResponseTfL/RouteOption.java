package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

public class RouteOption implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1241781746601241299L;
	private String id;
	private String name;
	private String[] directions;
	private LineIdentifier lineIdentifier;

	public RouteOption(String id, String name, String[] directions, LineIdentifier lineIdentifier) {
		this.id = id;
		this.name = name;
		this.directions = directions;
		this.lineIdentifier = lineIdentifier;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String[] getDirections() {
		return directions;
	}

	public LineIdentifier getLineIdentifier() {
		return lineIdentifier;
	}
}