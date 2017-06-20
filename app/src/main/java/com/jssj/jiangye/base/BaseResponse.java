package com.jssj.jiangye.base;


/**
 * response 基类
 * <p/>
 */
public class BaseResponse {

    /**
     "currentPage": 1, // 当前页数
     "totalPages": 10,// 总页数
     "pageSize": 10, // 每页显示条数
     "totalItem": 200,// 总记录数
     */
    private int pageSize;
    private int currentPage;
    private int totalItem;
    private int totalPages;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", totalItem=" + totalItem +
                ", totalPages=" + totalPages +
                '}';
    }
}
