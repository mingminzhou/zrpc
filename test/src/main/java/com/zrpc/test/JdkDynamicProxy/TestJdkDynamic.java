package com.zrpc.test.JdkDynamicProxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/6/27
 */
@Slf4j
public class TestJdkDynamic {

    private static JdkDynamicInterface jdkDynamicInterface;

    public static void main(String[] args) {
        Class cls = JdkDynamicInterface.class;
        jdkDynamicInterface = (JdkDynamicInterface) Proxy.newProxyInstance(cls.getClassLoader(),new Class[]{cls},new JdkDynamicHandler());
        String value = jdkDynamicInterface.test("zmm","smart");
        log.info("main.value:{}",value);
    }

}
