package com.zrpc.breaker;

/**
 *
 * 降级策略，暴露接口，给用户自己实现降级策略
 *
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/6/26
 */
public interface ZrpcDownGradingStrategy<T> {

    T getFallBack();

    default Throwable fallBack(String detail){
        return new Throwable(detail);
    }
}
