package zxc.rpc.zxcrpcremote.reflection.proxy;

public class Proxy implements Subject{
    private RealSubject realSubject;
    @Override
    public void request() {
        System.out.println("request pre");
        realSubject.request();
        System.out.println("request post");
    }

    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        proxy.request();
    }
}
