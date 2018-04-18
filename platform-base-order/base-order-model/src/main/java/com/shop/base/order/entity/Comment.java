package com.shop.base.order.entity;

import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 评价实体
 */
@Entity
@Table(name = "base_order_comment")
public class Comment {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;
    
    @Column
    private Float score;
    
    @Column
    private String content;
    
    @Column(name = "item_id")
    private String itemId;
    
    @Column(name = "order_detail_id")
    private String orderDetailId;
    
    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Float getScore() {
        return score;
    }
    
    public void setScore(Float score) {
        this.score = score;
    }
    
    public String getContent() {
        return content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }
    
    public String getItemId() {
        return itemId;
    }
    
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    
    public String getOrderDetailId() {
        return orderDetailId;
    }
    
    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }
    
    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
}
