package com.example.demoActuator.common;

public class BooleanResponse extends GenericResponseBody {

    private boolean success;

    public BooleanResponse() {
        super();
    }

    public BooleanResponse(boolean success) {
        this.setSuccess(success);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
