package com.zrpc.client.call;

/**
 *
 * client获取server返回值或者做具体回调操作
 *
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/7/25
 */
public interface CallResponse<T> {
    T call();
}
