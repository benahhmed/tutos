package com.example.demoActuator.common;

/**
 * a generic helper to be used to build uniform response
 *
 * @author Radhouene
 */
public class ResponseBuilder {

    private static String BACK_END_SUCCESS_CODE = "000";
    private static String SUCCESS = "Success";

    public static GenericResponse buildSuccessResponse(GenericResponseBody body, Provider provider) {
        GenericResponseHeader header = buildResponseHeader(BACK_END_SUCCESS_CODE, "", "", "", SUCCESS, provider,"");

        GenericResponse response = new GenericResponse();
        response.setBody(body);
        response.setHeader(header);

        return response;
    }

    public static GenericResponse buildErrorResponse(String errorCode, String errorTitleEN, String errorTitleAR, String key, String errorDescription,
                                                     Provider provider, String messageType, String... errors) {
        GenericResponseHeader header = buildResponseHeader(errorCode, errorTitleEN, errorTitleAR, key, errorDescription, provider,messageType, errors);

        GenericResponse response = new GenericResponse();
        response.setBody(null);
        response.setHeader(header);

        return response;
    }

    public static GenericResponseHeader buildResponseHeader(String statusCode, String errorTitleEN, String errorTitleAR, String key, String statusDescription,
                                                            Provider provider,String messageType, String... errors) {
        GenericResponseHeader header = new GenericResponseHeader();
        header.setStatusCode(statusCode);
        header.setTitleEN(errorTitleEN);
        header.setTitleAR(errorTitleAR);
        header.setKey(key);
        header.setDescription(statusDescription);
        header.setProviderCode(provider.getCode());
        header.setProviderDescription(provider.getDescription());
        header.setMessageType(messageType);
        if (errors.length > 0)
            header.setErrors(errors);
        return header;
    }
}
