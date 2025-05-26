package zxc.rpc.zxcrpcremote.reflection;

import org.springframework.stereotype.Component;
import zxc.rpc.zxcrpcremote.annotation.Printable;
@Component
public class Customer {
    private String name;
    private String email;

    public Customer() {
    }

    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Printable
    public void printName() {
        System.out.println(name);
    }

    @Printable
    public void printEmail() {
        System.out.println(email);
    }




























}
