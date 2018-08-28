package wang.ggblog.publish;

import com.rabbitmq.client.*;

/**
 * @author wang1
 * @date 2018/8/28
 *  * 交换器的规则有：
    direct （直连）
    topic （主题）
    headers （标题）
    fanout （分发）也有翻译为扇出的。
 *
 */
public class EmitLog {
    // 交换器名称
    public static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception {
        // 创建连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        // 定义交换器 不再直接往队列里发送消息 交换器类型为分发
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
//      分发消息
        for(int i = 0 ; i < 5; i++){
            String message = "Hello World! " + i;
            // 分发消息 因为交换器类型为fanout(广播到所有队列) 所以忽视routingkey配置
            channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
        channel.close();
        connection.close();
    }
}
