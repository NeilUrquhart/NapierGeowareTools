package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

public  class TrainLoading implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4506059362529463983L;
	private  String line;
    private  String lineDirection;
    private  String platformDirection;
    private  String direction;
    private  String naptanTo;
    private  String timeSlice;
    private  long value;

    public TrainLoading(String line, String lineDirection, String platformDirection, String direction, String naptanTo, String timeSlice, long value){
        this.line = line;
        this.lineDirection = lineDirection;
        this.platformDirection = platformDirection;
        this.direction = direction;
        this.naptanTo = naptanTo;
        this.timeSlice = timeSlice;
        this.value = value;
    }

	public String getLine() {
		return line;
	}

	public String getLineDirection() {
		return lineDirection;
	}

	public String getPlatformDirection() {
		return platformDirection;
	}

	public String getDirection() {
		return direction;
	}

	public String getNaptanTo() {
		return naptanTo;
	}

	public String getTimeSlice() {
		return timeSlice;
	}

	public long getValue() {
		return value;
	}
}