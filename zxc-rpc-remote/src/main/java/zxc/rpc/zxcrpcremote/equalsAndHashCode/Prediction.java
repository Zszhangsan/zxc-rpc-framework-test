package zxc.rpc.zxcrpcremote.equalsAndHashCode;

import java.util.Random;

public class Prediction {
    protected Random random = new Random(47);

    @Override
    public String toString() {
        return random.nextBoolean() ? "Six more weeks of Winter!" : "早春";
    }
}
