package com.shop.base.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "base_user_address")
public class BaseUserAddress {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @GeneratedValue(generator = "uuid")
    private String id;
    
    /**
     * 收件人姓名
     */
    @Column(name = "receiver_name", columnDefinition = "varchar(20) not null")
    @NotNull
    private String receiverName;
    
    /**
     * 收件人手机
     */
    @Column(name = "receiver_phone")
    @NotNull
    private String receiverPhone;
    
    /**
     * 收件人地址
     */
    @Column(name = "receiver_addr")
    @NotNull
    private String receiverAddr;
    
    /**
     * 是否默认
     */
    @Column(name = "active")
    private boolean active;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private BaseUser user;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getReceiverName() {
        return receiverName;
    }
    
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
    
    public String getReceiverPhone() {
        return receiverPhone;
    }
    
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }
    
    public String getReceiverAddr() {
        return receiverAddr;
    }
    
    public void setReceiverAddr(String receiverAddr) {
        this.receiverAddr = receiverAddr;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public BaseUser getUser() {
        return user;
    }
    
    public void setUser(BaseUser user) {
        this.user = user;
    }
}
