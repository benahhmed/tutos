package com.example.demoActuator.common;

import org.springframework.hateoas.PagedResources;

import java.util.List;


public class PaginatedListResponse<T extends Object> extends GenericResponseBody {

    private long size; // number of elements in the page
    private long count; // Total of elements
    private long totalPages;
    private long page; // Total of pages
    private List<T> list;
    private PagedResources pagedResource;

    public PaginatedListResponse() {
        super();
    }


    /**

     * @param size
     * @param count
     * @param page
     * @param totalPages
     * @param list
     */
    public PaginatedListResponse(long size, long count, long totalPages, long page, List<T> list) {
        this.size = size;
        this.count = count;
        this.totalPages = totalPages;
        this.page = page;
        this.list = list;
    }

    public PaginatedListResponse(PagedResources pagedResource, List<T> data) {
        this.pagedResource = pagedResource;
        this.totalPages = pagedResource.getMetadata().getTotalPages();
        this.size = pagedResource.getMetadata().getSize();
        this.count = pagedResource.getMetadata().getTotalElements() ;
        this.page = pagedResource.getMetadata().getNumber();
        this.list = data;
    }

    /**
     * @return the count
     */
    public long getCount() {
        return count;
    }

    /**
     * @return the page
     */
    public long getPage() {
        return page;
    }

    /**
     * @return the size
     */
    public long getSize() {
        return size;
    }

    /**
     * @return the list
     */
    public List<T> getList() {
        return list;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public String getNextHref() {
        return pagedResource.getLink("next") != null ? pagedResource.getLink("next").getHref() : null;
    }

    public String getPreviousHref() {
        return pagedResource.getLink("prev") != null ? pagedResource.getLink("prev").getHref() : null;
    }

    public String getFirstHref() {
        return pagedResource.getLink("first") != null ? pagedResource.getLink("first").getHref() : null;
    }

    public String getLastHref() {
        return pagedResource.getLink("last") != null ? pagedResource.getLink("last").getHref() : null;
    }

}
