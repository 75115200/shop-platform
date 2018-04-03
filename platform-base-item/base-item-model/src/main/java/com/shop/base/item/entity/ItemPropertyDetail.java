package com.shop.base.item.entity;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 属性值
 */
public class ItemPropertyDetail {
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
}
