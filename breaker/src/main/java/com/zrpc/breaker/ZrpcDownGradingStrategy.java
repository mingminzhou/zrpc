package com.zrpc.breaker;

/**
 *
 * 指令隔离
 *
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/6/26
 */
public interface ZrpcDownGradingStrategy<T> {

    T getFallBack();

    default void print(){
        // TODO: 2018/6/26 定制化zrpcCommand的特征
    }
}
