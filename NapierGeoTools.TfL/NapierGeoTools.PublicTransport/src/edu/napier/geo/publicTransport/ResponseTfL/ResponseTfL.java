package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

public class ResponseTfL implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8124741540587098503L;
	private TflJourney journeys[];
	private Line lines[];
	private CycleHireDockingStationData cycleHireDockingStationData;
	private String[] stopMessages;
	private long recommendedMaxAgeMinutes;
	private SearchCriteria searchCriteria;
	private TflJourneyVector tflJourneyVector;

	public ResponseTfL(TflJourney[] journeys, Line[] lines, CycleHireDockingStationData cycleHireDockingStationData,
			String[] stopMessages, long recommendedMaxAgeMinutes, SearchCriteria searchCriteria,
			TflJourneyVector tflJourneyVector) {
		this.journeys = journeys;
		this.lines = lines;
		this.cycleHireDockingStationData = cycleHireDockingStationData;
		this.stopMessages = stopMessages;
		this.recommendedMaxAgeMinutes = recommendedMaxAgeMinutes;
		this.searchCriteria = searchCriteria;
		this.tflJourneyVector = tflJourneyVector;
	}
	public String x() {
		String x = "";
		if(journeys!=null){
		for (TflJourney tflJourney : journeys) {
			x = x + "startDateTime=" + tflJourney.getStartDateTime() + "arrivalDateTime=" + tflJourney.getArrivalDateTime() + "duration"
					+ tflJourney.getDurationMinutes();
		}}
		return x;
	}

	public TflJourney[] getTflJourneys() {
		return journeys;
	}


	public Line[] getLines() {
		return lines;
	}

	public CycleHireDockingStationData getCycleHireDockingStationData() {
		return cycleHireDockingStationData;
	}

	public String[] getStopMessages() {
		return stopMessages;
	}

	public long getRecommendedMaxAgeMinutes() {
		return recommendedMaxAgeMinutes;
	}

	public SearchCriteria getSearchCriteria() {
		return searchCriteria;
	}

	public TflJourneyVector getTflJourneyVector() {
		return tflJourneyVector;
	}
}
