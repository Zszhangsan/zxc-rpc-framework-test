package zxc.rpc.zxcrpcremote.design;

public interface AnInterface {
    void method1();

    void method2();

    default void method3() {
        System.out.println("default method3");
    }
}
