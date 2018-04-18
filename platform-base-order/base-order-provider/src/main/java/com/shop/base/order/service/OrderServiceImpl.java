package com.shop.base.order.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.shop.base.order.dao.CartDao;
import com.shop.base.order.dao.CommentDao;
import com.shop.base.order.dao.OrderDao;
import com.shop.base.order.dao.OrderDetailDao;
import com.shop.base.order.entity.Cart;
import com.shop.base.order.entity.Comment;
import com.shop.base.order.entity.Order;
import com.shop.base.order.stereotype.OrderStatus;
import com.shop.common.base.BusinessException;
import com.shop.common.base.Page;
import com.shop.common.util.NullAwareBeanUtilBean;

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
    @Autowired
    private CommentDao commentDao;

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
    public void updateOrderStatus(String orderNum, OrderStatus status) {
        Order order = orderDao.queryByOrderNum(orderNum);
        if (order == null) {
            throw new BusinessException("订单不存在");
        }
    
        //TODO 检查是否允许从x状态更新到y状态
        order.setStatus(status.getStatus());
        orderDao.save(order);
    }
    
    @Override
    public Page<Order> listOrderByUserId(String userId, Page page) {
        Order order = new Order();
        order.setUid(userId);
        return listOrderByExample(order, page);
    }
    
    @Override
    public Order getOrderById(String orderId) {
        return orderDao.findOne(orderId);
    }
    
    @Override
    public Page<Order> listOrderByExample(Order order, Page page) {
        Pageable pageable = new PageRequest(page.getPageNo() - 1, page.getPageSize());
    
        org.springframework.data.domain.Page<Order> result = orderDao.findAll(Example.of(order), pageable);
    
        page.setContent(result.getContent());
        page.setTotalElements(result.getTotalElements());
        page.setPageSum(result.getTotalPages());
        return page;
    }
    
    @Override
    @Transactional
    public void updateOrder(Order order) {
        Order target = orderDao.findOne(order.getId());
        if (target == null) {
            throw new BusinessException("订单不存在");
        }
    
        try {
            new NullAwareBeanUtilBean().copyProperties(target, order);
            target.setUpdateTime(new Date());
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("bean拷贝异常", e);
        }
        orderDao.save(target);
    }
    
    @Override
    public List<Comment> saveComments(List<Comment> commentList) {
        return commentDao.save(commentList);
    }
    
    @Override
    public Page<Comment> listCommentByItemId(String itemId, int page, int pageSize) {
        Page<Comment> result = new Page<>(page, pageSize);
        
        Pageable pageable = new PageRequest(page - 1, pageSize);
        org.springframework.data.domain.Page<Comment> dbPage = commentDao.findByItemId(itemId, pageable);
        
        result.setContent(dbPage.getContent());
        result.setPageSum(dbPage.getTotalPages());
        result.setTotalElements(dbPage.getTotalElements());
        return result;
    }
    
    @Override
    public float countAvgScore(String itemId) {
        Float avgScore = commentDao.countAvgScore(itemId);
        if (avgScore == null) {
            return 0.0f;
        }
        BigDecimal formater = new BigDecimal(avgScore);
        return formater.setScale(2, BigDecimal.ROUND_HALF_DOWN).floatValue();
    }
}
