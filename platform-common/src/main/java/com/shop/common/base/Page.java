package com.shop.common.base;

import java.util.List;

/**
 * 公共Page
 */
public class Page<T> {
    /**
     * 默认页面大小
     */
    private static final int DEFAULT_PAGE_SIZE = 10;

    /**
     * 当前页面
     */
    private int pageNo;

    /**
     * 页面大小
     */
    private int pageSize;

    /**
     * 总页数
     */
    private int pageSum;

    /**
     * 总数据
     */
    private long totalElements;

    /**
     * 页面数据
     */
    private List<T> content;

    /**
     * 排序
     */
    private Sort sort;

    public Page() {
        this.pageNo = 1;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    public Page(int pageNo) {
        this(pageNo, DEFAULT_PAGE_SIZE);
    }

    public Page(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public static Page createByBeginAndSize(int begin, int pageSize) {
        int pageNo = 1;
        int temp = begin / pageSize;
        if (temp != 0) {
            pageNo = begin % pageSize > 0 ? temp + 1 : temp;
        }

        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        return page;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSum() {
        return pageSum;
    }

    public void setPageSum(int pageSum) {
        this.pageSum = pageSum;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List content) {
        this.content = (List<T>) content;
    }

    public Sort getSort() {
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", pageSum=" + pageSum +
                ", totalElements=" + totalElements +
                ", content=" + content +
                ", sort=" + sort +
                '}';
    }
    
    public static void main(String[] args) {
        Page page = createByBeginAndSize(0, 5);
        System.out.println(page.getPageNo());
    }
}
