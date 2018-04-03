package com.shop.chat;


import com.shop.chat.manager.netty.NettyChannelManager;
import com.shop.chat.manager.netty.ChatChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 启动配置
 */
public class Application {
    public static void main(String[] args) throws InterruptedException {
        int inetPort = 10088;

        EventLoopGroup boss = new NioEventLoopGroup(1);
        EventLoopGroup worker = new NioEventLoopGroup(10);

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            ChannelFuture future = bootstrap.group(boss, worker)
                    .handler(new NettyChannelManager())
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChatChannelInitializer("/ws"))
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .bind(inetPort)
                    .sync();

            future.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            worker.isShuttingDown();
        }
    }
}
