package com.shop.base.item.dao;

import com.shop.base.item.entity.ItemProperty;
import com.shop.base.item.entity.ItemPropertyDetail;
import com.shop.base.item.entity.ItemSku;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;
import java.util.List;

public interface ItemPropertyDao extends PagingAndSortingRepository<ItemProperty, String> {
    /**
     * 按照类型id列出属性
     * @param typeId
     * @param required 必要属性
     * @return
     */
    List<ItemProperty> queryByTypeIdAndRequiredOrderBySeqAsc(String typeId, int required);

    /**
     * 分页查询商品属性
     * @param typeId
     * @param pageable
     * @return
     */
    Page<ItemProperty> queryByTypeId(String typeId, Pageable pageable);

    /**
     * 根据code查询
     * @return
     */
    List<ItemProperty> queryByCodeIn(Collection<String> id);

    @Query(value = "{ 'details.code' : ?0 }", fields = "{ 'details.$':1, 'code':1, 'name':1 }")
    ItemProperty queryByDetailCode(String detailCode);
}
