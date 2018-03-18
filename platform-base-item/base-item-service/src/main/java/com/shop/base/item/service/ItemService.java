package com.shop.base.item.service;

import com.shop.base.item.entity.Item;
import com.shop.base.item.entity.ItemProperty;
import com.shop.base.item.entity.ItemType;

import java.util.List;

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

    /**
     * 获取商品
     * @param id
     * @return
     */
    Item getItem(String id);

    /**
     * 添加商品类型
     * @param itemType 商品类型
     * @return 商品类型
     */
    ItemType addItemType(ItemType itemType);

    /**
     * 列出热门商品
     * @return
     */
    List<Item> listHotItem();

    /**
     * 列出所有商品类型
     * @return 商品类型列表
     */
    List<ItemType> listAllType();

    /**
     * 获取类型的对应的筛选属性
     * @param typeId 类型id
     * @return
     */
    ItemType getType(String typeId);

}
