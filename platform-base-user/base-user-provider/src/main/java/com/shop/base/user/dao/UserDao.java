package com.shop.base.user.dao;

import com.shop.base.user.entity.BaseUser;
import org.springframework.data.repository.CrudRepository;

/**
 * 用户基础Dao
 */
public interface UserDao extends CrudRepository<BaseUser, String>{

    /**
     * 通过用户名查询用户基础信息
     * @param username 用户名
     * @return {@link BaseUser} 用户基础信息
     */
    BaseUser findBaseUserByUsername(String username);
}
