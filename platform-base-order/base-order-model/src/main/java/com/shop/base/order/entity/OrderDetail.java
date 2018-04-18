package com.shop.base.order.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.math.BigDecimal;

/**
 * 订单商品详情
 */
@Entity
@Table(name = "base_order_detail")
public class OrderDetail {
    @Id
    @GenericGenerator(strategy = "uuid", name = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;
    
    /**
     * 商品id
     */
    @Column(name = "item_id")
    private String itemId;
    
    /**
     * 商品名称快照
     */
    @Column(name = "item_name")
    private String itemName;
    
    /**
     * 商品图片
     */
    @Column(name = "item_img")
    private String itemImg;
    
    /**
     * 商品sku 编码
     */
    @Column(name = "item_sku", nullable = false)
    private String itemSku;
    
    /**
     * 商品单价
     */
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    
    /***
     * 商品数量
     */
    @Column(name = "num", nullable = false)
    private int num;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getItemSku() {
        return itemSku;
    }
    
    public void setItemSku(String itemSku) {
        this.itemSku = itemSku;
    }
    
    public BigDecimal getPrice() {
        return price;
    }
    
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public int getNum() {
        return num;
    }
    
    public void setNum(int num) {
        this.num = num;
    }
    
    public String getItemName() {
        return itemName;
    }
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    public String getItemImg() {
        return itemImg;
    }
    
    public void setItemImg(String itemImg) {
        this.itemImg = itemImg;
    }
    
    public Order getOrder() {
        return order;
    }
    
    public void setOrder(Order order) {
        this.order = order;
    }
    
    public String getItemId() {
        return itemId;
    }
    
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
