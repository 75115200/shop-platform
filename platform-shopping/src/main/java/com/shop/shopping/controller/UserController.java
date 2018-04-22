package com.shop.shopping.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.shop.base.order.entity.Cart;
import com.shop.base.order.entity.Comment;
import com.shop.base.order.entity.Order;
import com.shop.base.order.entity.OrderDetail;
import com.shop.base.order.service.OrderService;
import com.shop.base.order.stereotype.OrderStatus;
import com.shop.base.user.entity.BaseUser;
import com.shop.base.user.entity.BaseUserAddress;
import com.shop.base.user.service.UserService;
import com.shop.common.base.BaseResult;
import com.shop.common.base.Page;
import com.shop.shopping.config.AlipayConfig;
import com.shop.shopping.model.CartDto;
import com.shop.shopping.param.OrderForm;
import com.shop.shopping.service.ShoppingService;

import static com.shop.common.base.BaseResult.fail;
import static com.shop.common.base.BaseResult.success;
import static com.shop.common.constant.Constant.USER_SESSION;

/**
 * 用户相关操作
 * Created by ZZS on 2017/12/11.
 */
@Controller
@RequestMapping("/user")
public class UserController implements AlipayConfig {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private ShoppingService shoppingService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    
    @RequestMapping("/ucenter.html")
    public String ucenter() {
        return "ucenter";
    }
    
    @RequestMapping("/orderList.html")
    public String orderList(HttpSession session, Page page, ModelMap modelMap) {
        BaseUser user = (BaseUser) session.getAttribute(USER_SESSION);
        modelMap.put("orderPage", orderService.listOrderByUserId(user.getUid(), page));
        return "orderList";
    }
    
    @RequestMapping("/person.html")
    public String person() {
        return "person";
    }
    
    /**
     * 购物车页面
     * @param attrs
     * @param session
     * @return
     */
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
        int result = orderService.removeCart(user.getUid(), cartId);
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
    public void payment(OrderForm orderForm, HttpSession session, HttpServletRequest request, HttpServletResponse
            response) throws IOException {
        BaseUser user = (BaseUser) session.getAttribute(USER_SESSION);
        Order order = shoppingService.checkout(orderForm, user.getUid());
        redirectToPay(response, request, order);
    }
    
    /**
     * 保存地址信息
     * @param address
     * @param session
     * @return
     */
    @RequestMapping("/saveAddress.json")
    @ResponseBody
    public BaseResult saveAddress(@Validated BaseUserAddress address, HttpSession session) {
        BaseUser user = (BaseUser) session.getAttribute(USER_SESSION);
        address.setUser(user);
        return success(userService.saveAddress(address));
    }
    
    /**
     * 列出当前用户地址信息
     * @param session
     * @return
     */
    @RequestMapping("/listAddress.json")
    @ResponseBody
    public BaseResult listAddress(HttpSession session) {
        BaseUser user = (BaseUser) session.getAttribute(USER_SESSION);
        return success(userService.listAddressByUser(user.getUid()));
    }
    
    @RequestMapping("/delAddress.json")
    @ResponseBody
    public BaseResult delAddress(String id, HttpSession session) {
        BaseUser user = (BaseUser) session.getAttribute(USER_SESSION);
        int result = userService.deleteUserAddress(id, user.getUid());
        if (result <= 0) {
            return fail("地址不存在");
        }
        return success();
    }
    
    @RequestMapping("/redirectToPay.html")
    public void redirectToPay(String orderId, HttpServletRequest request, HttpServletResponse response) throws
            IOException {
        redirectToPay(response, request, orderService.getOrderById(orderId));
    }
    
    /**
     * 评论页面
     * @param orderId 订单id
     * @param modelMap
     * @return
     */
    @RequestMapping("/comment.html")
    public String comment(String orderId, Map<String, Object> modelMap) {
        Order order = orderService.getOrderById(orderId);
        if (order != null) {
            modelMap.put("order", order);
            modelMap.put("details", order.getOrderDetails());
        }
        return "comment";
    }
    
    /**
     * 提交订单评论
     * @param orderNum 订单号
     * @param score 评分
     * @param content 内容
     * @param detailId 商品详情id
     * @param itemId 商品id
     * @return
     */
    @RequestMapping("/comment.json")
    @ResponseBody
    public BaseResult comment(String orderNum, String[] score, String[] content, String[] detailId, String[] itemId) {
        List<Comment> commentList = new ArrayList<>();
        for (int i = 0; i < score.length; i++) {
            Comment comment = new Comment();
            comment.setScore(Float.valueOf(score[i]));
            comment.setContent(content[i]);
            comment.setItemId(itemId[i]);
            comment.setOrderDetailId(detailId[i]);
            comment.setCreateTime(new Date());
            commentList.add(comment);
        }
        
        orderService.saveComments(commentList);
        orderService.updateOrderStatus(orderNum, OrderStatus.FINISHED);
        return success();
    }
    
    private void redirectToPay(HttpServletResponse httpResponse, HttpServletRequest request, Order order) throws
            IOException {
        AlipayClient alipayClient = new DefaultAlipayClient(URL, APP_ID, APP_PRIVATE_KEY, FORMAT, CHARSET,
                ALIPAY_PUBLIC_KEY, SIGN_TYPE);
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        alipayRequest.setReturnUrl(getBasePath(request) + AlipayConfig.RETURN_URL);
        alipayRequest.setNotifyUrl(getBasePath(request) + AlipayConfig.NOTIFY_URL);//在公共参数中设置回跳和通知地址
        
        List<OrderDetail> details = order.getOrderDetails();
        if (details.isEmpty()) {
            logger.error("订单{}的没有详情信息", order.getId());
            return;
        }
        // 计算价格
        BigDecimal total = BigDecimal.ZERO;
        // 处理body显示
        StringBuilder body = new StringBuilder();
        for (int i = 0; i < details.size(); i++) {
            OrderDetail detail = details.get(i);
            total = total.add(detail.getPrice().multiply(BigDecimal.valueOf(detail.getNum())));
            
            if (i != 0) {
                body.append(",");
            }
            body.append(detail.getItemName());
        }
        // 处理subject显示
        String firstItemName = details.get(0).getItemName();
        String subject = details.size() == 1 ? firstItemName : firstItemName + "等,共" + details.size() + "件商品";
        
        alipayRequest.setBizContent("{" + "    \"out_trade_no\":\"" + order.getOrderNum() + "\"," + "    " +
                "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"," + "    \"total_amount\":" + total.toString() + "," +
                "" + "    \"subject\":\"" + subject + "\"," + "    \"body\":\"" + body.toString() + "\"," + "    " +
                "\"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," + "    " +
                "\"extend_params\":{" + "    \"sys_service_provider_id\":\"2088511833207846\"" + "    }" + "  }");
        //填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        httpResponse.setContentType("text/html;charset=" + CHARSET);
        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
        httpResponse.getWriter().flush();
        httpResponse.getWriter().close();
    }
    
    private String getBasePath(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request
                .getContextPath();
    }
}
