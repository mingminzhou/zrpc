package com.zrpc.test.JdkDynamicProxy;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/6/27
 */
@Slf4j
public class JdkDynamicHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("proxy start");
        int i = 0;
        for (Object obj : args) {
            if (i == 0) {
                log.info("key:{}", obj);
            } else {
                log.info("value:{}", obj);
            }
            i++;
        }
        log.info("JdkDynamicHandler.method.params:{}", method.getParameters());
        log.info("JdkDynamicHandler.method.paramsType:{}", method.getParameterTypes());
        log.info("JdkDynamicHandler.method.paramsCount:{}", method.getParameterCount());
        log.info("JdkDynamicHandler.method.paramsAnnotations:{}", method.getParameterAnnotations());
        log.info("JdkDynamicHandler.method.GenericParameterTypes:{}", method.getGenericParameterTypes());
        log.info("JdkDynamicHandler.method.TypeParameters:{}", method.getTypeParameters());
        return "success";
    }
}
