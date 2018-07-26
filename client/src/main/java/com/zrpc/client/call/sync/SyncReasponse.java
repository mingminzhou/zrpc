package com.zrpc.client.call.sync;

import com.zrpc.client.call.CallResponse;
import reqandresp.ZrpcResponse;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/7/25
 */
public class SyncReasponse implements CallResponse<ZrpcResponse> {
    @Override
    public ZrpcResponse call() {
        return new ZrpcResponse();
    }
}
