package zxc.rpc.zxcrpcremote.design.decoupling;

public class WeavForm {
    private static long count;

    private final long id = count++;

    @Override
    public String toString() {
        return "WeavForm{" +
                "id=" + id +
                '}';
    }
}
