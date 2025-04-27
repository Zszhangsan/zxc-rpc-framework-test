package zxc.rpc.zxcrpcremote.design;

public class Oprations2 implements Oprations{
    @Override
    public void execute() {
        Oprations.printOps("oprations2");
    }


    public static void main(String[] args) {
        Oprations.runOps(new Oprations2(), new Oprations1());
    }
}
