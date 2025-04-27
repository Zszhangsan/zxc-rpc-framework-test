package zxc.rpc.zxcrpcremote.design.decoupling;

public class Filter {
    public String getName() {
        return getClass().getSimpleName();
    }

    public WeavForm process(WeavForm input) {
        return input;
    }
}
