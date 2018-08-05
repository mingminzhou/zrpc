package com.zrpc.client.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reqandresp.ZrpcResponse;

import java.net.InetSocketAddress;
import java.util.concurrent.locks.ReentrantLock;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/6/22
 */
public class ZrpcClientChannel implements com.zrpc.breaker.channel.Channel {
    private static Logger logger = LoggerFactory.getLogger(ZrpcClientChannel.class);
    // client属性
    private Channel clientChannel;

    private Bootstrap bootstrap;

    private ReentrantLock reentrantLock;

    public ZrpcClientChannel() {
        this.bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup(1));
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.ALLOCATOR,PooledByteBufAllocator.DEFAULT);
    }

    // 连接zRPCServer的基本属性  // TODO: 2018/6/22 从服务注册中心读取（zk或者eureka）
    private String host;
    private int port;
    private int timeOut;
    private int reConnectTimes;
    private long waitDoneTime;

    // TODO: 2018/6/22 interfaceName、methodName通过注解方式加载
    private String interfaceName;
    private String methodName;

    public Channel getClientChannel(){
        return clientChannel;
    }

    public void connect() throws Exception {
        logger.info("rpc connect to ({}/{}):",host,port);
        bootstrap.handler(new ChannelHandlerFactory());

        ChannelFuture future = null;
        int times = 0;
        while (true){
            try {
                future = bootstrap.connect(new InetSocketAddress(host,port)).sync();
            } catch (InterruptedException e) {
                logger.error("zRpcClient connect to server error:",e);
            }
            while (!future.isDone()) Thread.sleep(waitDoneTime);
            if(future.isSuccess()) {
                logger.info("connect to ({}/{}) success:" ,host , port);
                break;
            }

            logger.info("trying reconnect to ({}/{}):" ,host , port);
            if(++times > reConnectTimes){
                throw new Exception(String.format("connect refused: (%s/%d)", host, port));
            }
        }

        clientChannel = future.channel();

        logger.info("");
    }

    public boolean isConnected(){
        if(clientChannel == null) return clientChannel.isActive();
        return false;
    }

    public void reConnect() throws Exception {
        if(!isConnected()) connect();
    }

    public void disconnect(){
        if(isConnected()) clientChannel.disconnect();
    }

    private void writeRequest(Object msg) throws Exception {
        reConnect();
        clientChannel.writeAndFlush(msg);
    }

    @Override
    public void send(Object msg) throws Exception {
        writeRequest(msg);
    }

    @Override
    public ZrpcResponse getResult() {
        return null;
    }


}
