package com.shop.base.user.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 用户权限
 */
@Entity
@Table(name = "base_user_permission")
public class BaseUserPermission {
    /**
     * 主键id
     */
    @Id
    @Column
    @GeneratedValue(generator  = "uuidStrategy")
    @GenericGenerator(name = "uuidStrategy", strategy = "uuid")
    private String id;

    /**
     * 权限代码
     */
    @Column
    private String code;

    /**
     * 权限名称
     */
    @Column
    private String name;

    /**
     * 资源
     */
    @Column
    private String resource;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
