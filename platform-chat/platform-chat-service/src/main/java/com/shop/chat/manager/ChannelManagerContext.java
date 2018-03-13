package com.shop.chat.manager;

import io.netty.util.internal.ConcurrentSet;

import java.io.Serializable;
import java.util.Set;

/**
 * 通道管理上下文
 */
public class ChannelManagerContext {
    /**
     * 包含可以访问通道的白名单
     */
    private final Set<Serializable> whitelist = new ConcurrentSet<>();

    /**
     * 添加白名单
     * @param id
     */
    public void addWhiteList(Serializable id) {
        whitelist.add(id);
    }
}
