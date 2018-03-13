package com.shop.shopping.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.shop.base.item.entity.Item;
import com.shop.base.item.service.ItemService;
import com.shop.common.base.BaseResult;
import com.shop.common.util.JsonUtil;
import com.shop.file.model.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import static com.shop.common.base.BaseResult.success;

/**
 * 管理者Controller
 */
@Controller
@RequestMapping("/manager")
public class ManagerController {

    @Autowired
    private ItemService itemService;

    /**
     * 添加商品业务回调
     * @param jsonNode
     * @return
     */
    @RequestMapping("/addItem")
    @ResponseBody
    public BaseResult addItem(@RequestBody JsonNode jsonNode) {
        Item item = JsonUtil.jsonToBean(jsonNode.get("data").toString(), Item.class);
        TypeReference<List<FileInfo>> typeReference = new TypeReference<List<FileInfo>>() {};
        List<FileInfo> fileInfos = JsonUtil.jsonToBean(jsonNode.get("fileInfo").toString(), typeReference);

        item.setFiles(getFileIds(fileInfos));
        itemService.addItem(item);
        return success(jsonNode);
    }

    private List<String> getFileIds(List<FileInfo> fileInfos) {
        List<String> list = new ArrayList<>();
        for (FileInfo f : fileInfos) {
            list.add(f.getId());
        }
        return list;
    }
}
