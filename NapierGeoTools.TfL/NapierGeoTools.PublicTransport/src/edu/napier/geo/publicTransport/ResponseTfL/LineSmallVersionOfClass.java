package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

public class LineSmallVersionOfClass implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2705538284996131199L;
	private String id;
	private String name;
	private String uri;
	private String fullName;
	private String type;
	private Crowding crowding;

	public LineSmallVersionOfClass(String id, String name, String uri, String fullName, String type,
			Crowding crowding) {
		this.id = id;
		this.name = name;
		this.uri = uri;
		this.fullName = fullName;
		this.type = type;
		this.crowding = crowding;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getUri() {
		return uri;
	}

	public String getFullName() {
		return fullName;
	}

	public String getType() {
		return type;
	}

	public Crowding getCrowding() {
		return crowding;
	}

	
}