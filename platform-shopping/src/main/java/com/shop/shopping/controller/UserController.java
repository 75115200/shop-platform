package com.shop.shopping.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.shop.base.item.entity.Item;
import com.shop.base.order.entity.Cart;
import com.shop.base.order.service.OrderService;
import com.shop.base.user.entity.BaseUser;
import com.shop.common.base.BaseResult;
import com.shop.common.util.JsonUtil;
import com.shop.file.model.FileInfo;
import com.shop.shopping.model.CartDto;
import com.shop.shopping.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.shop.common.base.BaseResult.fail;
import static com.shop.common.base.BaseResult.success;
import static com.shop.common.constant.Constant.USER_SESSION;

/**
 * 用户相关操作
 * Created by ZZS on 2017/12/11.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ShoppingService shoppingService;
    @Autowired
    private OrderService orderService;

//
//    @Value("#{config.key}")
//    private String error;
//
//    @RequestMapping("/exception")
//    public void exception() {
//        System.out.println("test begin...");
//        throw new RuntimeException("test1");
//    }
//
//    @ExceptionHandler(RuntimeException.class)
//    public void handler1(Exception e) {
//        e.printStackTrace();
//        System.out.println("test1 ...");
//    }


    @RequestMapping("/cart.html")
    public String cart(Map<String, Object> attrs, HttpSession session) {
        BaseUser user = (BaseUser) session.getAttribute(USER_SESSION);
        List<CartDto> cartDtos = shoppingService.showCart(user.getUid());
        attrs.put("carts", cartDtos);
        return "/cart";
    }

    /**
     * 添加商品到购物车
     * @param cart 商品id, 库存码, 数量
     * @param session 用户信息
     * @return
     */
    @RequestMapping("/addItemToCart.json")
    @ResponseBody
    public BaseResult addItemToCart(@Validated Cart cart, HttpSession session) {
        BaseUser user = (BaseUser) session.getAttribute(USER_SESSION);
        cart.setUserId(user.getUid());
        cart.setAddTime(new Date());

        shoppingService.addCart(cart);
        return success();
    }

    /**
     * 从购物车中移除商品
     * @param cartId
     * @param session
     * @return
     */
    @RequestMapping("/removeFromCart.json")
    @ResponseBody
    public BaseResult removeFromCart(String cartId, HttpSession session) {
        BaseUser user = (BaseUser) session.getAttribute(USER_SESSION);
        int result = orderService.removeCart(cartId, user.getUid());
        if (result > 0) {
            return success();
        }
        return fail("操作失败");
    }

    /**
     * 获取购物车中商品数量
     * @param session
     * @return
     */
    @RequestMapping("/getCartCount.json")
    @ResponseBody
    public BaseResult getCartCount(HttpSession session) {
        BaseUser user = (BaseUser) session.getAttribute(USER_SESSION);
        return success(orderService.countCart(user.getUid()));
    }

    /**
     * 更新购物车中特定商品的数量
     * @param session
     * @param id
     * @param num
     * @return
     */
    @RequestMapping("/updateCartNum.json")
    @ResponseBody
    public BaseResult updateCartNum(HttpSession session, String id, int num) {
        BaseUser user = (BaseUser) session.getAttribute(USER_SESSION);
        orderService.updateCartNum(id, num, user.getUid());
        return success();
    }

    /**
     * 支付页面
     * @param cartIds 购物车商品id
     * @return
     */
    @RequestMapping("/payment.html")
    public String payment(String[] cartIds) {

        return "";
    }
}
