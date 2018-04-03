package com.shop.base.order.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 购物车实体
 */
@Entity
@Table(name = "base_order_cart")
public class Cart {

    /**
     * 主键id
     */
    @Id
    @Column
    @GeneratedValue(generator = "uuidStrategy")
    @GenericGenerator(name = "uuidStrategy", strategy = "uuid")
    private String id;

    /**
     * 商品id
     */
    @Column(name = "item_id", columnDefinition = "varchar(24) not null")
    @NotNull(message = "{cart.itemId}")
    private String itemId;

    /**
     * 库存码
     */
    @Column(name = "sku_code", columnDefinition = "varchar(50) not null")
    @NotNull(message = "{cart.skuCode}")
    private String skuCode;

    /**
     * 数量
     */
    @Column(name = "num", columnDefinition = "int(11) default 0")
    @Min(value = 1, message = "{cart.num}")
    private int num;

    /**
     * 用户id
     */
    @Column(name = "user_id", columnDefinition = "varchar(32) not null")
    private String userId;

    /**
     * 添加时间
     */
    @Column(name = "add_time")
    private Date addTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}
