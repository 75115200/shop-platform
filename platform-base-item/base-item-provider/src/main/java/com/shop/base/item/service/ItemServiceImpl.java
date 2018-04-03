package com.shop.base.item.service;

import com.google.common.collect.Lists;
import com.shop.base.item.dao.ItemDao;
import com.shop.base.item.dao.ItemPropertyDao;
import com.shop.base.item.dao.ItemTypeDao;
import com.shop.base.item.entity.Item;
import com.shop.base.item.entity.ItemProperty;
import com.shop.base.item.entity.ItemType;
import com.shop.common.base.Page;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.data.domain.Sort.Direction.valueOf;

import java.util.Collection;
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
    @Autowired
    private ItemPropertyDao itemPropertyDao;

    @Override
    public Item addItem(Item item) {
        return itemDao.save(item);
    }

    @Override
    public Item getItem(String id) {
        return itemDao.findOne(id);
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

    @Override
    public ItemType addItemType(ItemType itemType) {
        return itemTypeDao.save(itemType);
    }

    @Override
    public void delItemType(String id) {
        itemTypeDao.delete(id);
    }

    @Override
    public List<ItemProperty> listProperties(String typeId, int required) {
        return itemPropertyDao.queryByTypeIdAndRequiredOrderBySeqAsc(typeId, 1);
    }

    @Override
    public ItemProperty saveItemProperty(ItemProperty itemProperty) {
        return itemPropertyDao.save(itemProperty);
    }

    @Override
    public List<ItemProperty> listItemPropertyByCode(Collection<String> codes) {
        return itemPropertyDao.queryByCodeIn(codes);
    }

    @Override
    public ItemProperty getItemProperty(String id) {
        return itemPropertyDao.findOne(id);
    }

    @Override
    public Page<ItemProperty> listPropertyByPage(Page page, String typeId) {
        PageRequest pageRequest = new PageRequest(page.getPageNo(),  page.getPageSize());
        org.springframework.data.domain.Page result = null;
        if (StringUtils.isBlank(typeId)) {
            result = itemPropertyDao.findAll(pageRequest);
        } else {
            result = itemPropertyDao.queryByTypeId(typeId, pageRequest);
        }

        page.setTotalElements(result.getTotalElements());
        page.setPageSum(result.getTotalPages());
        page.setContent(result.getContent());
        return page;
    }

    @Override
    public Page<Item> listItemByType(String typeId, Page page) {
        PageRequest pageRequest = new PageRequest(page.getPageNo(), page.getPageSize());
        org.springframework.data.domain.Page<Item> result = null;
        if (StringUtils.isBlank(typeId)) {
            result = itemDao.findAll(pageRequest);
        } else {
            result = itemDao.queryItemsByTypeId(typeId, pageRequest);
        }

        page.setContent(result.getContent());
        page.setPageSum(result.getTotalPages());
        page.setTotalElements(result.getTotalElements());
        return page;
    }

    @Override
    public ItemProperty queryByDetailCode(String detailCode) {
        return itemPropertyDao.queryByDetailCode(detailCode);
    }
}
