package com.shop.common.base;

/**
 * 排序方式
 */
public class Sort {
    /**
     * 属性名
     */
    private String property;

    /**
     * 排序方式
     */
    private SortBy sortBy;

    public static Sort desc(String property) {
        return new Sort(property, SortBy.DESC);
    }

    public static Sort asc(String property) {
        return new Sort(property, SortBy.ASC);
    }

    private Sort() {
    }

    private Sort(String property, SortBy sortBy) {
        this.property = property;
        this.sortBy = sortBy;
    }

    private enum SortBy {
        ASC, DESC
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getSortBy() {
        if (sortBy == null) {
            throw new IllegalStateException("sortBy不应该为null");
        }
        return sortBy.toString();
    }
}
