package com.zrpc.test.ZrpcClientChannelPool;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/7/17
 */
public class BusinessServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        ByteBuf buf = (ByteBuf)msg;
        //1、读取消息长度
        int length = buf.readInt();

        //2、读取消息序列号
        int seq = buf.readInt();

        //3、读取消息头部
        byte[] head = new byte[8];
        buf.readBytes(head);
        String headString = new String(head);

        //4、读取消息体
        byte[] body = new byte[4];
        buf.readBytes(body);
        String bodyString = new String(body);

        //5、新建立一个缓存区,写入内容,返回给客户端
        UnpooledByteBufAllocator allocator = new UnpooledByteBufAllocator(false);
        ByteBuf responseBuf = allocator.buffer(20);
        responseBuf.writeInt(ChannelUtils.MESSAGE_LENGTH);
        responseBuf.writeInt(seq);
        responseBuf.writeBytes(headString.getBytes());
        responseBuf.writeBytes(bodyString.getBytes());

        //6、将数据写回到客户端
        channel.writeAndFlush(responseBuf);
    }
}
