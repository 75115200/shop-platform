package com.shop.shopping.service;

import com.google.common.collect.Sets;
import com.shop.base.item.entity.Item;
import com.shop.base.item.entity.ItemProperty;
import com.shop.base.item.entity.ItemSku;
import com.shop.base.item.service.ItemService;
import com.shop.base.order.entity.Cart;
import com.shop.base.order.entity.Order;
import com.shop.base.order.entity.OrderDetail;
import com.shop.base.order.service.OrderService;
import com.shop.base.order.stereotype.OrderStatus;
import com.shop.common.base.BusinessException;
import com.shop.shopping.model.CartDto;
import com.shop.shopping.model.OrderForm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.apache.commons.collections.CollectionUtils.isEmpty;

@Service
public class ShoppingServiceImpl implements ShoppingService {
    private Logger logger = LoggerFactory.getLogger(ShoppingServiceImpl.class);
    
    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    @Override
    public void addCart(Cart cart) {
        Item item = itemService.getItem(cart.getItemId());
        if (item == null) {
            throw new BusinessException("商品不存在");
        }

        // 校验库存
        List<ItemSku> skus = item.getSku();
        boolean checked = false;
        for (ItemSku s : skus) {
            if (cart.getSkuCode().equals(s.getCode())) {
                checked = true;
                break;
            }
        }

        orderService.saveCart(cart);
    }

    @Override
    public List<CartDto> showCart(String userId) {
        List<CartDto> result = new ArrayList<>();
        List<Cart> carts = orderService.listCartByUserId(userId);

        for (Cart c : carts) {
            CartDto cart = new CartDto();
            cart.setCartId(c.getId());
            cart.setItemSkuCode(c.getSkuCode());
            cart.setNum(c.getNum());

            Item item = itemService.getItem(c.getItemId());
            for (ItemSku sku : item.getSku()) {
                if (sku.getCode().equals(c.getSkuCode())) {
                    cart.setPrice(sku.getPrice());
                }
            }
            cart.setItemName(item.getName());
            cart.setItemPic(item.getFiles().get(0));

            List<ItemProperty> properties = new ArrayList<>();
            String[] detailCodes = c.getSkuCode().split(";");
            for (String detailCode : detailCodes) {
                properties.add(itemService.queryByDetailCode(detailCode));
            }
            cart.setProperties(properties);

            result.add(cart);
        }
        return result;
    }

    @Override
    @Transactional
    public Order checkout(OrderForm orderForm, String userId) {
        // 所有的购物车存在相关信息
        String[] cartIds = orderForm.getCartIds();
        List<Cart> carts = orderService.findCart(Sets.newHashSet(cartIds));
        if (cartIds == null || cartIds.length == 0 || isEmpty(carts)) {
            throw new BusinessException("没有要结算的商品");
        }
    
        // 订单需要用户填写收货人等信息
        Order order = new Order();
        BeanUtils.copyProperties(orderForm, order);
        
        List<OrderDetail> details = new ArrayList<>();
        // 移除购物车中的商品
        for (Cart cart : carts) {
            if (!cart.getUserId().equals(userId)) {
                logger.error("用户{}企图下单非自己购物车的商品{}", userId, cart.getId());
                throw new BusinessException("非法操作");
            }
            
            Item item = itemService.queryNameAndSkuByItemIdAndSkuCode(cart.getItemId(), cart.getSkuCode());
            if (item.getSku().isEmpty()) {
                throw new BusinessException("不存在的商品库存信息");
            }
            ItemSku itemSku = item.getSku().get(0);
            
            int num = cart.getNum();
            int result = itemService.reduceItemSku(itemSku.getCode(), num);
            if (result < 0) {
                throw new BusinessException("商品已售空");
            }
            
            // 创建订单详情信息
            OrderDetail detail = new OrderDetail();
            detail.setItemName(item.getName());
            detail.setNum(num);
            detail.setPrice(itemSku.getPrice());
            detail.setItemSku(cart.getSkuCode());
            details.add(detail);
            detail.setOrder(order);
        }
    
        orderService.removeCart(userId, cartIds);
    
        order.setOrderNum(getOrderNum(userId));
        order.setUid(userId);
        order.setCreateTime(new Date());
        order.setStatus(OrderStatus.PAYING.getStatus());
        order.setOrderDetails(details);
        return orderService.saveOrder(order);
    }
    
    /**
     * 生成订单号
     * 时间戳后8位，加上用户id hascode后3位
     * @return
     */
    private String getOrderNum(String userId) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String userIdHashCode = String.valueOf(userId.hashCode());
        return timestamp.substring(timestamp.length() - 8) + userIdHashCode.substring(userIdHashCode.length() - 3);
    }
}
