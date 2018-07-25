package com.zrpc.client.context;

import collection.ZrpcMap;
import com.zrpc.client.netty.ZrpcClientChannel;
import io.netty.channel.Channel;

import java.util.HashMap;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/7/16
 */
public enum ClientContext {

    INSTANCE;

    /**
     * 存储服务端接口的方法名和方法代理的对应关系
     */
    private ZrpcMap<String, Object> clientProxyCache;

    /**
     * 存储server端每个方法对应的client端channel
     */
    private ZrpcMap<String, ZrpcClientChannel> clientChannelCache;


    /**
     * 获取client代理 TODO: 2018/7/16 后续考虑是否加锁
     *
     * @return
     */
    public ZrpcMap<String, Object> getClientProxyCache() {
        if (clientProxyCache == null) {
            clientProxyCache = new ZrpcMap(new HashMap(16));
        }
        return clientProxyCache;
    }

    /**
     * @param serverMethodName 服务端方法绝对路径形式的名称
     * @param proxy            对应的代理
     */
    public void putClientProxyCache(String serverMethodName, Object proxy) {
        clientProxyCache.put(serverMethodName, proxy);
    }

    public void putClientChannel(String name, ZrpcClientChannel clientChannel) {
        clientChannelCache.put(name, clientChannel);
    }

    public ZrpcClientChannel getClientChannel(String name){
        return clientChannelCache.get(name);
    }

    /**
     * 获取clientChannel // TODO: 2018/7/16 后续考虑是否加锁
     * @return
     */
    public ZrpcMap<String, ZrpcClientChannel> getClientChannelCache(){
        if (clientChannelCache == null) {
            clientChannelCache = new ZrpcMap(new HashMap(16));
        }
        return clientChannelCache;
    }

}
