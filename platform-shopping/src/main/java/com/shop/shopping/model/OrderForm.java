package com.shop.shopping.model;

/**
 * 用户下单表单
 */
public class OrderForm {
    /**
     * 收件人姓名
     */
    private String receiverName;

    /**
     * 收件人电话
     */
    private String receiverPhone;

    /**
     * 收件地址
     */
    private String receiverAddr;

    /**
     * 要结算的购物车商品id
     */
    private String[] cartIds;

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverAddr() {
        return receiverAddr;
    }

    public void setReceiverAddr(String receiverAddr) {
        this.receiverAddr = receiverAddr;
    }

    public String[] getCartIds() {
        return cartIds;
    }

    public void setCartIds(String[] cartIds) {
        this.cartIds = cartIds;
    }
}
