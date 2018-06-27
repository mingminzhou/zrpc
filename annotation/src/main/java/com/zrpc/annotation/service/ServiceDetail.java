package com.zrpc.annotation.service;

/**
 * service 对应的接口、方法名
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/6/27
 */
public class ServiceDetail {

    private String interfaceName;

    private String methodName;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
}
