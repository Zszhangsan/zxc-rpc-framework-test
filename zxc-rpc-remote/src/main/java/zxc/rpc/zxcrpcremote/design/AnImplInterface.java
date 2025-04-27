package zxc.rpc.zxcrpcremote.design;

import org.checkerframework.checker.units.qual.A;

public class AnImplInterface implements AnInterface {
    @Override
    public void method1() {
        System.out.println("method1");
        method3();
    }

    @Override
    public void method2() {
        System.out.println("method2");
        method3();
    }

    public static void main(String[] args) {
        AnImplInterface anImplInterface = new AnImplInterface();
        anImplInterface.method1();
        anImplInterface.method2();
        anImplInterface.method3();
    }
}
