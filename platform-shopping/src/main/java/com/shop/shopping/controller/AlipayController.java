package com.shop.shopping.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.shop.base.order.service.OrderService;
import com.shop.base.order.stereotype.OrderStatus;
import com.shop.shopping.service.ShoppingService;

import static com.shop.shopping.config.AlipayConfig.ALIPAY_PUBLIC_KEY;
import static com.shop.shopping.config.AlipayConfig.CHARSET;
import static com.shop.shopping.config.AlipayConfig.SIGN_TYPE;

/**
 * 阿里支付回调
 */
@Controller
@RequestMapping("/alipay")
public class AlipayController {
    private Logger logger = LoggerFactory.getLogger(AlipayController.class);
    
    @Autowired
    private OrderService orderService;
    
    @RequestMapping("/alipayNotify")
    public void alipayNotify(@RequestParam Map<String, String> param, HttpServletResponse
            response) {
        try {
            boolean verified = AlipaySignature.rsaCheckV1(param, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE);
            if (verified) {
                // 验签成功后,返回success
                response.getWriter().write("success");
                doPaidCallback(param);
            } else {
                // 验签失败则记录异常日志，并在response中返回failure.
                response.getWriter().write("failure");
            }
        } catch (AlipayApiException e) {
            logger.error("支付宝验签异常", e);
        } catch (IOException e) {
            logger.error("验签过程异常", e);
        }
    }
    
    private void doPaidCallback(Map<String, String> param) {
        String orderNum = param.get("out_trade_no");
        int result = orderService.updateOrderStatus(orderNum, OrderStatus.PAID.getStatus());
        if (result <= 0) {
            logger.error("订单号:" + orderNum + "更新已支付状态失败");
        }
    }
}
