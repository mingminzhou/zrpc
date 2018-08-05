package com.zrpc.breaker.channel;

import reqandresp.ZrpcResponse;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/7/26
 */
public interface Channel {
    void send(Object msg) throws Exception;
    ZrpcResponse getResult();
}
