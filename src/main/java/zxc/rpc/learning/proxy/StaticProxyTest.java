package zxc.rpc.learning.proxy;

import zxc.rpc.learning.proxy.dynamicProxy.CglibProxyFactory;
import zxc.rpc.learning.proxy.dynamicProxy.JdkProxyFactory;
import zxc.rpc.learning.proxy.staticProxy.SmsProxy;

public class StaticProxyTest {
    public static void main(String[] args) {
        SmsService smsService = new SmsServiceImpl();
        SmsProxy smsProxy = new SmsProxy(smsService);
        smsProxy.send("java");

        SmsService jdkSmsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        jdkSmsService.send("java1");

        AliSmsService proxy = (AliSmsService) CglibProxyFactory.getProxy(AliSmsService.class);
        proxy.send("java2");

    }

}
