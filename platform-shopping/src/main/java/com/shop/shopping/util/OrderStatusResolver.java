package com.shop.shopping.util;

import java.util.HashMap;
import java.util.Map;

import com.shop.base.order.stereotype.OrderStatus;

public class OrderStatusResolver {
    public static Map<String, Object> enumMap = new HashMap<>();
    
    public static String resolve(int status) {
        OrderStatus orderStatus = OrderStatus.valueOf(status);
        switch (orderStatus) {
            case PAYING:
                return "待支付";
            case PAID:
                return "已支付";
            case SHIPPED:
                return "已发货";
            case RECEIVED:
                return "已确认收货";
            case FINISHED:
                return "已完成";
        }
        return "未知状态";
    }
}
