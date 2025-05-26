package zxc.rpc.zxcrpcremote.reflection;

import org.springframework.context.annotation.Bean;

/**
 * 保存一些初始化实例的方法
 */
public class Config {
    @Bean
    public Customer customer() {
        return  new Customer("customerName", "customerEmail.com");
    }

    @Bean
    public Address address() {
        return new Address("addressStreet", "addressPostCode");
    }

    public Message message() {
        return new Message("message");
    }
}
