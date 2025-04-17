package zxc.rpc.zxcrpcremote.inheritance;

import java.util.Random;

public class RandomShapes {
    private Random random = new Random(47);
    public Shape get() {
        switch (random.nextInt(2)) {
            default:
            case 0: return new Circle();
            case 1: return new Square();
        }
    }

    public Shape[] arrays(int length) {
        Shape[] shapes = new Shape[length];
        for (int i = 0; i < shapes.length; i++) {
            shapes[i] = get();
        }
        return shapes;
    }

    public static void main(String[] args) {
        RandomShapes randomShape = new RandomShapes();
        Shape[] arrays = randomShape.arrays(9);
        for (Shape shape : arrays) {
            shape.draw();
        }
    }
}
