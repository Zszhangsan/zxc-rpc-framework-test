package zxc.rpc.learning.netty.kryo.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import zxc.rpc.learning.netty.dto.RpcRequest;
import zxc.rpc.learning.netty.dto.RpcResponse;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class KryoSerializer implements Serializer {

    /**
     * 由于Kryo是线程不安全的，所以这里使用ThreadLocal来管理Kryo对象
     */
    private static final ThreadLocal<Kryo> kryoThreadLocal = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        kryo.register(RpcResponse.class);
        kryo.register(RpcRequest.class);
        kryo.setReferences(true); // 默认值为true，是否关闭注册行为，关闭之后可能存在序列化问题，一般推荐设置为true
        kryo.setRegistrationRequired(false); // 默认值为false，是否关闭循环引用，可以提高性能，但是一般不推荐设置为true
        return kryo;
    });

    public KryoSerializer() {
        super();
    }

    @Override
    public byte[] serialize(Object obj) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            Output output = new Output(byteArrayOutputStream);
            Kryo kryo = kryoThreadLocal.get();
            // Object -> byte: 将对象序列化为byte数组
            kryo.writeObject(output, obj);
            kryoThreadLocal.remove();
            return output.toBytes();
        } catch (Exception e) {
            throw new SerializeException("序列化失败");
        }
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes)) {
            Input input = new Input(byteArrayInputStream);
            Kryo kryo = kryoThreadLocal.get();
            Object t = kryo.readObject(input, clazz);
            kryoThreadLocal.remove();
            return clazz.cast(t);
        } catch (Exception e) {
            throw new SerializeException("反序列化失败");
        }
    }
}
