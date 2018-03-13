package com.shop.shopping.controller;

import com.shop.base.item.service.ItemService;
import com.shop.base.user.entity.BaseUser;
import com.shop.base.user.service.UserService;
import com.shop.common.base.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

import static com.shop.common.base.BaseResult.fail;
import static com.shop.common.base.BaseResult.success;

/**
 * 无需权限，共用Controller
 */
@Controller
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;

    /**
     * 主页
     */
    @RequestMapping("/home.html")
    public String home() {
        return "index";
    }

    /**
     * 类目页面
     * @return
     */
    @RequestMapping("/category.html")
    public String category() {
        return "category";
    }

    /**
     * 详情页面
     * @return
     */
    @RequestMapping("/detail.html")
    public String detail() {
        return "detail";
    }

    /**
     * 注册
     * @param baseUser
     * @return
     */
    @RequestMapping("/register.json")
    @ResponseBody
    public BaseResult register(BaseUser baseUser) {
        userService.register(baseUser);
        return success();
    }

    /**
     * 登录
     * @param account
     * @param password
     * @return
     */
    @RequestMapping("/login.json")
    @ResponseBody
    public BaseResult login(String account, String password) {
        BaseUser user = userService.login(account, password);
        if (user != null) {
            return success();
        }
        return fail("用户名或密码错误");
    }
}
