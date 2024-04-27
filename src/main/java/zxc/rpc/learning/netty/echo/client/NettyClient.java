package zxc.rpc.learning.netty.echo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import zxc.rpc.learning.netty.dto.RpcRequest;
import zxc.rpc.learning.netty.dto.RpcResponse;
import zxc.rpc.learning.netty.kryo.decod.NettyKryoDecode;
import zxc.rpc.learning.netty.kryo.decod.NettyKryoEncoder;
import zxc.rpc.learning.netty.kryo.serialize.KryoSerializer;

/**
 * Netty客户端类
 */
public class NettyClient {
    private String host;
    private int port;

    private static Bootstrap b;

    private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);

    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    static {
        b = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        KryoSerializer kryoSerializer = new KryoSerializer();
        b.group(group)
                .channel(NioSocketChannel.class) // 使用NioSocketChannel作为通道类型
                .handler(new LoggingHandler(LogLevel.INFO)) // 添加日志处理器
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000) // 设置连接超时时间
                .handler(new ChannelInitializer<SocketChannel>() { // 添加处理器
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new NettyKryoDecode(kryoSerializer, RpcResponse.class)); // 添加解码器
                        ch.pipeline().addLast(new NettyKryoEncoder(kryoSerializer, RpcRequest.class)); // 添加编码器
//                        ch.pipeline().addLast(new StringDecoder()); // 添加解码器
//                        ch.pipeline().addLast(new StringEncoder()); // 添加编码器
                        ch.pipeline().addLast(new NettyClientHandler()); // 添加业务处理器
                    }
                })
        ;
    }

    /**
     * 发送消息到服务端
     * 通过 {@link Bootstrap#connect(String, int)}连接服务端
     * 获取到{@link ChannelFuture}后，通过Channel想服务端发送消息，发送成功后，阻塞等待，知道
     *
     * @param request 消息体
     * @return 服务端返回的消息
     */
    public RpcResponse sendMessage(RpcRequest request) {
        try {
            ChannelFuture sync = b.connect(host, port).sync();
            logger.info("client connect: {}", host + ":" + port);
            Channel channel = sync.channel();
            logger.info("send message");
            if (channel != null) {
                channel.writeAndFlush(request).addListener(fut -> {
                    if (fut.isSuccess()) {
                        logger.info("send message success: [{}]", request.toString());
                    } else {
                        logger.error("send message fail: ", fut.cause());
                    }
                });
            }
            // 阻塞等待，直到Channel关闭
            Assert.notNull(channel, "channel is null");
            channel.closeFuture().sync();

            AttributeKey<RpcResponse> rpcResponse = AttributeKey.valueOf("rpcResponse");
            return channel.attr(rpcResponse).get();
        } catch (InterruptedException e) {
            logger.error("occur exception when connect server", e);
        }
        return null;
    }
}
