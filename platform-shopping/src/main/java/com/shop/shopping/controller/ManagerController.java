package com.shop.shopping.controller;

import com.shop.base.item.entity.Item;
import com.shop.base.item.entity.ItemProperty;
import com.shop.base.item.entity.ItemPropertyDetail;
import com.shop.base.item.entity.ItemType;
import com.shop.base.item.service.ItemService;
import com.shop.base.order.entity.Order;
import com.shop.base.order.service.OrderService;
import com.shop.common.base.BaseResult;
import com.shop.common.base.Page;
import com.shop.file.model.FileInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

import static com.shop.common.base.BaseResult.fail;
import static com.shop.common.base.BaseResult.success;
import static com.shop.common.base.Page.createByBeginAndSize;
import static org.hibernate.internal.util.collections.CollectionHelper.isNotEmpty;

/**
 * 管理者Controller
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ItemService itemService;
    
    @Autowired
    private OrderService orderService;

    @RequestMapping("/home.html")
    public String home() {
        return "/admin/home";
    }

    @RequestMapping("/role.html")
    public String role() {
        return "/admin/role";
    }

    @RequestMapping("/roleAdd.html")
    public String roleAdd() {
        return "/admin/role_add";
    }

    @RequestMapping("/permission.html")
    public String permission() {
        return "/admin/permission";
    }

    @RequestMapping("/adminList.html")
    public String adminList() {
        return "/admin/admin_list";
    }

    @RequestMapping("/adminAdd.html")
    public String adminAdd() {
        return "/admin/admin_add";
    }

    @RequestMapping("/typeAdd.html")
    public String typeAdd(Map<String, Object> attrs) {
        List<ItemType> types = itemService.listAllType();
        attrs.put("types", types);
        return "/admin/type_add";
    }

    @RequestMapping("/itemList.html")
    public String itemList() {
        return "/admin/item_list";
    }

    @RequestMapping("/itemAdd.html")
    public String itemAdd(Map<String, Object> attrs) {
        List<ItemType> types = itemService.listAllType();
        attrs.put("types", types);

        if (!CollectionUtils.isEmpty(types)) {
            List<ItemProperty> properties = itemService.listProperties(types.get(0).getId(), 1);
            attrs.put("properties", properties);
        }
        return "/admin/item_add";
    }

    @RequestMapping("/propertyList.html")
    public String propertyList() {
        return "/admin/property_list";
    }

    @RequestMapping("/propertyAdd.html")
    public String propertyAdd(Map<String, Object> attrs) {
        List<ItemType> types = itemService.listAllType();
        attrs.put("types", types);
        return "/admin/property_add";
    }

    @RequestMapping("/propertyDetailAdd.html")
    public String propertyDetailAdd(String id, Map<String, Object> attrs) {
        ItemProperty property = itemService.getItemProperty(id);
        attrs.put("property", property);
        return "/admin/property_detail_add";
    }

    @RequestMapping("/orderList.html")
    public String orderList() {
        return "/admin/order_list";
    }
    
    @RequestMapping("/listOrder.json")
    @ResponseBody
    public BaseResult listOrderList(int begin, int pageSize, Order orderExample) {
        Page page = Page.createByBeginAndSize(begin, pageSize);
        return success(orderService.listOrderByExample(orderExample, page));
    }
    
    /**
     * 添加属性值
     * @param id
     * @param val
     * @return
     */
    @RequestMapping("/addPropertyDetail.json")
    @ResponseBody
    public BaseResult addPropertyDetail(String id, String val) {
        if (StringUtils.isBlank(val)) {
            return fail("参数错误，属性值不能为空");
        }

        ItemProperty property = itemService.getItemProperty(id);
        if (property == null) {
            return fail("属性不存在");
        }

        // 查找code应该从哪个数字开始
        List<ItemPropertyDetail> details = property.getDetails();
        Set<String> nameSet = new HashSet<>();
        int max = 0;
        for (ItemPropertyDetail d : details) {
            nameSet.add(d.getVal());
            String[] code = d.getCode().split(":");
            int seq = Integer.parseInt(code[1]);
            if (seq > max) {
                max = seq;
            }
        }
        // 添加并保存新的属性值
        String[] vals = val.split("\\|");
        for (int i = 0; i < vals.length; i++) {
            if (nameSet.contains(vals[i])) {
                return fail("已经存在相同属性");
            }
            property.addDetail(property.getCode() + ":"+ ++max, vals[i]);
        }
        itemService.saveItemProperty(property);
        return success();
    }

    /**
     * 添加商品属性
     * @param property 属性
     * @param val 属性具体参数
     * @return BaseResult
     */
    @RequestMapping("/addProperty.json")
    @ResponseBody
    public BaseResult addProperty(@Validated ItemProperty property, String val) {
        List<ItemProperty> properties = itemService.listProperties(property.getTypeId(), 1);
        for (ItemProperty p : properties) {
            if (StringUtils.equals(p.getCode(), property.getCode())) {
                return fail("属性标识已存在");
            }
            if (StringUtils.equals(p.getName(), property.getName())) {
                return fail("属性名已存在");
            }
        }

        if (StringUtils.isNotBlank(val)) {
            String[] vals = val.split("\\|");
            for (int i = 0; i < vals.length; i++) {
                property.addDetail(property.getCode() + ":"+ i, vals[i]);
            }
        }
        itemService.saveItemProperty(property);
        return success();
    }

    /**
     * 分页查询商品属性
     * @param typeId
     * @param page
     * @return
     */
    @RequestMapping("/listProperty.json")
    @ResponseBody
    public BaseResult listProperty(String typeId, int begin, int pageSize) {
        if (StringUtils.isBlank(typeId)) {
            typeId = null;
        }

        return success(itemService.listPropertyByPage(createByBeginAndSize(begin, pageSize), typeId));
    }

    /**
     * 添加商品类型
     * @param type
     * @return
     */
    @RequestMapping("/addType.json")
    @ResponseBody
    public BaseResult addType(ItemType type) {
        ItemType itemType = itemService.addItemType(type);
        if (itemType != null) {
            return success();
        }
        return fail("添加失败");
    }

    /**
     * 删除商品类型
     * @param id
     * @return
     */
    @RequestMapping("/delType.json")
    @ResponseBody
    public BaseResult delType(String id) {
        if (StringUtils.isBlank(id)) {
            return fail("参数错误:" + id);
        }
        itemService.delItemType(id);
        return success();
    }

    /**
     * 列出商品的必填属性
     * @param typeId 商品类型id
     * @return
     */
    @RequestMapping("listRequiredProperties.json")
    @ResponseBody
    public BaseResult listRequiredProperties(String typeId) {
        return success(itemService.listProperties(typeId, 1));
    }

    /**
     * 添加商品业务回调
     * @param jsonNode
     * @return
     */
    @RequestMapping("/addItem.json")
    @ResponseBody
    public BaseResult addItem(Item item) {
        itemService.addItem(item);
        return success();
    }

    /**
     * 分页列出商品
     * @param typeId
     * @param begin
     * @param pageSize
     * @return
     */
    @RequestMapping("/listItemByPage.json")
    @ResponseBody
    public BaseResult listItemByPage(String typeId, int begin, int pageSize) {
        Page page = Page.createByBeginAndSize(begin, pageSize);
        return success(itemService.listItemByType(typeId, page));
    }
    
    private List<String> getFileIds(List<FileInfo> fileInfos) {
        List<String> list = new ArrayList<>();
        for (FileInfo f : fileInfos) {
            list.add(f.getId());
        }
        return list;
    }
    
    /**
     * 更新订单信息
     * 根据订单id更新订单信息
     * @return
     */
    @RequestMapping("/updateOrder.json")
    @ResponseBody
    public BaseResult updateOrder(Order order) {
        orderService.updateOrder(order);
        return success();
    }
    
}
