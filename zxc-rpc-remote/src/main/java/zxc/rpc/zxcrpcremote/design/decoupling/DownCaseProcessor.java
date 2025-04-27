package zxc.rpc.zxcrpcremote.design.decoupling;

public class DownCaseProcessor extends Processor{
    @Override
    public String processor(Object input) {
        return ((String) input).toLowerCase();
    }
}
