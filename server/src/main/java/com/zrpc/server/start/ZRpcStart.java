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
public class ZRpcStart implements Start {
    private static Logger logger = LoggerFactory.getLogger(ZRpcStart.class);
    private Integer workNUm = Runtime.getRuntime().availableProcessors() * 2;

    private static RpcServer RPC_SERVER;

    @Override
    public void start() {
        logger.info("gain start params");
        ZRpcStartParam param = getParams();
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
    public ZRpcStartParam getParams() {
        ZRpcStartParam param = new ZRpcStartParam();
        param.setPort(8080);
        return param;
    }

    public static void main(String[] args) {
        ZRpcStart zRpcStart = new ZRpcStart();
        zRpcStart.start();
    }

}
