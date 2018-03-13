package com.shop.base.item.dao;

import com.shop.base.item.entity.Item;
import org.springframework.data.repository.CrudRepository;

/**
 * 商品数据Dao
 */
public interface ItemDao extends CrudRepository<Item, String> {
}
