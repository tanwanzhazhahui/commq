package helloworld;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {
/*        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("106.14.135.230");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("wdnmd");
        connectionFactory.setPassword("lwj518");

        //创建连接对象
        Connection connection = connectionFactory.newConnection(); */

        //通过工具类获取连接
        Connection connection = RabbitMQUtils.getConnection();

        //创建通道
        Channel channel = connection.createChannel();

        //通道绑定对象
        channel.queueDeclare("hello",true,false,false,null);

        //消费消息
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("new String(Body)="+new String(body));
            }
        });

//        channel.close();
//        connection.close();
    }

}
