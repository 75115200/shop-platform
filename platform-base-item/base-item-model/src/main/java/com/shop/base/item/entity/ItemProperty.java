package com.shop.base.item.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品属性
 */
@Document
public class ItemProperty {
    /**
     * 主键id
     */
    private String id;

    /**
     * 属性序号
     */
    private int seq;

    /**
     * 商品类型id
     */
    private String typeId;

    /**
     * 属性名称
     */
    private String name;

    /**
     * 是否必须,1 - 必须， 0 - 非必须
     */
    private int required;

    /**
     * 属性值集合
     */
    private List<ItemPropertyDetail> details;

    /**
     * 版本号
     */
    private int version;

    /**
     * 添加子属性详情，非线程安全
     *
     * @param code
     * @param val
     */
    public void addDetail(String code, String val) {
        if (this.details == null) {
            this.details = new ArrayList<>();
        }
        this.details.add(new ItemPropertyDetail(code, val));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getRequired() {
        return required;
    }

    public void setRequired(int required) {
        this.required = required;
    }
}
