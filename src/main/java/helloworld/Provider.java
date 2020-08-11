package helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Provider {

    @Test
    public void testSendMessage() throws IOException, TimeoutException {
/*        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("106.14.135.230");
        //设置端口号
        connectionFactory.setPort(5672);
        //设置虚拟主机
        connectionFactory.setVirtualHost("/ems");
        //设置访问虚拟主机的用户名和密码
        connectionFactory.setUsername("wdnmd");
        connectionFactory.setPassword("lwj518");
        //获取连接对象
        Connection connection = connectionFactory.newConnection(); */

        //通过工具类获取链接对象
        Connection connection = RabbitMQUtils.getConnection();
        //获取连接中的通道
        Channel channel = connection.createChannel();
        //通道绑定对应的消息队列
        channel.queueDeclare("hello",true,false,false,null);
        //发布消息
        channel.basicPublish("","hello", MessageProperties.PERSISTENT_TEXT_PLAIN,"hello,rabbitmq,wdnmd".getBytes());

//        channel.close();
//        connection.close();
        RabbitMQUtils.closeConnectionAndChannel(channel,connection);

    }
}
