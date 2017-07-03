package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

public  class RouteSection implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8295145142793795517L;
	private  String routeCode;
    private  String name;
    private  String direction;
    private  String originationName;
    private  String destinationName;
    private  String originator;
    private  String destination;
    private  String serviceType;

    public RouteSection(String routeCode, String name, String direction, String originationName, String destinationName, String originator, String destination, String serviceType){
        this.routeCode = routeCode;
        this.name = name;
        this.direction = direction;
        this.originationName = originationName;
        this.destinationName = destinationName;
        this.originator = originator;
        this.destination = destination;
        this.serviceType = serviceType;
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

	public String getOriginationName() {
		return originationName;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public String getOriginator() {
		return originator;
	}

	public String getDestination() {
		return destination;
	}

	public String getServiceType() {
		return serviceType;
	}
}