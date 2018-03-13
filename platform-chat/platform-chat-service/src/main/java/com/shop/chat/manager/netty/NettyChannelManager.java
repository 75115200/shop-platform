package com.shop.chat.manager.netty;

import com.shop.chat.manager.ChannelId;
import com.shop.chat.manager.ChannelManager;
import com.shop.chat.manager.ChannelManagerContext;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 基于Netty的通道管理
 */
@Component
public class NettyChannelManager extends ChannelInboundHandlerAdapter implements ChannelManager {

    private ChannelManagerContext context;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("this is manager");
        System.out.println(msg);

        if (msg instanceof ByteBuf) {
            ByteBuf buf = (ByteBuf) msg;
            byte b = buf.getByte(buf.readerIndex());
            System.out.println(b);
        }
//        ByteBuf byteBuf = (ByteBuf) msg;
//        System.out.println(((ByteBuf) msg).toString(Charset.forName("UTF-8")));
        ctx.fireChannelRead(msg);
    }

    @Override
    public void sendMsg(ChannelId channelId, Object msg) {

    }

    @Override
    public Set<ChannelId> getAliveChannel() {
        return null;
    }
}
