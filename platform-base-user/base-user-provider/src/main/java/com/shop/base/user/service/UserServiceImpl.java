package com.shop.base.user.service;

import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.shop.base.user.dao.UserDao;
import com.shop.base.user.entity.BaseUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.UUID;
import static com.shop.common.constant.Constant.UTF8;

/**
 * 基础:用户服务接口实现
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public BaseUser register(BaseUser baseUser) {
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
}
