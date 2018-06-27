package com.zrpc.annotation.proxy;

import java.lang.reflect.Proxy;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/6/27
 */
public class ClientAnnotationProxy {

    public <T> T newProxyInstance(Class<?> interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, (proxy, method, params) -> {
            return new Object();
        });
    }

}
