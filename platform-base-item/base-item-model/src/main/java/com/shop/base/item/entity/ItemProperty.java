package com.shop.base.item.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品属性
 */
public class ItemProperty {
    /**
     * 属性序号
     */
    private int seq;

    /**
     * 属性名称
     */
    private String name;

    /**
     * 属性值集合
     */
    private List<ItemPropertyDetail> details;

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemPropertyDetail> getDetails() {
        return details;
    }

    public void setDetails(List<ItemPropertyDetail> details) {
        this.details = details;
    }

    /**
     * 添加子属性详情，非线程安全
     * @param code
     * @param val
     */
    public void addDetail(String code, String val) {
        if (this.details == null) {
            this.details = new ArrayList<>();
        }
        this.details.add(new ItemPropertyDetail(code, val));
    }
}
