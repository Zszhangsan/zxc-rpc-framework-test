package zxc.rpc.learning.proxy.dynamicProxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class DebugMethodInterceptor implements MethodInterceptor {


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before method interceptor" + method.getName());
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("after method interceptor" + method.getName());
        return result;
    }
}
