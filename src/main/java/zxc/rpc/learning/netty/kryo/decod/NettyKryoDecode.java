package zxc.rpc.learning.netty.kryo.decod;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zxc.rpc.learning.netty.kryo.serialize.KryoSerializer;

import java.util.List;

public class NettyKryoDecode extends ByteToMessageDecoder {
    private static final Logger logger = LoggerFactory.getLogger(NettyKryoDecode.class);
    private final KryoSerializer serializer;
    private final Class<?> genericClass;

    /**
     * Netty传输的消息长度也就是对象序列化后对应的字节数组的大小，存储在ByteBuf头部
     */
    private static final int BODY_LENGTH = 4;

    public NettyKryoDecode(KryoSerializer serializer, Class<?> genericClass) {
        this.serializer = serializer;
        this.genericClass = genericClass;
    }

    /**
     * 接收消息，将ByteBuf转换为Object
     *
     * @param ctx 解码器关联的ChannelHandlerContext对象 the {@link ChannelHandlerContext} which this {@link ByteToMessageDecoder} belongs to
     * @param in  the {@link ByteBuf} from which to read data “入站”数据，也就是ByteBuf对象
     * @param out the {@link List} to which decoded messages should be added 解码之后的数据对象需要添加到out对象中。
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        // 1. byteBuf中写入的消息长度所占的字节数已经是4个了，所以byteBuf的可读字节必须大于4个
        if (in.readableBytes() >= BODY_LENGTH) {
            // 2. 标记当前readIndex的位置，以便后面充值readIndex的时候使用
            in.markReaderIndex();
            // 3. 读取消息的长度
            // 注意：消息长度是encode的时候我们自己写入的，参见NettyKryoEncoder的encode方法。
            int dataLength = in.readInt();
            // 4. 遇到不合理的情况，直接丢弃
            if (dataLength < 0 || in.readableBytes() < 0) {
                logger.error("data length or byteBuf readabelBtyes is not valid");
                return;
            }
            // 5. 如果可读字节数小于消息长度，则说明是不完整的消息，充值readIndex，等待下次接收
            if (in.readableBytes() < dataLength) {
                in.resetReaderIndex();
                return;
            }
            // 6. 读取消息
            byte[] bytes = new byte[dataLength];
            in.readBytes(bytes);
            // 7. 将bytes数组转换为我们需要的对象
            Object deserialize = serializer.deserialize(bytes, genericClass);
            out.add(deserialize);
            logger.info("successful decode ByteBuf to Object");
        }
    }
}
