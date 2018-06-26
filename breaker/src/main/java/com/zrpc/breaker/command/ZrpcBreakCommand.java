package com.zrpc.breaker.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.zrpc.breaker.ZrpcCommand;
import reqandresp.ZrpcRequest;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/6/26
 */
public class ZrpcBreakCommand extends HystrixCommand<Object>{

    private ZrpcCommand zrpcCommand;

    protected ZrpcBreakCommand(ZrpcRequest request, HystrixThreadPoolKey threadPool, int executionIsolationThreadTimeoutInMilliseconds,ZrpcCommand zrpcCommand) {
        super(HystrixCommandGroupKey.Factory.asKey(request.getInterfaceName() + ":" + request.getMethodName()), threadPool, executionIsolationThreadTimeoutInMilliseconds);
        this.zrpcCommand = zrpcCommand;
    }

    @Override
    protected Object run() throws Exception {
        return null;// TODO: 2018/6/26 调用下游接口的具体逻辑
    }

    @Override
    protected Object getFallback() {
        return zrpcCommand.getFallBack();// TODO: 2018/6/26 实现具体降级和熔断后的回调逻辑
    }

}
