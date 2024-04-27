package zxc.rpc.learning.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaProducerDemo {

    public static void main(String[] args) {
        Producer<String, String> producer = getStringStringProducer();

        try {
            for (int i = 0; i < 100; i++) {
                String key = "key-" + i;
                String value = "value-" + i;
                ProducerRecord<String, String> record = new ProducerRecord<>("test-topic", key, value);
                producer.send(record); // 发送消息
                System.out.println("Sent message: (" + key + ", " + value + ")");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close(); // 关闭生产者
        }
    }

    private static Producer<String, String> getStringStringProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.88.128:9092"); // Kafka集群地址
        props.put("key.serializer", StringSerializer.class.getName()); // 键的序列化类
        props.put("value.serializer", StringSerializer.class.getName()); // 值的序列化类
        props.put("acks", "all"); // 确保消息被提交到所有副本
        props.put("retries", 0); // 发送失败后的重试次数
        props.put("batch.size", 16384); // 批量发送消息的大小
        props.put("linger.ms", 1); // 发送延迟时间
        props.put("buffer.memory", 33554432); // 生产者用于缓存的内存大小

        Producer<String, String> producer = new KafkaProducer<>(props);
        return producer;
    }
}
