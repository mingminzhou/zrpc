package com.zrpc.test.ZrpcClientChannelPool;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/7/17
 */
public class IntegerFactory {
    private static class SingletonHolder {
        private static final AtomicInteger INSTANCE = new AtomicInteger();
    }

    private IntegerFactory(){}

    public static final AtomicInteger getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
