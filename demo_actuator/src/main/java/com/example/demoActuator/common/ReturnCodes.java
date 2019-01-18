package com.example.demoActuator.common;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum ReturnCodes {

    E250 ("250", "A required argument {0} is missing"),
    E251 ("251", "General error not handled");

    private String code;
    private String message;
    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }


    private static final Map<String, String> mMap = Collections.unmodifiableMap(initializeMapping());


    public String getMessage(Object... args) {
        return MessageFormat.format(message, args);
    }

    /**
     * @param message
     */
    private ReturnCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * aims to retrieve message by code
     *
     * @author radhouene.gniwa
     * @param code:
     *            The code to fetch corresponding message
     * @return: The corresponding message for the code
     */
    public static String getMessageByCode(String code) {
        return mMap.get(code);
    }
    public String getTitle(Object... args) {
        return MessageFormat.format(message, args);
    }
    private static Map<String, String> initializeMapping() {
        Map<String, String> mMap = new HashMap<String, String>();
        for (ReturnCodes s : ReturnCodes.values()) {
            mMap.put(s.code, s.message);
        }
        return mMap;
    }
}
