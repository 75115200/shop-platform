package com.shop.base.user.entity;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 基础对象信息
 */
@Entity
public class BaseUser {
    /**
     * 唯一标识
     */
    @Id
    @Column
    @GeneratedValue(generator  = "uuidStrategy")
    @GenericGenerator(name = "uuidStrategy", strategy = "uuid")
    private String uid;

    /**
     * 用户名
     */
    @Column
    private String username;

    /**
     * 密码
     */
    @Column
    private String password;

    /**
     * 盐值
     */
    @Column
    private String salt;

    /**
     * 电子邮箱
     */
    @Column
    private String email;

    /**
     * 电话号码
     */
    @Column
    private String phone;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
