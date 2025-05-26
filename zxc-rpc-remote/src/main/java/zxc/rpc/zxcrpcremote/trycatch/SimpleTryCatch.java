package zxc.rpc.zxcrpcremote.trycatch;

/**
 * D:\idea-workspace-stu\zxc-rpc-framework-test\zxc-rpc-remote\src\main\java\zxc\rpc\zxcrpcremote\trycatch\SimpleTryCatch.java
 */
public class SimpleTryCatch {
    public static void simpleTryCatchTest() {
        try {
            testNPE();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void testNPE() {
        synchronized (SimpleTryCatch.class) {
            System.out.println("testNPE");
        }
    }
}
