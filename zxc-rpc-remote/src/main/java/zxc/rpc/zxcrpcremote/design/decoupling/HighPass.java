package zxc.rpc.zxcrpcremote.design.decoupling;

public class HighPass extends Filter{
    double cutoff;

    public HighPass(double cutoff) {
        this.cutoff = cutoff;
    }

    @Override
    public WeavForm process(WeavForm input) {
        return input;
    }
}
