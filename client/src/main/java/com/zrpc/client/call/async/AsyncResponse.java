package com.zrpc.client.call.async;

import com.zrpc.client.call.CallResponse;
import reqandresp.ZrpcResponse;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/7/25
 */
public class AsyncResponse implements CallResponse<ZrpcResponse> {

    @Override
    public ZrpcResponse call() {
        return new ZrpcResponse();
    }
}
