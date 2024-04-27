package zxc.rpc.learning.netty.echo.server;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zxc.rpc.learning.netty.dto.RpcRequest;
import zxc.rpc.learning.netty.dto.RpcResponse;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * netty server handler
 *
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);
    private static final AtomicInteger atomicInteger = new AtomicInteger();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        RpcRequest request = (RpcRequest) msg;
        logger.info("server receive msg: [{}], times:[{}]", request, atomicInteger.getAndIncrement());
        RpcResponse messageFromSever = RpcResponse.builder().message("message from sever").build();
        ChannelFuture channelFuture = ctx.writeAndFlush(messageFromSever);
        channelFuture.addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("client caught exception", cause);
        ctx.close();
    }
}
