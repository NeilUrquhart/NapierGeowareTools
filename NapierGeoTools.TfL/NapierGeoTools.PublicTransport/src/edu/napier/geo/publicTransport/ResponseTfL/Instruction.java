package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

public class Instruction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5874347722989890899L;
	private String summary;
	private String detailed;
	private Step steps[];

	public Instruction(String summary, String detailed, Step[] steps) {
		this.summary = summary;
		this.detailed = detailed;
		this.steps = steps;
	}

	public String getSummary() {
		return summary;
	}

	public String getDetailed() {
		return detailed;
	}

	public Step[] getSteps() {
		return steps;
	}

}