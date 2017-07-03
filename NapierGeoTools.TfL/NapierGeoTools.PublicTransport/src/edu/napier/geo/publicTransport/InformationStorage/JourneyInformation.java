package edu.napier.geo.publicTransport.InformationStorage;

import java.io.Serializable;
import java.util.ArrayList;

//import edu.napier.geo.publicTransport.General.Location;
import edu.napier.geo.common.Location;
import edu.napier.geo.publicTransport.ResponseTfL.TflJourney;

public class JourneyInformation extends edu.napier.geo.common.Journey implements
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 862989431123992144L;
	private ArrayList<Integer> timesMS/* = new ArrayList<Integer>() */;
	private ArrayList<Integer> legs;
	private double averageNumberOfLegs;
	private ArrayList<Integer> distancesMeter;
	private ArrayList<TflJourney> tflJourneys;
	private ArrayList<Location> routeLocations; // only from constructor

	public JourneyInformation(Location from, Location to, int time,
			int numberOfLegs, int distanceKM, TflJourney tflJourney,
			ArrayList<Location> routeLocations) {
		super(from, to);
		this.setSource("PublicTransport InformationStorage. distance is walkingDistance!");
		this.locationB = to;
		this.timesMS = new ArrayList<Integer>();
		this.addTime(time);
		this.legs = new ArrayList<Integer>();
		this.addLeg(numberOfLegs);
		this.distancesMeter = new ArrayList<Integer>();
		this.addDistance(distanceKM);
		System.out.println("constructor journeyInformation: " + toString());
		this.tflJourneys = new ArrayList<TflJourney>();
		this.tflJourneys.add(tflJourney);
		this.routeLocations = routeLocations;
	}

	public Location getFrom() {
		return super.locationA;
	}

	public void setFrom(Location from) {
		this.locationA = from;
	}

	public Location getTo() {
		return super.locationB;
	}

	public void setTo(Location to) {
		this.locationB = to;
	}

	public ArrayList<Integer> getTime() {
		return timesMS;
	}

	public void setTime(ArrayList<Integer> time) {
		this.timesMS = time;
	}

	public ArrayList<Integer> getTimesMS() {
		return timesMS;
	}

	public ArrayList<Integer> getLegs() {
		return legs;
	}

	public ArrayList<Integer> getDistancesMeter() {
		return distancesMeter;
	}

	public ArrayList<TflJourney> getTflJourneys() {
		return tflJourneys;
	}

	public double getAverageTimeMS() {
		return travelTimeMS;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ArrayList<Location> getRouteLocations() {
		return routeLocations;
	}

	public void setAverageTimeMS(double averageTime) {
		this.travelTimeMS = averageTime;
	}

	public void addTime(int timeInMinutes) {
		this.timesMS.add(timeInMinutes * 3600);
		System.out.println(timesMS);
		System.out
				.println("averageTimeForRoute-addTime. Time=" + timeInMinutes);
		setAverageTimeMS(calcAverage(timesMS));
	}

	public void addDistance(int distanceMeter) {
		this.distancesMeter.add(distanceMeter);
		setDistanceKM(calcAverage(distancesMeter) / 1000);
	}

	public double getAverageNumberOfLegs() {
		return averageNumberOfLegs;
	}

	public void setAverageNumberOfLegs(double averageNumberOfLegs) {
		this.averageNumberOfLegs = averageNumberOfLegs;
	}

	public void addLeg(int numberOfLegs) {
		this.legs.add(numberOfLegs);
		System.out
				.println("averageNumberOfLegsForRoute-addNumberOfLegs. NumberOfLegs="
						+ numberOfLegs);
		// calcAverageNumberOfLegs();
		setAverageNumberOfLegs(calcAverage(legs));
	}

	public double calcAverage(ArrayList<Integer> list) {
		double avg = 0;
		int count = 0;
		for (Integer x : list) {
			avg = avg + x;
			count++;
		}
		return (avg / count);
	}

	public String toString() {
		String string="from:" + this.getFrom().toString() + " to:"
				+ this.getTo().toString() + " averageTime:"
				+ this.getAverageTimeMS() + " all times:" + timesMS
				+ " averageNumberOfLegs=" + averageNumberOfLegs
				+ " all numbersOfLegs=" + legs + "distanceKMAvg=" + distanceKM
				+ " distances=" + distancesMeter + "numberOfTflJourney Objects:";
		return string;
	}
}
