package zxc.rpc.zxcrpcremote.reflection.proxy.manul;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicInteger;

public class MyInterfaceFactory {

    private static final AtomicInteger counter = new AtomicInteger(0);


    private static File createAndReturnJavaFile(String className, MyHandler myHandler) throws IOException {

        String fun1 = myHandler.functionBody("fun1");
        String fun2 = myHandler.functionBody("fun2");
        String fun3 = myHandler.functionBody("fun3");
        String content = "package zxc.rpc.zxcrpcremote.reflection.proxy.manul;\n" +
                "\n" +
                "public class " + className +
                " implements MyInterface {\n" +
                " MyInterface myInterface;\n" +
                "    @Override\n" +
                "    public void fun1() {\n" +
                fun1 +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void fun2() {\n" + fun2 +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void fun3() {\n" + fun3 +
                "    }\n" +
                "}\n";

        File javaFile = new File(className + ".java");
        Files.writeString(javaFile.toPath(), content);
        return javaFile;
    }

    /**
     * 避免重复
     * 动态生成类名
     *
     * @return
     */
    private static String getClassName() {
        return "MyInterface$proxy" + counter.incrementAndGet();
    }

    /**
     * 动态生成方法体
     *
     * @param methodName
     * @return
     */
    private static String functionBody(String methodName) {
        return "System.out.println(\"" + methodName + "\");";
    }

    /**
     * 生成代理对象，通过全限类名
     *
     * @param className 全限类名 = 包名 + 类名
     * @return
     * @throws Exception
     */
    private static MyInterface newInstance(String className, MyHandler myHandler) throws Exception {
        Class<?> aClass = MyInterface.class.getClassLoader().loadClass(className);
        Constructor<?> constructor = aClass.getConstructor();
        MyInterface proxy = (MyInterface) constructor.newInstance();
        myHandler.setProxy(proxy);

        return proxy;
    }

    public static MyInterface createProxyObject(MyHandler myHandler) throws Exception {
        String className = getClassName();
        File andReturnJavaFile = createAndReturnJavaFile(className, myHandler);
        JavaFileCompiler.complie(andReturnJavaFile);
        return newInstance("zxc.rpc.zxcrpcremote.reflection.proxy.manul." + className, myHandler);

    }

}
