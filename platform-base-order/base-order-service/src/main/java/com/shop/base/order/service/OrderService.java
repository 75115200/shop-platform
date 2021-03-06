package com.shop.base.order.service;

import com.shop.base.order.entity.Cart;
import com.shop.base.order.entity.Comment;
import com.shop.base.order.entity.Order;
import com.shop.base.order.stereotype.OrderStatus;
import com.shop.common.base.Page;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

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
     * @param userId
     * @param id
     * @return
     */
    int removeCart(String userId, String... id);

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

    /**
     * 按照id集合查找购物车中的商品
     * @param ids
     * @return
     */
    List<Cart> findCart(Set<String> ids);
    
    /**
     * 保存更新订单
     * @param order
     * @return
     */
    Order saveOrder(Order order);
    
    /**
     * 更新订单状态
     * @param orderNum
     * @param status
     * @return
     */
    void updateOrderStatus(String orderNum, OrderStatus status);
    
    /**
     * 列出用户订单
     * 默认时间降序排序
     * @param userId
     * @param page
     * @return
     */
    Page<Order> listOrderByUserId(String userId, Page page);
    
    /**
     * 根据订单id查询订单信息
     * @param orderId
     * @return
     */
    Order getOrderById(String orderId);
    
    /**
     * 根据Order的部分属性进行查询
     * @param order
     * @param page
     * @return
     */
    Page<Order> listOrderByExample(Order order, Page page);
    
    void updateOrder(Order order);
    
    /**
     * 保存评论
     * @param commentList
     * @return
     */
    List<Comment> saveComments(List<Comment> commentList);
    
    Page<Comment> listCommentByItemId(String itemId, int page, int pageSize);
    
    /**
     * 统计商品平均评分
     * 保留两位小数，向下取整
     * @param itemId
     * @return
     */
    float countAvgScore(String itemId);
}
