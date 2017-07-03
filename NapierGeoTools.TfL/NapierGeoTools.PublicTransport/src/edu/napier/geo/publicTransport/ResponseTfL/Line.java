package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

public class Line implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5052037762366090470L;
	private String id;
	private String name;
	private String modeName;
	private Disruption disruptions[];
	private String created;
	private String modified;
	private LineStatuse lineStatuses[];
	private RouteSection routeSections[];
	private ServiceType serviceTypes[];
	private Crowding crowding;

	public Line(String id, String name, String modeName, Disruption[] disruptions, String created, String modified,
			LineStatuse[] lineStatuses, RouteSection[] routeSections, ServiceType[] serviceTypes, Crowding crowding) {
		this.id = id;
		this.name = name;
		this.modeName = modeName;
		this.disruptions = disruptions;
		this.created = created;
		this.modified = modified;
		this.lineStatuses = lineStatuses;
		this.routeSections = routeSections;
		this.serviceTypes = serviceTypes;
		this.crowding = crowding;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getModeName() {
		return modeName;
	}

	public Disruption[] getDisruptions() {
		return disruptions;
	}

	public String getCreated() {
		return created;
	}

	public String getModified() {
		return modified;
	}

	public LineStatuse[] getLineStatuses() {
		return lineStatuses;
	}

	public RouteSection[] getRouteSections() {
		return routeSections;
	}

	public ServiceType[] getServiceTypes() {
		return serviceTypes;
	}

	public Crowding getCrowding() {
		return crowding;
	}

}