package com.shop.chat.manager;

import java.io.Serializable;
import java.util.UUID;

/**
 * 默认的ChannelId实现，采用UUID生成策略
 */
public class DefaultChannelId implements ChannelId{

    @Override
    public Serializable getChannelId() {
        return UUID.randomUUID().toString();
    }
}
