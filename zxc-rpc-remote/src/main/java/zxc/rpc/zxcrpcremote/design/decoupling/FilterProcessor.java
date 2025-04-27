package zxc.rpc.zxcrpcremote.design.decoupling;

public class FilterProcessor {
    public static void main(String[] args) {

        WeavForm weavForm = new WeavForm();
        Applicator.apply(new FilterAdapter(new LowPass(1)), weavForm);
        Applicator.apply(new FilterAdapter(new HighPass(3)), weavForm);
    }
}
