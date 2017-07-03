package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

public  class LineModeGroup implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 771505922606056801L;
	private  String modeName;
    private  String[] lineIdentifier;

    public LineModeGroup(String modeName, String[] lineIdentifier){
        this.modeName = modeName;
        this.lineIdentifier = lineIdentifier;
    }

	public String getModeName() {
		return modeName;
	}

	public String[] getLineIdentifier() {
		return lineIdentifier;
	}
}