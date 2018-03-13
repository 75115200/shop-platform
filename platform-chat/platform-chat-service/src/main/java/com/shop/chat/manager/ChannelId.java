package com.shop.chat.manager;

import java.io.Serializable;

/**
 * 通道唯一标识接口
 */
public interface ChannelId {

    /**
     * 获取ChannelId
     * @return Serializable 序列化的唯一标识
     */
    Serializable getChannelId();
}
