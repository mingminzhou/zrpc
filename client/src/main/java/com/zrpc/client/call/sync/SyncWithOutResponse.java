package com.zrpc.client.call.sync;

import com.zrpc.client.call.CallResponse;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/7/25
 */
public class SyncWithOutResponse implements CallResponse<String> {
    @Override
    public String call() {
        return "success";
    }
}
