import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.Charset;

public class TestClient {
    public static void main(String[] args) throws InterruptedException {
        String inetHost = "127.0.0.1";
        int inetPort = 10086;

        EventLoopGroup loopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bs = new Bootstrap();
            ChannelFuture future = bs.group(loopGroup)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    String firstMsg = "第一条消息";
                                    ByteBuf msg = Unpooled.buffer();
                                    byte[] bytes = firstMsg.getBytes(Charset.forName("UTF-8"));
                                    System.out.println(bytes.length);
                                    msg.writeBytes(bytes);
                                    ctx.writeAndFlush(msg);
                                }

                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    super.channelRead(ctx, msg);
                                }
                            });
                        }
                    }).connect(inetHost, inetPort).sync();

            future.channel().closeFuture().sync();
        } finally {
            loopGroup.shutdownGracefully();
        }
    }
}
