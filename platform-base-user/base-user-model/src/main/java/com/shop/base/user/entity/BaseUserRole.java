package com.shop.base.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * 角色
 */
@Entity
@Table(name = "base_user_role")
public class BaseUserRole {
    /**
     * id
     */
    @Id
    @Column
    @GeneratedValue(generator = "uuidStrategy")
    @GenericGenerator(name = "uuidStrategy", strategy = "uuid")
    private String id;

    /**
     * 角色名称
     */
    @Column
    private String roleName;

    @ManyToMany(mappedBy = "userRoles", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<BaseUser> users;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<BaseUser> getUsers() {
        return users;
    }

    public void setUsers(List<BaseUser> users) {
        this.users = users;
    }
}
