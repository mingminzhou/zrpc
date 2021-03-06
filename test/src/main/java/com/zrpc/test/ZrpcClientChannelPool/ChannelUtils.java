package com.zrpc.test.ZrpcClientChannelPool;

import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

import java.util.Map;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/7/17
 */
public class ChannelUtils {
    public static final int MESSAGE_LENGTH = 16;
    public static final AttributeKey<Map<Integer, Object>> DATA_MAP_ATTRIBUTEKEY = AttributeKey.valueOf("dataMap");
    public static <T> void putCallback2DataMap(Channel channel, int seq, T callback) {
        channel.attr(DATA_MAP_ATTRIBUTEKEY).get().put(seq, callback);
    }

    public static <T> T removeCallback(Channel channel, int seq) {
        return (T) channel.attr(DATA_MAP_ATTRIBUTEKEY).get().remove(seq);
    }


}
