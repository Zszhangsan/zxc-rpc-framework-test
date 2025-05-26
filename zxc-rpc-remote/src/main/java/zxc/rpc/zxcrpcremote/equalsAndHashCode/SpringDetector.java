package zxc.rpc.zxcrpcremote.equalsAndHashCode;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SpringDetector {
    public static <T extends Groundhog> void detectSpring(Class<T> type) {
        try {
            Constructor<T> ghog = type.getConstructor(int.class);
            Map<T, Prediction> map = IntStream.range(0, 10)
                    .mapToObj(i -> {
                        try {
                            return ghog.newInstance(i);
                        } catch (InvocationTargetException e) {
                            throw new RuntimeException(e);
                        } catch (InstantiationException e) {
                            throw new RuntimeException(e);
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }).collect(Collectors.toMap(Function.identity(),
                            gh -> new Prediction()));
            map.forEach((k, v) -> System.out.println(k + ": " + v));
            Groundhog gh = ghog.newInstance(4);
            System.out.println("Looking up prediction for " + gh);
            if (map.containsKey(gh)) {
                System.out.println(map.get(gh));
            } else {
                System.out.println("Key not found " + gh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        detectSpring(Groundhog.class);
    }
}
