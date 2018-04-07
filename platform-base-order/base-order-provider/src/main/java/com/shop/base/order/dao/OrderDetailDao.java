package com.shop.base.order.dao;

import org.springframework.data.repository.CrudRepository;

import com.shop.base.order.entity.OrderDetail;

public interface OrderDetailDao extends CrudRepository<OrderDetail, String> {

}
