package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

public  class ServiceType implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7305745889066061712L;
	private  String name;
    private  String uri;

    public ServiceType(String name, String uri){
        this.name = name;
        this.uri = uri;
    }

	public String getName() {
		return name;
	}

	public String getUri() {
		return uri;
	}
}