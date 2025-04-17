package zxc.rpc.zxcrpcremote.polymorphism;

import org.checkerframework.checker.units.qual.C;

public class Animal extends LivingCreature{
    private Characteristic characteristic = new Characteristic("has heart");
    private Description description = new Description("Animal not Vegetable");

    public Animal() {
        System.out.println("Animal()");
    }

    public static void AnimalStatic() {
        System.out.println("AnimalStatic()");
    }

    @Override
    protected void dispose() {
        System.out.println("animal dispose");
        characteristic.dispose();
        description.dispose();
        super.dispose();
    }
}
