package com.shop.chat.manager.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

public class ChatChannelInitializer extends ChannelInitializer<Channel>{

    private String websocketPath;

    public ChatChannelInitializer(String websocketPath) {
        this.websocketPath = websocketPath;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("codec", new HttpServerCodec());
        pipeline.addLast("aggregator", new HttpObjectAggregator(64 * 1024));
        pipeline.addLast(new WebSocketServerProtocolHandler(websocketPath));
        pipeline.addLast(new WebsocketFrameHandler());
    }
}
