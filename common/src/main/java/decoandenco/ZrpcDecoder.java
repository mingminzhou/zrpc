package decoandenco;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import protostuff.ProtoStuffUtil;

import java.util.List;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/6/26
 */
public class ZrpcDecoder extends ByteToMessageDecoder {

    private Class deserializeClass;

    public ZrpcDecoder(Class deserializeClass) {
        this.deserializeClass = deserializeClass;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }
        in.markReaderIndex();
        int dataLength = in.readInt();
        if (dataLength < 0) {
            ctx.close();
        }
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }
        byte[] data = new byte[dataLength];
        in.readBytes(data);
        out.add(ProtoStuffUtil.deserialize(data, deserializeClass));
    }
}
