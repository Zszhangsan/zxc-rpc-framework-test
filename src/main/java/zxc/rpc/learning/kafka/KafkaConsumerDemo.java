package zxc.rpc.learning.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerDemo {

    public static void main(String[] args) {
        KafkaConsumer<String, String> consumer = getStringStringKafkaConsumer();
        consumer.subscribe(Arrays.asList("test-topic")); // 订阅主题

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100)); // 拉取记录
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                }
            }
        } finally {
            consumer.close(); // 关闭消费者
        }
    }

    private static KafkaConsumer<String, String> getStringStringKafkaConsumer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.88.128:9092"); // Kafka集群地址
        props.put("group.id", "test-group"); // 消费者组ID
        props.put("key.deserializer", StringDeserializer.class.getName()); // 键的反序列化类
        props.put("value.deserializer", StringDeserializer.class.getName()); // 值的反序列化类
        props.put("auto.offset.reset", "earliest"); // 从最早的记录开始消费
        props.put("enable.auto.commit", "false"); // 启用自动提交偏移量
        props.put("auto.commit.interval.ms", "1000"); // 自动提交偏移量的时间间隔

        return new KafkaConsumer<>(props);
    }
}
