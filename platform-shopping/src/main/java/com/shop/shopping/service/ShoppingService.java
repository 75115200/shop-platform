package com.shop.shopping.service;

import com.shop.base.order.entity.Cart;
import com.shop.base.order.entity.Order;
import com.shop.shopping.model.CartDto;
import com.shop.shopping.param.OrderForm;

import java.util.List;

/**
 * 购物系统服务，封装业务，保证事务
 */
public interface ShoppingService {

    /**
     * 添加到购物车
     * @param cart
     * @throws com.shop.common.base.BusinessException
     */
    void addCart(Cart cart);

    /**
     * 显示用户的购物车列表
     * @param userId
     * @return
     */
    List<CartDto> showCart(String userId);
    
    /**
     * 结算下单
     * @param orderForm
     * @param userId
     * @return
     */
    Order checkout(OrderForm orderForm, String userId);
    
}
