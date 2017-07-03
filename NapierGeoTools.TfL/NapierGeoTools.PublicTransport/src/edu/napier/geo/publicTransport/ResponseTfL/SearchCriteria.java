package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

public  class SearchCriteria implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4605699731428925347L;
	private  String dateTime;
    private  String dateTimeType;
    private  TimeAdjustments timeAdjustments;

    public SearchCriteria(String dateTime, String dateTimeType, TimeAdjustments timeAdjustments){
        this.dateTime = dateTime;
        this.dateTimeType = dateTimeType;
        this.timeAdjustments = timeAdjustments;
    }

	public String getDateTime() {
		return dateTime;
	}

	public String getDateTimeType() {
		return dateTimeType;
	}

	public TimeAdjustments getTimeAdjustments() {
		return timeAdjustments;
	}
}