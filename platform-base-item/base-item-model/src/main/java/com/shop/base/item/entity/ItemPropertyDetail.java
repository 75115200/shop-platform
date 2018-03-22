package com.shop.base.item.entity;

/**
 * 属性值
 */
public class ItemPropertyDetail {
    /**
     * 主键
     */
    private String id;

    /**
     * 详情code值
     */
    private String code;

    /**
     * 详情的值
     */
    private String val;

    public ItemPropertyDetail(String code, String val) {
        this.code = code;
        this.val = val;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
