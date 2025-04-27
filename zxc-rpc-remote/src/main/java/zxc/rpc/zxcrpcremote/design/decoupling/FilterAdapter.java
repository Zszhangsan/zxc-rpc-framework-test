package zxc.rpc.zxcrpcremote.design.decoupling;

public class FilterAdapter extends Processor{

    private Filter filter;

    public FilterAdapter(Filter filter) {
        this.filter = filter;
    }

    @Override
    public String getName() {
        return filter.getName();
    }

    @Override
    public Object processor(Object input) {
        return filter.process((WeavForm) input);
    }
}
