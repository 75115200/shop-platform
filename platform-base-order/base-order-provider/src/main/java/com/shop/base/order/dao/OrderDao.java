package com.shop.base.order.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.shop.base.order.entity.Order;

/**
 * 订单Dao
 */
public interface OrderDao extends PagingAndSortingRepository<Order, String>{
    Order queryByOrderNum(String orderNum);
 
    Page<Order> queryByUidOrderByCreateTimeDesc(String userId, Pageable pageable);
}
