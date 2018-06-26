package com.zrpc.client.netty;

import com.zrpc.client.netty.handler.ZRPCClientHandler;
import decoandenco.ZrpcDecoder;
import decoandenco.ZrpcEncoder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpRequestDecoder;
import reqandresp.ZrpcRequest;
import reqandresp.ZrpcResponse;

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
                .addLast(new ZrpcEncoder<ZrpcRequest>())
                .addLast(new ZrpcDecoder(ZrpcResponse.class))
                .addLast(new ZRPCClientHandler());

    }
}
