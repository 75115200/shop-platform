package com.shop.base.item.service;

import com.google.common.collect.Lists;
import com.shop.base.item.dao.ItemDao;
import com.shop.base.item.dao.ItemTypeDao;
import com.shop.base.item.entity.Item;
import com.shop.base.item.entity.ItemType;
import com.shop.common.base.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.data.domain.Sort.Direction.valueOf;

import java.util.List;

/**
 * 商品服务实现
 */
@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao itemDao;
    @Autowired
    private ItemTypeDao itemTypeDao;

    @Override
    public Item addItem(Item item) {
        return itemDao.save(item);
    }

    @Override
    public Item getItem(String id) {
        return itemDao.findOne(id);
    }

    @Override
    public ItemType addItemType(ItemType itemType) {
        return itemTypeDao.save(itemType);
    }

    @Override
    public List<Item> listHotItem() {
        return null;
    }

    @Override
    public List<ItemType> listAllType() {
        return Lists.newArrayList(itemTypeDao.findAll());
    }

    @Override
    public ItemType getType(String typeId) {
        return itemTypeDao.findOne(typeId);
    }

    public Page<Item> listItemByType(String typeId, Page page) {
        Sort order = null;
        if (page.getSort() != null) {
            order = new Sort(valueOf(page.getSort().getSortBy()), page.getSort().getProperty());
        }
        PageRequest pageRequest = new PageRequest(1, 10, order);
        org.springframework.data.domain.Page<Item> result = itemDao.queryItemsByTypeId(typeId, pageRequest);

        page.setContent(result.getContent());
        page.setPageSum(result.getTotalPages());
        page.setTotalElements(result.getTotalElements());
        return page;
    }
}
