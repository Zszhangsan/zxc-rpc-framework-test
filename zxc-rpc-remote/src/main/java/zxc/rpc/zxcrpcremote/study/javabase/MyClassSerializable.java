package zxc.rpc.zxcrpcremote.study.javabase;

import com.esotericsoftware.kryo.NotNull;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class MyClassSerializable implements Serializable {

    private static <T extends Number> double add (T a, T b) {
        return a.doubleValue() + b.doubleValue();
    }

    public static void main(String[] args) {

        String abc = new String("abc");

        Class<MyClassCloneable> myClassCloneableClass = MyClassCloneable.class;
        try {
            Constructor<MyClassCloneable> constructor = myClassCloneableClass.getConstructor();
            MyClassCloneable myClassCloneable = constructor.newInstance();

            MyClassCloneable o = (MyClassCloneable) Class.forName("").getConstructor().newInstance();
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
