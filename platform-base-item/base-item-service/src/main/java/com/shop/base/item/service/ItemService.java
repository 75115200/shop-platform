package com.shop.base.item.service;

import com.shop.base.item.entity.Item;
import com.shop.base.item.entity.ItemProperty;
import com.shop.base.item.entity.ItemSku;
import com.shop.base.item.entity.ItemType;
import com.shop.common.base.Page;

import javax.annotation.Nullable;
import java.util.Collection;
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

    /**
     * 添加商品类型
     * @param itemType 商品类型
     * @return 商品类型
     */
    ItemType addItemType(ItemType itemType);

    /**
     * 删除商品类型
     * @param id
     */
    void delItemType(String id);

    /**
     * 查询商品属性
     * @param typeId 类型id
     * @param required
     * @return
     */
    List<ItemProperty> listProperties(String typeId, int required);

    /**
     * 保存商品属性
     * @param itemProperty
     * @return
     */
    ItemProperty saveItemProperty(ItemProperty itemProperty);

    /**
     * 通过code值列出属性
     * @param codes
     * @return
     */
    List<ItemProperty> listItemPropertyByCode(Collection<String> codes);

    /**
     * 获取商品属性
     * @param id
     * @return
     */
    ItemProperty getItemProperty(String id);

    /**
     * 分页列出商品属性
     * @param page 分页信息
     * @param typeId 类型id，可为null
     * @return
     */
    Page<ItemProperty> listPropertyByPage(Page page, String typeId);

    /**
     * 按照类型列出商品
     * @param typeId
     * @param page
     * @return
     */
    Page<Item> listItemByType(String typeId, Page page);
    
    /**
     * 通过code查询对应的属性
     * @param detailCode
     * @return
     */
    ItemProperty queryByDetailCode(String detailCode);
    
    /**
     * 保存或更新商品
     * @param item
     * @return
     */
    Item saveItem(Item item);
    
    /**
     * 扣减库存
     * @param skuCode
     * @param num
     * @return
     */
    int reduceItemSku(String skuCode, int num);
    
    /**
     * 查询库存信息
     * 只返回item的id, name和sku
     * @param itemId
     * @param skuCode
     * @return
     */
    Item queryNameAndSkuByItemIdAndSkuCode(String itemId, String skuCode);
}
