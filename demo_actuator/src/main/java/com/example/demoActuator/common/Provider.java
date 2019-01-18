package com.example.demoActuator.common;

public enum Provider {
	    COMPANY("1", "Back-end API");


	    private String code;
	    private String description;

	    Provider(String code, String description) {
	        this.code = code;
	        this.description = description;
	    }

	    public String getCode() {
	        return code;
	    }

	    public String getDescription() {
	        return description;
	    }
}

