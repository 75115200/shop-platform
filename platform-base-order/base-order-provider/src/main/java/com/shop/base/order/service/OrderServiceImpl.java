package com.shop.base.order.service;

import com.google.common.collect.Lists;
import com.shop.base.order.dao.CartDao;
import com.shop.base.order.dao.OrderDao;
import com.shop.base.order.dao.OrderDetailDao;
import com.shop.base.order.entity.Cart;
import com.shop.base.order.entity.Order;
import com.shop.common.base.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * 订单服务实现
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private CartDao cartDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Transactional
    @Override
    public Cart saveCart(Cart cart) {
        Cart exist = cartDao.queryByUserIdAndItemIdAndSkuCode(cart.getUserId(), cart.getItemId(), cart.getSkuCode());
        if (exist != null) {
            exist.setNum(exist.getNum() + 1);
            cart = exist;
        }

        return cartDao.save(cart);
    }

    @Override
    public List<Cart> listCartByUserId(String userId) {
        return cartDao.queryByUserId(userId);
    }

    @Override
    @Transactional
    public int removeCart(String userId, String... id) {
        return cartDao.deleteByUserIdAndIdIn(userId, id);
    }

    @Override
    public int countCart(String userId) {
        return cartDao.countByUserId(userId);
    }

    @Override
    @Transactional
    public void updateCartNum(String id, int num, String userId) {
        Cart target = cartDao.findOne(id);
        if (target != null && target.getUserId().equals(userId)) {
            target.setNum(num);
            cartDao.save(target);
        }
    }

    @Override
    public List<Cart> findCart(Set<String> ids) {
        return Lists.newArrayList(cartDao.findAll(ids));
    }
 
    @Override
    @Transactional
    public Order saveOrder(Order order) {
        Order result = orderDao.save(order);
        return result;
    }
    
    @Override
    @Transactional
    public int updateOrderStatus(String orderNum, int status) {
        Order order = orderDao.queryByOrderNum(orderNum);
        if (order == null) {
            // 订单不存在
            return 0;
        }
    
        //TODO 检查是否允许从x状态更新到y状态
        order.setStatus(status);
        orderDao.save(order);
        return 1;
    }
    
    @Override
    public Page<Order> listOrderByUserId(String userId, Page page) {
        Pageable pageable = new PageRequest(page.getPageNo(), page.getPageSize());
        org.springframework.data.domain.Page<Order> result = orderDao.queryByUid(userId, pageable);
        
        page.setContent(result.getContent());
        page.setTotalElements(result.getTotalElements());
        page.setPageSum(result.getTotalPages());
        return page;
    }
}
