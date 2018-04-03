package com.shop.shopping.service;

import com.shop.base.item.entity.Item;
import com.shop.base.item.entity.ItemProperty;
import com.shop.base.item.entity.ItemPropertyDetail;
import com.shop.base.item.entity.ItemSku;
import com.shop.base.item.service.ItemService;
import com.shop.base.order.entity.Cart;
import com.shop.base.order.service.OrderService;
import com.shop.common.base.BusinessException;
import com.shop.shopping.model.CartDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShoppingServiceImpl implements ShoppingService {
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
}
