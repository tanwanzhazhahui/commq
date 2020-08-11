package utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQUtils {

    private static ConnectionFactory connectionFactory;

    static {
        connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("106.14.135.230");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("wdnmd");
        connectionFactory.setPassword("lwj518");
    }

    //定义提供链接对象的方法
    public static Connection getConnection(){
        try {
            return connectionFactory.newConnection();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //关闭通道和关闭连接工具方法
    public static void closeConnectionAndChannel(Channel channel,Connection connection){
        try {
            if(channel!=null)channel.close();;
            if(connection!=null)connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
