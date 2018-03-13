package com.shop.chat.manager;

import java.util.Set;

/**
 * 通道管理接口
 */
public interface ChannelManager {

    /**
     * 提供发送消息给某个Channel的方法
     * @param channelId 通道的唯一标识
     * @param Object 要发送的消息
     */
    void sendMsg(ChannelId channelId, Object msg);

    /**
     * 获取存活的ChannelId
     * @return 已经服务器建立连接的channel
     */
    Set<ChannelId> getAliveChannel();

}
