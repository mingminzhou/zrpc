package com.zrpc.annotation;

import com.alibaba.fastjson.JSONObject;
import com.zrpc.annotation.service.ServiceDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * create by zmm 弄死熊猫
 * <p>zRpc server启动时用于存放service信息，key为interface的全路径名，value为service的实例对象
 * on 2018/6/27
 */
public class AnnotationMap<K,V> {
    private static Logger logger = LoggerFactory.getLogger(AnnotationMap.class);
    private Map<K,V> serviceDetailMap;

    public AnnotationMap(Map<K,V> map){
        this.serviceDetailMap = map;
    }

    public void put(K key, V value) {
        logger.info("put {}:{} into AnnotationMap",JSONObject.toJSONString(key),JSONObject.toJSONString(value));
        serviceDetailMap.put(key, value);
        logger.info("AnnotationMap.size:",serviceDetailMap.size());
    }

    public void remove(String key){
        logger.info("remove key {} from AnnotationMap",key);
        serviceDetailMap.remove(key);
        logger.info("AnnotationMap.size:",serviceDetailMap.size());
    }
}
