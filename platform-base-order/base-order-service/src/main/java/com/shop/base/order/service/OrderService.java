package com.shop.base.order.service;

import com.shop.base.order.entity.Cart;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单服务接口
 */
public interface OrderService {

    /**
     * 保存购物车信息
     * @param cart 购物车信息
     * @return 保存后的信息
     */
    Cart saveCart(Cart cart);

    /**
     * 列出用户的购物车清单
     * @param userId 用户id
     * @return
     */
    List<Cart> listCartByUserId(String userId);

    /**
     * 从购物车中移除商品
     * @param id
     * @param userId
     * @return
     */
    int removeCart(String id, String userId);

    /**
     * 统计购物车中的商品数量
     * @param userId
     * @return
     */
    int countCart(String userId);

    /**
     * 更新购物车中的特定商品的数量
     * @param id
     * @param num
     * @param userId
     */
    void updateCartNum(String id, int num, String userId);
}
