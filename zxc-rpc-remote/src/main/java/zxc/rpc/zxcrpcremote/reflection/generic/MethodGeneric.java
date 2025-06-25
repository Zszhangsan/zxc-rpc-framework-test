package zxc.rpc.zxcrpcremote.reflection.generic;

import java.util.ArrayList;

public class MethodGeneric {
    public static void main(String[] args) {
        // 反射的操作都是编译之后的操作，运行时操作
        ArrayList list = new ArrayList();
        ArrayList<String> list1 = new ArrayList();
        Class c1 = list.getClass();
        Class c2 = list1.getClass();
        System.out.println(c1 == c2);
        /**
         * c1 == c2结果返回true，说明编译之后集合的泛型是去泛型化的
         * Java中集合的泛型是防止错误输入的，只在编译阶段有效
         * 绕过编译就无效了
         * 验证方式：我们可以通过方法的反射操作，绕过编译
         */

    }
}
