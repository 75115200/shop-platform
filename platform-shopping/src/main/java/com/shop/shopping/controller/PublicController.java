package com.shop.shopping.controller;

import com.shop.base.item.entity.Item;
import com.shop.base.item.entity.ItemProperty;
import com.shop.base.item.entity.ItemType;
import com.shop.base.item.service.ItemService;
import com.shop.base.user.entity.BaseUser;
import com.shop.base.user.service.UserService;
import com.shop.common.base.BaseResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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
    public String category(String typeId, Map<String, Object> attrs) {
        if (StringUtils.isBlank(typeId)) {
            List<ItemType> types = itemService.listAllType();
            attrs.put("types", types);
        } else {
            ItemType type = itemService.getType(typeId);
            attrs.put("type", type);
            attrs.put("properties", type.getProperties());
        }
        return "category";
    }

    /**
     * 详情页面
     * @return
     */
    @RequestMapping("/detail.html")
    public String detail(String itemId, Map<String, Object> attr) {
        Item item = itemService.getItem(itemId);
        attr.put("item", item);
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
