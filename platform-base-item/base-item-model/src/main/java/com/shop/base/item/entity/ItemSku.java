package com.shop.base.item.entity;

import java.math.BigDecimal;

/**
 * 商品SKU数据
 */
public class ItemSku {

    /**
     * 库存code值
     * {@link ItemPropertyDetail#code}
     */
    private String code;

    /**
     * 库存量
     */
    private int stock;

    /**
     * 价格
     */
    private BigDecimal price;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

