package fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        //将通道声明指定的交换机
        channel.exchangeDeclare("logs","fanout");//fanout写死，广播类型

        //发送消息
        channel.basicPublish("logs","",null,"fanout type message".getBytes());

        //释放资源
        RabbitMQUtils.closeConnectionAndChannel(channel,connection);

    }
}
