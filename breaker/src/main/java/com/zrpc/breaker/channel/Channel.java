package com.zrpc.breaker.channel;

import io.netty.channel.ChannelPromise;
import reqandresp.ZrpcResponse;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/7/26
 */
public interface Channel {
    ChannelPromise send(Object msg) throws Exception;
    ZrpcResponse getResult(ChannelPromise promise);
}
