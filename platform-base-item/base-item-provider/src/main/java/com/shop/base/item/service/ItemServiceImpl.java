package com.shop.base.item.service;

import com.shop.base.item.dao.ItemDao;
import com.shop.base.item.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品服务实现
 */
@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemDao itemDao;

    @Override
    public Item addItem(Item item) {
        return itemDao.save(item);
    }
}
