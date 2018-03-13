package com.shop.chat.manager.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

public class WebsocketFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
        System.out.println(msg);

        System.out.println(msg instanceof BinaryWebSocketFrame);
        if (msg instanceof BinaryWebSocketFrame) {
            BinaryWebSocketFrame binary = (BinaryWebSocketFrame) msg;
            ByteBuf buff = binary.content();
            byte[] bytes = new byte[buff.readableBytes()];
            buff.readBytes(bytes);
            System.out.println(new String(bytes, "utf-8"));

//            while (buff.isReadable()) {
//                System.out.println(buff.readByte());
//            }
        }

        if (msg instanceof TextWebSocketFrame) {
            TextWebSocketFrame textFram = (TextWebSocketFrame) msg;
            System.out.println(textFram.text());
        }
    }
}
