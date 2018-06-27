package com.zrpc.test.JdkDynamicProxy;

import com.zrpc.annotation.client.ZrpcClient;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/6/27
 */
public class KeyAndValue {

    @ZrpcClient
    private JdkDynamicInterface jdkDynamicInterface;

    public String test(String key,String value){
        return jdkDynamicInterface.test(key,value);
    }

}
