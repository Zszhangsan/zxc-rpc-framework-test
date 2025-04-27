package zxc.rpc.zxcrpcremote.design.decoupling;

public class UpperCaseProcessor extends Processor{

    @Override
    public String processor(Object input) {
        return ((String) input).toUpperCase();
    }
}
