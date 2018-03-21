import com.alibaba.fastjson.JSON;
import mq.bean.MessageContent;
import mq.bean.User;
import mq.mq_enum.MQMsgMethod;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * author: getthrough
 * date: 2018/3/12
 * description:
 */

public class ProducerTest {

    @Test
    public void testSendMq() throws JMSException {
        ActiveMQConnectionFactory mqConnectionFactory = new ActiveMQConnectionFactory();
        mqConnectionFactory.setBrokerURL("tcp://127.0.0.1:61616");
        // 创建连接
        Connection connection = mqConnectionFactory.createConnection();
        // 开启连接
        connection.start();
        // arg1:是否开启事务; arg2:确认模式--自动确认
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 通过session 创建 队列
        Queue queue = session.createQueue("com.getthrough.spring_activemq.queue");
//        JmsTemplate jmsTemplate = new JmsTemplate(mqConnectionFactory);
//        jmsTemplate.send(queue, new MessageCreator() {
//            public Message createMessage(Session session) throws JMSException {
////                return session.createTextMessage("It's the message from test producer !");
//            }
//        });
        // 通过 session 创建消费者
        MessageProducer producer = session.createProducer(queue);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);// 默认即持久化
        producer.send(session.createTextMessage(JSON.toJSONString(new MessageContent(MQMsgMethod.USER_ADD.getMethodName(),
                JSON.toJSONString(new User("lina",18,"zhejiang","123@gmail.com"))))));
        // 关闭连接(会级联关闭session),释放资源
        connection.close();
    }

}
