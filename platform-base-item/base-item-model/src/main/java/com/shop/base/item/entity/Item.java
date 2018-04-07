package com.shop.base.item.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

/**
 * 商品基础数据
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
    @Pattern(regexp = "[a-zA-Z0-9]{13}", message = "{item.no}")
    private String no;

    /**
     * 产品名称
     */
    @NotNull(message = "{item.name}")
    @Size(max = 22, message = "{item.size}")
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
    private String typeId;

    /**
     * 原价
     */
    private String originalPrice;

    /**
     * 最低价
     */
    private String lowest;

    /**
     * 库存信息
     */
    private List<ItemSku> sku;

    /**
     * 商品属性值code
     */
    private Set<String> detailCodes;

    /**
     * 上架时间
     */
    @JsonProperty(access = READ_ONLY)
    private Date publishDate;

    /**
     * 商品状态
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

    public List<ItemSku> getSku() {
        return sku;
    }

    public void setSku(List<ItemSku> sku) {
        this.sku = sku;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Set<String> getDetailCodes() {
        return detailCodes;
    }

    public void setDetailCodes(Set<String> detailCodes) {
        this.detailCodes = detailCodes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
