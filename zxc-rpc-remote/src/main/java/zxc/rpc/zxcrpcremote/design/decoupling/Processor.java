package zxc.rpc.zxcrpcremote.design.decoupling;

public class Processor {
    public String getName() {
        return getClass().getSimpleName();
    }

    public Object processor(Object input) {
        return input;
    }
}
