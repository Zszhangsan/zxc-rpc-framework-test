package zxc.rpc.zxcrpcremote.reflection.proxy.manul;

public interface MyHandler {
    String functionBody(String param);

    default void setProxy(MyInterface myInterface) {

    }
}
