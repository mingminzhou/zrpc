package com.zrpc.client;

import com.zrpc.breaker.command.ZrpcBreakCommand;
import com.zrpc.client.context.ClientContext;
import com.zrpc.client.netty.ZrpcClientChannel;
import reqandresp.ZrpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/7/25
 */
public class ZrpcClientProxy implements InvocationHandler {

    private ZrpcBreakCommand zrpcBreakCommand;

    public ZrpcClientProxy(ZrpcBreakCommand zrpcBreakCommand) {
        this.zrpcBreakCommand = zrpcBreakCommand;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String interfaceName = method.getDeclaringClass().getName();
        String methodName = method.getName();
        ZrpcRequest<Object> request = new ZrpcRequest<>();
        request.setInterfaceName(interfaceName);
        request.setMethodName(methodName);
        request.setSeqId(UUID.randomUUID().toString()); // TODO: 2018/7/16 后期加入ip以区分不同物理服务器上相同服务的请求id
        String serviceName = interfaceName + methodName;
        if (!ClientContext.INSTANCE.getClientChannelCache().getMap().containsKey(serviceName)) {
            ClientContext.INSTANCE.getClientChannelCache().put(serviceName, new ZrpcClientChannel());
        }
        ZrpcClientChannel clientChannel = ClientContext.INSTANCE.getClientChannelCache().get(serviceName);
        clientChannel.send(request);
        return "success";
    }
}
