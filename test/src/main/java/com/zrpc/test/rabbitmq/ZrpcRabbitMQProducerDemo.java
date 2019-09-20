package com.zrpc.test.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/12/22
 */
public class ZrpcRabbitMQProducerDemo {
    private static String url = "127.0.0.1";
    private static Integer port = 5672;
    private static String username = "guest";
    private static String password = "guest";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(url);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        //声明一个队列    queueDeclare第一个参数表示队列名称、第二个参数为是否持久化（true表示是，队列将在服务器重启时生存）、
        // 第三个参数为是否是独占队列（创建者可以使用的私有队列，断开后自动删除）、第四个参数为当所有消费者客户端连接断开时是否自动删除队列、第五个参数为队列的其他参数
        AMQP.Queue.DeclareOk declareOk = channel.queueDeclare("test", false, false, false, null);
        String message = "hello rabbitmq";//消息内容
        //发送消息到队列  第一个参数为交换机名称、第二个参数为队列映射的路由key、第三个参数为消息的其他属性、第四个参数为发送信息的主体
        channel.basicPublish("", "test", null, message.getBytes("UTF-8"));
        System.out.println("producer send " + message);

        channel.close();
        connection.close();

    }
}
