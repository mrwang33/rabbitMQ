package wang.ggblog.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author wang1
 * @date 2018/8/28
 */
public class LogSend {
    public static final String EXCHANGE_NAME = "direct_log";
    public static final String[] routings = new String[]{"debug","info","warn","error"};

    public static void main(String[] args) throws Exception {
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        // 定义交换器 不再直接往队列里发送消息 交换器类型为分发
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        // 分发消息
        for(String routingKey : routings){
            String message = "Hello World! " + routingKey;
            // 分发消息 因为交换器类型为fanout(广播到所有队列) 所以忽视routingkey配置
            channel.basicPublish(EXCHANGE_NAME,routingKey,null,message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
        channel.close();
        connection.close();
    }

}
