package decoandenco;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import protostuff.ProtoStuffUtil;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/6/26
 */
public class ZrpcEncoder<T> extends MessageToByteEncoder<T> {

    @Override
    protected void encode(ChannelHandlerContext ctx, T msg, ByteBuf out) throws Exception {
        byte[] data = ProtoStuffUtil.serialize(msg);
        out.writeInt(data.length);
        out.writeBytes(data);
    }
}
