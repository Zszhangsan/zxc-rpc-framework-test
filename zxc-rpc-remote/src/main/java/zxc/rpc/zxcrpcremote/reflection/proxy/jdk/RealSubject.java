package zxc.rpc.zxcrpcremote.reflection.proxy.jdk;

public class RealSubject implements Subject {
    @Override
    public void response() {
        System.out.println("RealSubject.response");
    }

    @Override
    public void request() {
        System.out.println("RealSubject.request");


    }
}
