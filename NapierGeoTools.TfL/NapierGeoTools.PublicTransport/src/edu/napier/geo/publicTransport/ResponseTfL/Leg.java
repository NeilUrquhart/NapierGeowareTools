package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;
import edu.napier.geo.common.Location;

public class Leg implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2787436604149575957L;
	private int duration;
	private String speed;
	private Instruction instruction;
	private Obstacle obstacles[];
	private String departureTime;
	private String arrivalTime;
	private Location departurePoint;
	private Location arrivalPoint;
	private Path path;
	private RouteOption routeOptions[];
	private Mode mode;
	private Disruption disruptions[];
	private PlannedWork plannedWorks[];
	private long distance;
	private boolean isDisrupted;
	private boolean hasFixedLocations;

	public Leg(int duration, String speed, Instruction instruction, Obstacle[] obstacles, String departureTime,
			String arrivalTime, Location departurePoint, Location arrivalPoint, Path path,
			RouteOption[] routeOptions, Mode mode, Disruption[] disruptions, PlannedWork[] plannedWorks, long distance,
			boolean isDisrupted, boolean hasFixedLocations) {
		this.duration = duration;
		this.speed = speed;
		this.instruction = instruction;
		this.obstacles = obstacles;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		this.departurePoint = departurePoint;
		this.arrivalPoint = arrivalPoint;
		this.path = path;
		this.routeOptions = routeOptions;
		this.mode = mode;
		this.disruptions = disruptions;
		this.plannedWorks = plannedWorks;
		this.distance = distance;
		this.isDisrupted = isDisrupted;
		this.hasFixedLocations = hasFixedLocations;
	}

	public int getDuration() {
		return duration;
	}

	public String getSpeed() {
		return speed;
	}

	public Instruction getInstruction() {
		return instruction;
	}

	public Obstacle[] getObstacles() {
		return obstacles;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public String getArrivalTime() {
		return arrivalTime;
	}

	public Location getDeparturePoint() {
		return departurePoint;
	}

	public Location getArrivalPoint() {
		return arrivalPoint;
	}

	public Path getPath() {
		return path;
	}

	public RouteOption[] getRouteOptions() {
		return routeOptions;
	}

	public Mode getMode() {
		return mode;
	}

	public Disruption[] getDisruptions() {
		return disruptions;
	}

	public PlannedWork[] getPlannedWorks() {
		return plannedWorks;
	}

	public long getDistance() {
		return distance;
	}

	public boolean isDisrupted() {
		return isDisrupted;
	}

	public boolean isHasFixedLocations() {
		return hasFixedLocations;
	}

}