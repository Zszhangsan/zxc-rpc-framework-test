package zxc.rpc.learning.netty.echo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zxc.rpc.learning.netty.dto.RpcRequest;
import zxc.rpc.learning.netty.dto.RpcResponse;
import zxc.rpc.learning.netty.kryo.decod.NettyKryoDecode;
import zxc.rpc.learning.netty.kryo.decod.NettyKryoEncoder;
import zxc.rpc.learning.netty.kryo.serialize.KryoSerializer;

/**
 * 开启一个服务端用于接受客户端的请求并处理
 */
public class NettyServer {
    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);
    private final int port;

    public NettyServer(int port) {
        this.port = port;
    }

    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        KryoSerializer kryoSerializer = new KryoSerializer();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    // TCP默认开启了Nagle算法，该算法的作用是尽可能的发送大数据块，减少网络传输。TCP_NODELAY参数的作用是关闭Nagle算法，
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    // 是否开启TCP地层心跳机制
                    .childOption(ChannelOption.SO_BACKLOG, 128)
                    // 表示系统用于临时存放已完成三次握手的请求的队列的最大长度。如果连接无法建立，则客户端会一直处于等待状态，直到超时。如果建立频繁，服务器处理创建新连接较慢，可以适当调大这个参数。
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyKryoDecode(kryoSerializer, RpcRequest.class)); // 添加解码器
                            ch.pipeline().addLast(new NettyKryoEncoder(kryoSerializer, RpcResponse.class)); // 添加编码器
                            ch.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            // 绑定端口，同步等待成功
            ChannelFuture sync = b.bind(port).sync();
            // 等待服务端监听端口关闭
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error("NettyServer run error", e);
        } finally {
            // 退出，释放线程池资源
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
