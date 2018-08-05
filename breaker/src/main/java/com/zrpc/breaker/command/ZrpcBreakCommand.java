package com.zrpc.breaker.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.zrpc.breaker.ZrpcDownGradingStrategy;
import com.zrpc.breaker.channel.Channel;
import exception.ZrpcException;
import exception.ZrpcExceptionEnum;
import reqandresp.ZrpcRequest;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/6/26
 */
public class ZrpcBreakCommand<T> extends HystrixCommand<Object> {

    private String name;

    private ZrpcRequest<T> zrpcRequest;

    private Channel[] channels;

    private ZrpcDownGradingStrategy downGradingStrategy;

    public ZrpcBreakCommand(ZrpcRequest<T> zrpcRequest, Channel[] channels, int executionIsolationThreadTimeoutInMilliseconds, ZrpcDownGradingStrategy downGradingStrategy) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(zrpcRequest.getMethodName()))
                .andCommandKey(HystrixCommandKey.Factory.asKey(zrpcRequest.getInterfaceName())));
        this.name = zrpcRequest.getInterfaceName() + ":" + zrpcRequest.getMethodName();
        this.zrpcRequest = zrpcRequest;
        this.downGradingStrategy = downGradingStrategy;
        this.channels = channels;
    }

    @Override
    protected Object run() throws Exception {
        if (channels == null || channels.length == 0)
            throw new ZrpcException(ZrpcExceptionEnum.NO_CHANNELS_INIT.getExceptionDesc(), new Throwable(), ZrpcExceptionEnum.NO_CHANNELS_INIT.getExceptionDesc());
        channels[0].send(zrpcRequest); // TODO: 2018/7/26 完善从channel数组中获取channel的策略
        return channels[0].getResult();
    }

    @Override
    protected Object getFallback() {
        return downGradingStrategy == null ? downGradingStrategy.fallBack(name) : downGradingStrategy.getFallBack();
    }

}
