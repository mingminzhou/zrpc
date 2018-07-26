// create by zmm (弄死熊猫)
# zrpc
傻瓜式，轻量级rpc框架（基于netty、protoStuff；client、server仅需通过注解@ZrpcClient、@ZrpcService便可使用）


# client用注解和jdk动态代理的方式实现,基于hystrix做breaker，对于channel的管理可分为两种：
1、channelPool client端启动后有固定数据的channel用于server端的数据传输； （还处于涉及阶段）
2、根据调用server端方法的数据数量决定（1:1） （已完成）


# server同样用注解形式实现，增加对业务数据消费层的分发管理，避免业务操作占用netty自身线程 （处于涉及阶段）

# 服务注册发现模块默认使用eureka，开放服务注册发现接口，供使用者自己实现注册和发现 （处于设计阶段）

# 请求的路由用Netflix Zuul实现 （处于涉及阶段）

# 请求负载均衡用Netflix Ribbon实现 （处于设计阶段）