package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

public  class TimeAdjustments implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4011571634618605751L;
	private  Time earliest;
    private  Time earlier;
    private  Time later;
    private  Time latest;

    public TimeAdjustments(Time earliest, Time earlier, Time later, Time latest){
        this.earliest = earliest;
        this.earlier = earlier;
        this.later = later;
        this.latest = latest;
    }

	public Time getEarliest() {
		return earliest;
	}

	public Time getEarlier() {
		return earlier;
	}

	public Time getLater() {
		return later;
	}

	public Time getLatest() {
		return latest;
	}
}