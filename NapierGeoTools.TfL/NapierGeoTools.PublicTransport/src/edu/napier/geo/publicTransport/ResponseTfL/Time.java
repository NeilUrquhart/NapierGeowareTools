package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

public  class Time implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5226560175597841786L;
	private  String date;
    private  String time;
    private  String timeIs;
    private  String uri;

    public Time(String date, String time, String timeIs, String uri){
        this.date = date;
        this.time = time;
        this.timeIs = timeIs;
        this.uri = uri;
    }

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public String getTimeIs() {
		return timeIs;
	}

	public String getUri() {
		return uri;
	}
	public void print(){
		System.out.println(date + " " + time + " " + timeIs + " " + uri);
	}
    
}