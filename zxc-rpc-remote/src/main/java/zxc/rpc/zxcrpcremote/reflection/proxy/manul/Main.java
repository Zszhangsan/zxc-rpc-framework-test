package zxc.rpc.zxcrpcremote.reflection.proxy.manul;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws Exception {
        MyInterface proxyObject = MyInterfaceFactory.createProxyObject(new TestHandler());
        proxyObject.fun1();
        proxyObject.fun2();
        proxyObject.fun3();
        proxyObject = MyInterfaceFactory.createProxyObject(new TestNumberHandler());
        proxyObject.fun1();
        proxyObject.fun2();
        proxyObject.fun3();
        System.out.println("-----------------------");
        proxyObject = MyInterfaceFactory.createProxyObject(new LogHandler(proxyObject));
        proxyObject.fun1();
        proxyObject.fun2();
        proxyObject.fun3();
    }

    static class TestHandler implements MyHandler {

        @Override
        public String functionBody(String param) {
            return "System.out.println(\"" + param + "\");";
        }
    }
    static class TestNumberHandler implements MyHandler {

        @Override
        public String functionBody(String param) {
            StringBuilder s = new StringBuilder();
            s.append("System.out.println(\"1\");");
            s.append("System.out.println(\"" + param + "\");");
            return s.toString();
        }
    }
    static class LogHandler implements MyHandler {

        MyInterface myInterface;
        public LogHandler(MyInterface myInterface) {
            this.myInterface = myInterface;
        }

        @Override
        public String functionBody(String param) {

            String context =
                    "System.out.println(\"before\");\n" +
                    "myInterface." + param +"();\n" +
                    "        System.out.println(\"after\");";
            System.out.println(context);

            return context;
        }

        @Override
        public void setProxy(MyInterface myInterface)  {
            Class<? extends MyInterface> aClass = myInterface.getClass();
            Field field = null;
            try {
                field = aClass.getDeclaredField("myInterface");
                field.setAccessible(true);
                field.set(myInterface, this.myInterface);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
    static class BeforHandler implements MyHandler {

        MyInterface myInterface;
        public BeforHandler(MyInterface myInterface) {
            this.myInterface = myInterface;
        }

        @Override
        public String functionBody(String param) {

            String context =
                    "System.out.println(\"before\");\n" +
                    "myInterface." + param +"();\n" +
                    "        System.out.println(\"after\");";
            System.out.println(context);

            return context;
        }

        @Override
        public void setProxy(MyInterface myInterface)  {
            Class<? extends MyInterface> aClass = myInterface.getClass();
            Field field = null;
            try {
                field = aClass.getDeclaredField("myInterface");
                field.setAccessible(true);
                field.set(myInterface, this.myInterface);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }
    }
}
