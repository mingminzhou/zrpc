package com.zrpc.test;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/6/26
 */
public class TestBreaker extends HystrixCommand<String> {

    private final String name;

    public TestBreaker(String name) {
        super(HystrixCommandGroupKey.Factory.asKey(name));

        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return null;
    }

    @Override
    public String toString() {
        return "hello :" + name;
    }
}
