package collection;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * create by zmm 弄死熊猫
 * 对map做统一处理
 * on 2018/6/27
 */
public class ZrpcMap<K, V> {
    private static Logger logger = LoggerFactory.getLogger(ZrpcMap.class);
    private Map<K, V> map;

    public ZrpcMap(Map<K, V> map) {
        this.map = map;
    }

    public void put(K key, V value) {
        logger.info("put {}:{} into ZrpcMap", JSONObject.toJSONString(key), JSONObject.toJSONString(value));
        map.put(key, value);
        logger.info("ZrpcMap.size:", map.size());
    }

    public void remove(K key) {
        logger.info("remove key {} from ZrpcMap", key);
        map.remove(key);
        logger.info("ZrpcMap.size:", map.size());
    }

    public Map<K,V> getMap(){
        return map;
    }

    public V get(K k){
        return map.get(k);
    }
}
