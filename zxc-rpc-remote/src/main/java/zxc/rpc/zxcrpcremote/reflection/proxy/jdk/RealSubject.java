package zxc.rpc.zxcrpcremote.reflection.dynamic.jdk;

public class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("RealSubject.request");
    }
}
