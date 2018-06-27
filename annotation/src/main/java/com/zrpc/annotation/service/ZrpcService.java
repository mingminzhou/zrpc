package com.zrpc.annotation.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/6/27
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ZrpcService {

    /**
     * 服务的描述 默认为空
     * @return
     */
    String desc() default "";

}
