package com.shop.shopping.model;

import com.shop.base.item.entity.ItemProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车DTO
 */
public class CartDto {
    /**
     * 购物车id
     */
    private String cartId;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品数量
     */
    private int num;

    /**
     * 商品图片
     */
    private String itemPic;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 商品规格
     */
    private List<ItemProperty> properties;

    /**
     * 商品Sku code值
     */
    private String itemSkuCode;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
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

    public String getItemPic() {
        return itemPic;
    }

    public void setItemPic(String itemPic) {
        this.itemPic = itemPic;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSkuCode() {
        return itemSkuCode;
    }

    public void setItemSkuCode(String itemSkuCode) {
        this.itemSkuCode = itemSkuCode;
    }

    public List<ItemProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<ItemProperty> properties) {
        this.properties = properties;
    }
}
