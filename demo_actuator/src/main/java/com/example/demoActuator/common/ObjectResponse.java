package com.example.demoActuator.common;

public class ObjectResponse<T> extends GenericResponseBody {

    private T object;

    public ObjectResponse() {

    }

    /**
     * @param object
     */
    public ObjectResponse(T object) {
        super();
        this.object = object;
    }

    /**
     * @return the object
     */
    public T getObject() {
        return object;
    }

    /**
     * @param object the object to set
     */
    public void setObject(T object) {
        this.object = object;
    }

}
