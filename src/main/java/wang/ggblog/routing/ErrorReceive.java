package wang.ggblog.routing;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author wang1
 * @date 2018/8/28
 */
public class ErrorReceive {
    public static final String routingKey = "error";
    private static final String EXCHANGE_NAME = "direct_log";

    public static void main(String[] args) throws Exception {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
//		设置RabbitMQ地址
        factory.setHost("localhost");
//		创建一个新的连接
        Connection connection = factory.newConnection();
//		创建一个频道
        Channel channel = connection.createChannel();
        // 定义交换器和交换器类型
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        // 获取临时队列
        String queueName = channel.queueDeclare().getQueue();
        // 将临时队列与exchange绑定 由于是fanout类型exchange所以忽略routingkey配置
        channel.queueBind(queueName, EXCHANGE_NAME, routingKey);
        System.out.println("C [*] Waiting for messages. To exit press CTRL+C");
        // DefaultConsumer类实现了Consumer接口，通过传入一个频道，告诉服务器我们需要那个频道的消息，如果频道中有消息，就会执行回调函数handleDelivery
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("C [x] Received '" + message + "'");
            }
        };
//		自动回复队列应答 -- RabbitMQ中的消息确认机制，后面章节会详细讲解
        channel.basicConsume(queueName, true, consumer);
    }
}
