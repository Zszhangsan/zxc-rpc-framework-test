package zxc.rpc.learning.netty.echo.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zxc.rpc.learning.netty.dto.RpcResponse;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(NettyClientHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            logger.info("client read msg");
            RpcResponse response = (RpcResponse) msg;
            // 声明一个AttributeKey对象，用于保存RpcResponse对象
            AttributeKey<RpcResponse> rpcResponse = AttributeKey.valueOf("rpcResponse");
            // 将服务端的返回结果保存到AttributeMap上，AttributeMap可以看作是一个Channel的共享数据源
            // AttributeMap的kley是AttributeKey，value是Attribute
            ctx.channel().attr(rpcResponse).set(response);
            ctx.channel().close();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("client caught exception", cause);
        ctx.close();
    }
}
