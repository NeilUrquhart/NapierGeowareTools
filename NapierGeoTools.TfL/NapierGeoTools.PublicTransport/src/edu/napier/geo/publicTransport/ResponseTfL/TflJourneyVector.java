package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

public  class TflJourneyVector implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5659194568193047918L;
	private  String from;
    private  String to;
    private  String via;
    private  String uri;

    public TflJourneyVector(String from, String to, String via, String uri){
        this.from = from;
        this.to = to;
        this.via = via;
        this.uri = uri;
    }

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public String getVia() {
		return via;
	}

	public String getUri() {
		return uri;
	}
}