package com.shop.base.user.service;

import com.shop.base.user.entity.BaseUser;
import com.shop.base.user.entity.BaseUserAddress;
import com.shop.base.user.entity.BaseUserRole;
import com.shop.common.base.Page;

import java.util.List;

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
    
    /**
     * 保存地址
     * @param address
     * @return
     */
    BaseUserAddress saveAddress(BaseUserAddress address);
    
    List<BaseUserAddress> listAddressByUser(String userId);
    
    int deleteUserAddress(String id, String userId);

//    /**
//     * 列出用户
//     * @param page
//     * @return
//     */
//    List<BaseUser> listUser(Page page);

//    List<BaseUserRole> addUserRole();

//    List<BaseUser> listUserByFilter(BaseUser user);
}
