package zxc.rpc.zxcrpcremote.reflection.proxy.jdk;

import java.lang.reflect.Proxy;

public class Client {
    public static void main(String[] args) {

        Subject subject = new RealSubject();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(subject);
        Subject proxy = (Subject) Proxy.newProxyInstance(
                subject.getClass().getClassLoader(), subject.getClass().getInterfaces(),
                myInvocationHandler);

        proxy.request();
        proxy.response();
        System.out.println("end");

    }
}
