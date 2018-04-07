package com.shop.base.user.service;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.shop.base.user.dao.BaseUserAddressDao;
import com.shop.base.user.dao.UserDao;
import com.shop.base.user.dao.UserRoleDao;
import com.shop.base.user.entity.BaseUser;
import com.shop.base.user.entity.BaseUserAddress;
import com.shop.common.base.BusinessException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;
import java.util.List;
import java.util.UUID;

import static com.shop.common.constant.Constant.UTF8;

/**
 * 基础:用户服务接口实现
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private BaseUserAddressDao addressDao;
    
    @Override
    @Transactional
    public BaseUser register(BaseUser baseUser) {
        BaseUser user = userDao.findBaseUserByUsername(baseUser.getUsername());
        if (user != null) {
            throw new BusinessException("用户名已存在");
        }
        
        String salt = UUID.randomUUID().toString();
        HashCode hashingPwd = Hashing.md5().hashString(salt + "-" + baseUser.getPassword(), UTF8);
        
        baseUser.setSalt(salt);
        baseUser.setPassword(hashingPwd.toString());
        return userDao.save(baseUser);
    }
    
    @Override
    public BaseUser login(String account, String password) {
        BaseUser target = userDao.findBaseUserByUsername(account);
        if (target != null) {
            HashCode pwd = Hashing.md5().hashString(target.getSalt() + "-" + password, UTF8);
            if (pwd.toString().equals(target.getPassword())) {
                return target;
            }
        }
        
        return null;
    }
    
    @Override
    @Transactional
    public BaseUserAddress saveAddress(BaseUserAddress address) {
        return addressDao.save(address);
    }
    
    @Override
    public List<BaseUserAddress> listAddressByUser(String userId) {
        return addressDao.queryByUser(userId);
    }
    
    @Override
    public int deleteUserAddress(String id, String userId) {
        BaseUserAddress userAddress = addressDao.findOne(id);
        if (userAddress == null || !userAddress.getUser().getUid().equals(userId)) {
            return 0;
        }
        addressDao.delete(userAddress);
        return 1;
    }
}
