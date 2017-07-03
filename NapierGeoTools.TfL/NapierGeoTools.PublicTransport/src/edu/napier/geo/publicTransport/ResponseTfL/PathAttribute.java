package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

public class PathAttribute implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2145284630463355389L;
	private String name;
	private String value;

	public PathAttribute(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
}