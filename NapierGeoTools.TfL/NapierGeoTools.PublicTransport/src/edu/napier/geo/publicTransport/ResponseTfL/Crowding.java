package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

public class Crowding implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -713556270670160272L;
	private PassengerFlow passengerFlows[];
	private TrainLoading trainLoadings[];

	public Crowding(PassengerFlow[] passengerFlows, TrainLoading[] trainLoadings) {
		this.passengerFlows = passengerFlows;
		this.trainLoadings = trainLoadings;
	}



	public PassengerFlow[] getPassengerFlows() {
		return passengerFlows;
	}

	public TrainLoading[] getTrainLoadings() {
		return trainLoadings;
	}
	
}