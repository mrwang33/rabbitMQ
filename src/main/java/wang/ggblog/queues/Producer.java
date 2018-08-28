package wang.ggblog.queues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * @author wang1
 * @date 2018/8/27
 *
 * 交换器的规则有：
    direct （直连）
    topic （主题）
    headers （标题）
    fanout （分发）也有翻译为扇出的。
 */
public class Producer {

    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] argv) throws java.io.IOException, Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
//      分发消息
        for(int i = 0 ; i < 5; i++){
            String message = "Hello World! " + i;
            channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
        channel.close();
        connection.close();
    }
}
