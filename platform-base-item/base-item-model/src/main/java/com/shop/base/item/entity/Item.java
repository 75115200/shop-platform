package com.shop.base.item.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品数据
 */
@Document(collection = "item")
public class Item {
    /**
     * 产品id
     */
    private String id;

    /**
     * 产品编号
     */
    private String no;

    /**
     * 产品名称
     */
    private String name;

    /**
     * 产品详情
     */
    private String detail;

    /**
     * 产品图片
     */
    private List<String> files;

    /**
     * 产品类型
     */
    private String type;

    /**
     * 原价
     */
    private String originalPrice;

    /**
     * 最低价
     */
    private String lowest;

    /**
     * 状态
     */
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getLowest() {
        return lowest;
    }

    public void setLowest(String lowest) {
        this.lowest = lowest;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }
}
