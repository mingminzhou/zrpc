package com.zrpc.client;

import collection.ZrpcMap;
import com.zrpc.annotation.client.ZrpcClient;
import com.zrpc.breaker.command.ZrpcBreakCommand;
import com.zrpc.client.context.ClientContext;
import com.zrpc.client.netty.ZrpcClientChannel;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import reqandresp.ZrpcRequest;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.util.UUID;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/7/16
 */
public class ClientAnnotationResolver implements BeanPostProcessor, InitializingBean {

    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> clz = bean.getClass();
        Field[] fields = clz.getDeclaredFields();
        if (fields == null || fields.length > 1) return bean;
        ZrpcMap<String, Object> clientProxyCache = ClientContext.INSTANCE.getClientProxyCache();
        ZrpcMap<String, ZrpcClientChannel> clientChannelCache = ClientContext.INSTANCE.getClientChannelCache();
        for (Field field : fields) {
            ZrpcClient zrpcClient = field.getAnnotation(ZrpcClient.class);
            if (zrpcClient == null) continue;
            String className = field.getType().getCanonicalName();

            if (!clientProxyCache.getMap().containsKey(className)) {
                ZrpcBreakCommand zrpcBreakCommand = new ZrpcBreakCommand(new ZrpcRequest(),null, 200,null);
                Object fieldProxy = Proxy.newProxyInstance(field.getType().getClassLoader(),
                        new Class[]{field.getClass()}, new ZrpcClientProxy(zrpcBreakCommand));
                clientProxyCache.put(className, fieldProxy);
            }
            field.setAccessible(true);
            try {
                field.set(bean, clientProxyCache.getMap().get(className));
            } catch (IllegalAccessException e) {
                new IllegalAccessException(className);
            }
            field.setAccessible(false);

        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
