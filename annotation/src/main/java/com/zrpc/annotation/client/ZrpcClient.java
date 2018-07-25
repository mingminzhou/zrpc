package com.zrpc.annotation.client;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * create by zmm 弄死熊猫
 * <p> // TODO: 2018/7/16 后期添加降级、熔断、限流参数
 * on 2018/6/27
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ZrpcClient {

    String name() default "";

}
