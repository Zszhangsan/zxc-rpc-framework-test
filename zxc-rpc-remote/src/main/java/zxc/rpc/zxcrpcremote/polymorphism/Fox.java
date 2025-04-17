package zxc.rpc.zxcrpcremote.polymorphism;

public class Fox extends Animal{
    private Characteristic characteristic = new Characteristic("狐狸");
    private Description description = new Description("狐狸地描述");

    public Fox() {
        System.out.println("Fox()");
    }

    public static void FoxStatic() {
        System.out.println("FoxStatic()");
    }

    @Override
    protected void dispose() {
        System.out.println("狐狸消失");
        characteristic.dispose();
        description.dispose();
        super.dispose();
    }

    public static void main(String[] args) {
        Fox fox = new Fox();
        System.out.println("bye！");
        fox.dispose();
    }
}
