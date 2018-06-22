package com.zrpc.server.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

/**
 * create by zmm 弄死熊猫
 * <p> netty server初始化以及启动类
 * on 2018/6/22
 */
public class RpcServer {
    private static Logger logger = LoggerFactory.getLogger(RpcServer.class);

    private Integer port;
    private ServerBootstrap bootstrap;
    private NioEventLoopGroup boss;
    private NioEventLoopGroup work;

    public RpcServer(Integer port,Integer bossNum,Integer workNum) {
        this.port = port;
        this.bootstrap = new ServerBootstrap();
        this.boss = new NioEventLoopGroup(bossNum == null ? 1 : bossNum);
        if(workNum == null){
            this.work = new NioEventLoopGroup();
        }else{
            this.work = new NioEventLoopGroup(workNum);
        }
        bootstrap.group(boss,work);
        bootstrap.channel(NioServerSocketChannel.class);
    }

    public void start(){
        logger.info("zRPC begin start.............");
        if(port == null){
            logger.info("zRPC is terminate，caused by ： port is null");
        }
        bootstrap.childHandler(new ChannelHandlerFactory());
        bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        bootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        bootstrap.option(ChannelOption.SO_REUSEADDR, true);
        try {
            ChannelFuture channelFuture = bootstrap.bind(new InetSocketAddress(port)).sync();
            logger.info("zRpc start success");
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            logger.error("zRPC start ERROR:",e);
        }finally {
            stop();
        }
    }

    public void stop() {
        // Shut down all event loops to terminate all threads.
        boss.shutdownGracefully();
        // Wait until all threads are terminated.
        boss.terminationFuture();
        // Shut down all event loops to terminate all threads.
        work.shutdownGracefully();
        // Wait until all threads are terminated.
        work.terminationFuture();
        logger.warn("rpc server stop on {}", port);
    }
}
