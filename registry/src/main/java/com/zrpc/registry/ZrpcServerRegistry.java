package com.zrpc.registry;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/6/28
 */
public interface ZrpcServerRegistry<T> {

    T registry(T t);

}
