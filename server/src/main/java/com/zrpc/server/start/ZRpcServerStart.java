package com.zrpc.server.start;

import com.zrpc.server.Start;
import com.zrpc.server.netty.RpcServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/6/22
 */
public class ZRpcServerStart implements Start {
    private static Logger logger = LoggerFactory.getLogger(ZRpcServerStart.class);
    private Integer workNUm = Runtime.getRuntime().availableProcessors() * 2;

    private static RpcServer RPC_SERVER;

    @Override
    public void start() {
        logger.info("gain start params");
        ZRpcServerStartParam param = getParams();
        logger.info("begin start......");
        RPC_SERVER = new RpcServer(param.getPort(), 1, workNUm);
        RPC_SERVER.start();
        logger.info("start success");
    }

    @Override
    public void stop() {
        RPC_SERVER.stop();
    }

    // TODO: 2018/6/22 param 做成可配置
    public ZRpcServerStartParam getParams() {
        ZRpcServerStartParam param = new ZRpcServerStartParam();
        param.setPort(8080);
        return param;
    }

    public static void main(String[] args) {
        ZRpcServerStart zRpcStart = new ZRpcServerStart();
        zRpcStart.start();
    }

}
