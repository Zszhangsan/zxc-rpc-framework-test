package zxc.rpc.zxcrpcremote.design.decoupling;

public class LowPass extends Filter{
    double cutoff;

    public LowPass(double cutoff) {
        this.cutoff = cutoff;
    }

    @Override
    public WeavForm process(WeavForm input) {
        return input;
    }
}
