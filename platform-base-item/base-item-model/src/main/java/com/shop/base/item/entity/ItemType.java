package com.shop.base.item.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * 商品类型
 */
@Document(collection = "itemType")
public class ItemType {
    /**
     * 类型id
     */
    private String id;

    /**
     * 类型名称
     */
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
