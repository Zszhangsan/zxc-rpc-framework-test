package zxc.rpc.zxcrpcremote.inheritance;

public class Dog extends Animal{
    public Dog(String id, String name) {
        super(id, name);
    }
    @Override
    public void eat() {
        System.out.println("狗狗吃骨头！！");
    }
}
