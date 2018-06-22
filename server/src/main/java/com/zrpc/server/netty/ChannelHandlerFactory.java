package com.zrpc.server.netty;

import com.zrpc.server.netty.handler.ZRPCServerHandler;
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
    protected void initChannel(Channel ch) {
        ChannelPipeline cp = ch.pipeline();
        cp.addLast(new HttpRequestDecoder())
                .addLast(new HttpResponseDecoder())
                .addLast(new HttpRequestEncoder())
                .addLast(new HttpResponseEncoder())
                .addLast(new ZRPCServerHandler());
    }
}
