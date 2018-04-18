package com.shop.shopping.controller;

import com.shop.base.item.entity.*;
import com.shop.base.item.service.ItemService;
import com.shop.base.order.entity.Comment;
import com.shop.base.order.service.OrderService;
import com.shop.base.user.entity.BaseUser;
import com.shop.base.user.service.UserService;
import com.shop.common.base.BaseResult;
import com.shop.common.base.Page;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;
import java.util.*;

import static com.shop.common.base.BaseResult.fail;
import static com.shop.common.base.BaseResult.success;
import static com.shop.common.constant.Constant.USER_SESSION;

/**
 * 无需权限，共用Controller
 */
@Controller
@RequestMapping("/public")
public class PublicController {
    public static final int DEFAULT_PAGE_SIZE = 10;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private OrderService orderService;

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
            List<ItemProperty> properties = itemService.listProperties(typeId, 1);
            attrs.put("properties", properties);

            ItemType type = itemService.getType(typeId);
            attrs.put("type", type);
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

        Set<String> propertyCodeSet = new HashSet<>();
        Set<String> detailCodeSet = new HashSet<>();
        // 库存code转属性
        List<ItemSku> skus = item.getSku();
        for (ItemSku sku : skus) {
            String[] detailCodes = StringUtils.split(sku.getCode(), ";");
            for (String detailCode : detailCodes) {
                detailCodeSet.add(detailCode);

                propertyCodeSet.add(detailCode.split(":")[0]);
            }
        }

        // 移除库存中没有的属性值
        List<ItemProperty> properties = itemService.listItemPropertyByCode(propertyCodeSet);
        for (ItemProperty p : properties) {
            for (Iterator<ItemPropertyDetail> iter = p.getDetails().iterator(); iter.hasNext();) {
                if (!detailCodeSet.contains(iter.next().getCode())) {
                    iter.remove();
                }
            }
        }

        attr.put("properties",properties);
        attr.put("item", item);
        attr.put("score", orderService.countAvgScore(itemId));
        return "detail";
    }

    /**
     * 获取商品库存信息
     * @param itemId 商品id
     * @param skuCode 商品库存code值
     * @return
     */
    @RequestMapping("/getSku.json")
    @ResponseBody
    public BaseResult getSku(String itemId, String skuCode) {
        Item item = itemService.getItem(itemId);
        if (item == null) {
            return fail("商品不存在");
        }

        for (ItemSku sku : item.getSku()) {
            if (sku.getCode().equals(skuCode)) {
                return success(sku);
            }
        }

        return fail("没有相应的库存信息");
    }

    /**
     * 注册
     * @param baseUser
     * @return
     */
    @RequestMapping("/register.json")
    @ResponseBody
    public BaseResult register(@Validated BaseUser baseUser, HttpSession session) {
        BaseUser user = userService.register(baseUser);
        session.setAttribute(USER_SESSION, user);
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
    public BaseResult login(String account, String password, HttpSession session) {
        BaseUser user = userService.login(account, password);
        if (user != null) {
            session.setAttribute(USER_SESSION, user);
            return success();
        }
        return fail("用户名或密码错误");
    }

    @RequestMapping("/noPermission.json")
    @ResponseBody
    public BaseResult noPermission() {
        return fail("没有权限访问");
    }
    
    /**
     * 获取商品的评价
     * @param itemId
     * @param page
     * @return
     */
    @RequestMapping("/getComment.json")
    @ResponseBody
    public BaseResult getComment(String itemId, @RequestParam(defaultValue = "1") int page) {
        return success(orderService.listCommentByItemId(itemId, page, DEFAULT_PAGE_SIZE));
    }
}
