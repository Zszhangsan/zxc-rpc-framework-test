package zxc.rpc.zxcrpcremote.design.decoupling;

public class Applicator {

    public static void apply(Processor processor, Object s) {
        System.out.println("using processor: " + processor.getName());
        System.out.println(processor.processor(s));
    }

    public static void main(String[] args) {
        String s = "We are such stuff as dreams are made on";
        apply(new DownCaseProcessor(), s);
        apply(new UpperCaseProcessor(), s);
    }
}
