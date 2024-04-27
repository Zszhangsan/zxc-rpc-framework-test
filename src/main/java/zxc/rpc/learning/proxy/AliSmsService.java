package zxc.rpc.learning.proxy;

public class AliSmsService {
    public String send(String message) {
        System.out.println("send ali sms service: " + message);
        return message;
    }
}
