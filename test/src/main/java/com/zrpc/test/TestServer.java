package com.zrpc.test;

import com.zrpc.server.start.ZRpcServerStart;
import lombok.extern.slf4j.Slf4j;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/6/25
 */
@Slf4j
public class TestServer {

    public static void main(String[] args) {
        ZRpcServerStart zRpcStart = new ZRpcServerStart();
        zRpcStart.start();
    }

}
