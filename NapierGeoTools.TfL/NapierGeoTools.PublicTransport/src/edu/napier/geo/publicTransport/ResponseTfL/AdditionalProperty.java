package edu.napier.geo.publicTransport.ResponseTfL;

import java.io.Serializable;

public  class AdditionalProperty implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 9056151929296838274L;
	private  String category;
    private  String key;
    private  String sourceSystemKey;
    private  String value;
    private  String modified;

    public AdditionalProperty(String category, String key, String sourceSystemKey, String value, String modified){
        this.category = category;
        this.key = key;
        this.sourceSystemKey = sourceSystemKey;
        this.value = value;
        this.modified = modified;
    }

	public String getCategory() {
		return category;
	}

	public String getKey() {
		return key;
	}

	public String getSourceSystemKey() {
		return sourceSystemKey;
	}

	public String getValue() {
		return value;
	}

	public String getModified() {
		return modified;
	}
    
}