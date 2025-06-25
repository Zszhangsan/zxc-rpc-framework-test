package zxc.rpc.zxcrpcremote.reflection.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    private Object target;

    public MyInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("jdk 通用逻辑前置");
        Object invoke = method.invoke(target, args);
        System.out.println("jdk 通过逻辑后置");
        return invoke;

    }
}
