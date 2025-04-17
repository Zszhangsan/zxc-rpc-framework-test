package zxc.rpc.zxcrpcremote.polymorphism;

public class ReferenceCounting {
    public static void main(String[] args) {
        Shared shared = new Shared();
        Composing[] composings = {
                new Composing(shared),
                new Composing(shared),
                new Composing(shared),
                new Composing(shared)
        };
        for (Composing c: composings) {
            c.dispose();
        }
    }

}
