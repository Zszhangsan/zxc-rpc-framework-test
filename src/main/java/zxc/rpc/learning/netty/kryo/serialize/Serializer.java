package zxc.rpc.learning.netty.kryo.serialize;

public interface Serializer {
    /**
     * 序列化
     * @param obj 要序列化的对象
     * @return  字节数组
     */
    byte[] serialize(Object obj);

    /**
     * 反序列化
     * @param bytes 反序列化后的字节数组
     * @param clazz 反序列化后的对象类型
     * @return  反序列化后的对象
     * @param <T>   反序列化后的对象类型
     */
    <T> T deserialize(byte[] bytes, Class<T> clazz);
}
