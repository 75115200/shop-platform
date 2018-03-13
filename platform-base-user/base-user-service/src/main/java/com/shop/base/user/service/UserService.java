package com.shop.base.user.service;

import com.shop.base.user.entity.BaseUser;

/**
 * 基础：用户服务接口
 */
public interface UserService {
    /**
     * 注册用户
     * @param baseUser
     * @return BaseUser
     */
    BaseUser register(BaseUser baseUser);

    /**
     * 用户登录
     *
     * @param account
     * @param password
     * @return BaseUser
     */
    BaseUser login(String account, String password);
}
