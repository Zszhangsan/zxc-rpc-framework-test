package zxc.rpc.zxcrpcremote.polymorphism;

import org.checkerframework.checker.units.qual.C;

public class LivingCreature {
    private Characteristic characteristic = new Characteristic("is alive");
    private Description description = new Description("basic living Creature");

    public LivingCreature() {
        System.out.println("LivingCreature()");
    }

    protected void dispose() {
        System.out.println("disposing LivingCreature");
        characteristic.dispose();
        description.dispose();
    }
}
