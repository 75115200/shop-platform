package com.shop.base.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import javax.validation.constraints.*;

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
    @Pattern(regexp = "[a-zA-Z0-9]{4,16}", message = "{user.username}")
    private String username;

    /**
     * 密码
     */
    @Column
    @Pattern(regexp = "[a-zA-Z0-9]{6,}", message = "{user.password}")
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
    @Pattern(regexp = "([A-Za-z0-9_\\-\\.])+\\@([A-Za-z0-9_\\-\\.])+\\.([A-Za-z]{2,4})", message = "{user.email}")
    private String email;

    /**
     * 电话号码
     */
    @Column
    @Pattern(regexp = "((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}", message = "{user.phone}")
    private String phone;

    @ManyToMany
    @JoinTable(name = "base_user_role_permission")
    @JsonIgnore
    private List<BaseUserRole> userRoles;

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

    public List<BaseUserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<BaseUserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
