package com.example.demoActuator.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericResponse<T extends GenericResponseBody> {

    protected GenericResponseHeader header;
    protected GenericResponseBody body;

    public GenericResponseHeader getHeader() {
        return header;
    }

    public void setHeader(GenericResponseHeader header) {
        this.header = header;
    }

    public T getBody() {
        return (T) body;
    }

    public void setBody(T body) {
        this.body = body;
    }

}