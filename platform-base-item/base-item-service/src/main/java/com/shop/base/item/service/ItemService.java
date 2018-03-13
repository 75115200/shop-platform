package com.shop.base.item.service;

import com.shop.base.item.entity.Item;

/**
 *  商品服务
 */
public interface ItemService {

    /**
     * 添加商品
     * @param item 要添加的商品
     * @return 商品完整属性
     */
    Item addItem(Item item);
}
