package com.shop.base.order.service;

import com.shop.base.order.dao.CartDao;
import com.shop.base.order.entity.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 订单服务实现
 */
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private CartDao cartDao;

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
    public int removeCart(String id, String userId) {
        return cartDao.deleteByIdAndUserId(id, userId);
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

}
