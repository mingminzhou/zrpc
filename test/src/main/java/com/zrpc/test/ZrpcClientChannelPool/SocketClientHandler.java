package com.zrpc.test.ZrpcClientChannelPool;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/7/17
 */
public class SocketClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();

        ByteBuf responseBuf = (ByteBuf)msg;
        responseBuf.markReaderIndex();

        int length = responseBuf.readInt();
        int seq = responseBuf.readInt();

        responseBuf.resetReaderIndex();

        //获取消息对应的callback
        SocketClient.CallbackService callbackService = ChannelUtils.<SocketClient.CallbackService>removeCallback(channel, seq);
        callbackService.receiveMessage(responseBuf);
    }
}
