package zxc.rpc.learning.netty.kryo.decod;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.AllArgsConstructor;
import zxc.rpc.learning.netty.kryo.serialize.KryoSerializer;

/**
 * 自定义编码器
 * 网络传输需要通过字节流来实现，ByteBuf可以看作是Netty提供的字节数据的容器，使用它会让我们更加方便地处理字节数据。
 */
@AllArgsConstructor
public class NettyKryoEncoder extends MessageToByteEncoder<Object> {
    private final KryoSerializer serializer;
    private final Class<?> genericClass;

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        if (genericClass.isInstance(msg)) {
            // 1. 将对象转换为byte
            byte[] body = serializer.serialize(msg);
            // 2. 读取消息的长度
            int length = body.length;
            // 3. 写入消息对应的字节数组长度，writeIndex加4
            out.writeInt(length);
            // 4. 将消息的字节数组写入out
            out.writeBytes(body);
        }
    }
}
