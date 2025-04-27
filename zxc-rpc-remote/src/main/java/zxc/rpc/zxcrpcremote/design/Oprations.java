package zxc.rpc.zxcrpcremote.design;

public interface Oprations {
    void execute();

    static void runOps(Oprations... ops) {
        for (Oprations op : ops) {
            op.execute();
        }
    }
    static void printOps(String msg) {
        System.out.println("msg: " + msg);
    }
}
