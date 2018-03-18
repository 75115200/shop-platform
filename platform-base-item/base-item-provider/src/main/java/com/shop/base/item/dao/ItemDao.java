package com.shop.base.item.dao;

import com.shop.base.item.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * 商品数据Dao
 */
public interface ItemDao extends PagingAndSortingRepository<Item, String> {
    Page<Item> queryItemsByTypeId(String typeId, Pageable pageable);
}
