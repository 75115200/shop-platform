package com.shop.base.item.dao;

import com.shop.base.item.entity.ItemType;
import org.springframework.data.repository.CrudRepository;

/**
 * 商品类型dao
 */
public interface ItemTypeDao extends CrudRepository<ItemType, String> {
}
