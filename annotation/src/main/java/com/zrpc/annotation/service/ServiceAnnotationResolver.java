package com.zrpc.annotation.service;

import com.zrpc.annotation.AnnotationMap;

import java.util.HashMap;
import java.util.Map;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/6/27
 */
public class ServiceAnnotationResolver {

    private ServiceAnnotationResolver() {
    }

    public static AnnotationMap<String,Object> annotationMap = new AnnotationMap<>(new HashMap<>(16));

    public static void resolve(){
//        Map<String, Object> serviceBeanMap = ctx.getBeansWithAnnotation(ZrpcService.class);
        // TODO: 2018/6/27 解析@ZRpcService注解
    }


}
