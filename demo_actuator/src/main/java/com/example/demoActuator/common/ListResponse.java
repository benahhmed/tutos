package com.example.demoActuator.common;

import java.util.List;


public class ListResponse<T extends Object> extends GenericResponseBody {

    private List<T> list;

    public ListResponse() {
        super();
    }

    /**
     * @param list
     */
    public ListResponse(List<T> list) {
        super();
        this.list = list;
    }

    /**
     * @return the list
     */
    public List<T> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<T> list) {
        this.list = list;
    }

}
