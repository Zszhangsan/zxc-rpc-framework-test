package zxc.rpc.zxcrpcremote.study.javabase;

public class MyClassCloneable implements Cloneable{

    private NestedClass nestedClass;
    @Override
    protected Object clone() throws CloneNotSupportedException {
        MyClassCloneable myClass = (MyClassCloneable) super.clone();
        myClass.nestedClass = (NestedClass) nestedClass.clone();
        return myClass;
    }


    static class NestedClass implements Cloneable {
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
}
