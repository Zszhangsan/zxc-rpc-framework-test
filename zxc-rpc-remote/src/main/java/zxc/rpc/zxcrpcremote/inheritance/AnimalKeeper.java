package zxc.rpc.zxcrpcremote.inheritance;

import org.jetbrains.annotations.NotNull;

public class AnimalKeeper {
    public void feed(@NotNull Animal animal) {
        animal.eat();
    }

    public static void main(String[] args) {
        AnimalKeeper animalKeeper = new AnimalKeeper();
        Dog dog = new Dog("","");
        animalKeeper.feed(dog);
        Cat cat = new Cat();
        animalKeeper.feed(cat);
    }
}
