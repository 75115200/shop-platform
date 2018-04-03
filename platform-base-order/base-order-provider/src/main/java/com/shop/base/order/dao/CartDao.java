package com.shop.base.order.dao;

import com.shop.base.order.entity.Cart;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 基础CRUD接口
 */
public interface CartDao extends PagingAndSortingRepository<Cart, String> {
    Cart queryByUserIdAndItemIdAndSkuCode(String userId, String itemId, String skuCode);

    List<Cart> queryByUserId(String userId);

    int deleteByIdAndUserId(String id, String userId);

    int countByUserId(String userId);
}
