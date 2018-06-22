package com.zrpc.client.netty;

import com.zrpc.client.netty.handler.ZRPCClientHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/6/22
 */
public class ChannelHandlerFactory extends ChannelInitializer {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline channelPipeline = ch.pipeline();
        channelPipeline.addLast(new HttpRequestDecoder())
                .addLast(new HttpResponseDecoder())
                .addLast(new HttpRequestEncoder())
                .addLast(new HttpResponseEncoder())
                .addLast(new ZRPCClientHandler());

    }
}
