package com.shop.base.order.stereotype;

/**
 * 订单状态
 */
public enum OrderStatus {
    /**
     * 未付款
     */
    PAYING(0),
    
    /**
     * 已付款
     */
    PAID(1),
    
    /**
     * 已发货
     */
    SHIPPED(2),
    
    /**
     * 已收货
     */
    RECEIVED(3),
    
    /**
     * 已完成
     */
    FINISHED(4);
    
    private int status;
    
    OrderStatus(int status) {
        this.status = status;
    }
    
    public int getStatus() {
        return status;
    }
}
