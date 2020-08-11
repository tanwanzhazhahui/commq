package direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        //获取连接通道对象
        Channel channel = connection.createChannel();
        //通过通道声明交换机
        channel.exchangeDeclare("logs_direct","direct");
         //发送消息
        String routingKey="error";
        channel.basicPublish("logs_direct",routingKey,null,("这是路由模型发布的基于route key:["+routingKey+"]的消息").getBytes());

        //关闭资源
        RabbitMQUtils.closeConnectionAndChannel(channel,connection);
    }
}
