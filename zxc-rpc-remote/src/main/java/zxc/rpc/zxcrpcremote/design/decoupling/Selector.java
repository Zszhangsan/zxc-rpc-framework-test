package zxc.rpc.zxcrpcremote.design.decoupling;

public interface Selector {
    boolean end();

    Object current();

    void next();
}
