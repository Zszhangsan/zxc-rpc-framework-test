package zxc.rpc.zxcrpcremote.reflection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Order {
    private Address address;
    private Customer customer;

    public Order() {
    }

    @Autowired
    public Order(Address address, Customer customer) {
        this.customer = customer;
        this.address = address;
    }
}
