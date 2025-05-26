package zxc.rpc.zxcrpcremote.reflection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Container {
    // 存储用于实例化bean的方法
    private Map<Class<?>, Method> methods;

    private Object config;

    // 单例模式
    private Map<Class<?>, Object> services;

    /**
     * 初始化 Config中所有用于实例化类的方法
     *
     * @throws ClassNotFoundException
     */
    public void init() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        this.methods = new HashMap<Class<?>, Method>();
        this.services = new HashMap<>();
        Class<?> configClazz = Class.forName("zxc.rpc.zxcrpcremote.reflection.Config");
        Method[] declaredMethods = configClazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.getAnnotation(Bean.class) != null) {
                System.out.println(method.getName());
                this.methods.put(method.getReturnType(), method);
            }
        }
        this.config = configClazz.getConstructor().newInstance();
    }

    /**
     * 通过class对象作为键，从初始化好的服务方法集合中获取到实例化的方法，进行实例化，并返回实例化后的对象
     *
     * @param clazz
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public Object getServiceInstanceByClass(Class<?> clazz) throws InvocationTargetException, IllegalAccessException {
        if (this.services.containsKey(clazz)) {
            return this.services.get(clazz);
        } else {
            if (this.methods.containsKey(clazz)) {
                Method method = this.methods.get(clazz);
                Object obj = method.invoke(this.config);
                this.services.put(clazz, obj);
                return obj;
            }
        }
        return null;
    }

    /**
     * 通过注解Autowired 获取指定的构造器，将对象注入。
     * 通过getServiceInstanceByClass()方法获取对象，并注入
     * @param clazz
     * @return
     */
    public Object createInstance(Class<?> clazz) throws InvocationTargetException, IllegalAccessException, InstantiationException, NoSuchMethodException {
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            if (constructor.getAnnotation(Autowired.class) != null) {
                Class[] parameterTypes = constructor.getParameterTypes(); // 获取所有的参数类型
                Object[] args = new Object[parameterTypes.length]; // 存放所有参数的实例
                for (int i = 0; i <parameterTypes.length; i++) {
                    args[i] = getServiceInstanceByClass(parameterTypes[i]);
                }
                return constructor.newInstance(args);
            }
        }
        // 使用默认的无参构造器
        return clazz.getConstructor().newInstance();
    }


}
