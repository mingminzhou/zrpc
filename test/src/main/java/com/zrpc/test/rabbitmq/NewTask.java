package com.zrpc.test.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * create by zmm 弄死熊猫
 * <p>
 * on 2018/12/22
 */
public class NewTask {
    private static final String TASK_QUEUE_NAME="task_queue";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection=factory.newConnection();
        Channel channel=connection.createChannel();
        channel.queueDeclare(TASK_QUEUE_NAME,true,false,false,null);
        //分发信息
        for (int i=0;i<1000000;i++){
            String message="Hello RabbitMQ"+i;
            channel.basicPublish("",TASK_QUEUE_NAME,
                    MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
            System.out.println("NewTask send '"+message+"'");
        }
        channel.close();
        connection.close();
    }
}
