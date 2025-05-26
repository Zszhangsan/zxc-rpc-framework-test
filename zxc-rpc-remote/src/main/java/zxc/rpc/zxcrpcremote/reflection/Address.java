package zxc.rpc.zxcrpcremote.reflection;

import org.springframework.stereotype.Component;
import zxc.rpc.zxcrpcremote.annotation.Printable;
@Component
public class Address {
    private String street;
    private String postCode;

    public Address() {

    }
    public Address(String street, String postCode) {
        this.street = street;
        this.postCode = postCode;
    }

    @Printable
    public void printStreet() {
        System.out.println(street);
    }

    @Printable
    public void printPostCode() {
        System.out.println(postCode);
    }


}
