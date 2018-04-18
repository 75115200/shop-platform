package com.shop.base.order.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.shop.base.order.entity.Comment;

public interface CommentDao extends JpaRepository<Comment, String> {
    /**
     * 按照itemId分页查询
     * @param itemId 商品id
     * @param pageable spring 分页对象
     * @return spring Page对象
     */
    Page<Comment> findByItemId(String itemId, Pageable pageable);
    
    /**
     * 根据itemId统计总分
     * @param itemId
     * @return
     */
    @Query(value = "select sum(c.score)/count(c) from Comment c where itemId = ?1")
    Float countAvgScore(String itemId);
}
