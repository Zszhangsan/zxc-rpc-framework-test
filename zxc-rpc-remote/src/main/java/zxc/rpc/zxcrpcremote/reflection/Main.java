package zxc.rpc.zxcrpcremote.reflection;

import zxc.rpc.zxcrpcremote.annotation.Printable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    /**
     *
     * @param args
     * @throws ClassNotFoundException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws NoSuchFieldException
     */
    public static  void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException, NoSuchFieldException {
        Container container = new Container();
        container.init();
        Object obj = container.getServiceInstanceByClass(Class.forName("zxc.rpc.zxcrpcremote.reflection.Customer"));
        System.out.println(obj);
        Class<?> aClass = Class.forName("zxc.rpc.zxcrpcremote.reflection.Order");
        Object orderObj = container.createInstance(aClass);
        System.out.println(orderObj);
        Field field = aClass.getDeclaredField("address");
        field.setAccessible(true);
        Object fieldValue = field.get(orderObj);
        System.out.println(fieldValue);
        Method[] methods = fieldValue.getClass().getMethods();
        for (Method method : methods) {
            if (method.getDeclaredAnnotation(Printable.class) != null) {
                method.setAccessible(true);
                method.invoke(fieldValue);
            }
        }


    }
}
