package com.zrpc.test.JdkDynamicProxy;

import com.zrpc.annotation.client.ZrpcClient;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/6/27
 */
@Slf4j
public class TestJdkDynamic {

    private static KeyAndValue keyAndValue;// 这一步在spring中实现由spring注入KeyAndValue，这里因为是main函数，所以下面直接new

    public static void main(String[] args) throws IllegalAccessException {
        keyAndValue = new KeyAndValue();
        Class cls1 = KeyAndValue.class;
        Class cls2 = JdkDynamicInterface.class;
        Field[] fields = cls1.getDeclaredFields();
        for (Field field : fields){
            if(field.getAnnotation(ZrpcClient.class) != null){
                field.setAccessible(true);
                field.set(keyAndValue,Proxy.newProxyInstance(cls2.getClassLoader(),new Class[]{cls2},new JdkDynamicHandler()));
                field.setAccessible(false);

            }
        }
        String value = keyAndValue.test("zmm","smart");
        log.info("main.value:{}",value);
    }

}
