package mq.listener;

import com.alibaba.fastjson.JSONObject;
import mq.MyApplicationContext;
import mq.UserService;
import mq.bean.MessageContent;
import mq.bean.User;
import mq.mq_enum.MQMsgMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * author: getthrough
 * date: 2018/3/8
 * description:
 */
public class QueueMessageListener extends ParentMessageListener {
    private Logger logger = LoggerFactory.getLogger(QueueMessageListener.class);

    /**
     * 消息前处理
     */
    public void beforeHandling() {
        // doSomething ...
        logger.info("before hanlding queue msg ...");
    }

    /**
     * 消息后处理
     */
    public void afterHandling() {
        // doSomething ...
        logger.info("after hanlding queue msg ...");
    }

    /**
     * 这里假设这个 listener 是监听 user 队列
     * @param message
     */
    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            logger.info("########### consumer1 has receive the message :" + textMessage.getText() + " ############");
            // 将接受到的消息转换为java对象
            MessageContent messageContent = (MessageContent)JSONObject.parseObject(textMessage.getText(), MessageContent.class);
            // 获取mq消息内容,并转化为java bean
            User user = (User) JSONObject.parseObject(messageContent.getContent(), User.class);
            // 获取mq方法
            String mqMethod = messageContent.getMqMsgMethod();
            // 根据mq方法获取处理该消息对应的服务类的名称
            MQMsgMethod mqMsgMethod = MQMsgMethod.getMQMsgMethodByMethodName(mqMethod);
            String serviceName = mqMsgMethod.getServiceName();
            // 获得服务类
            UserService userService = (UserService) MyApplicationContext.getBean(serviceName);
            // 调用服务类方法
            switch (mqMsgMethod) {
                case USER_ADD :
                    userService.addUser(user);
            break;
            case USER_DELETE:
                userService.deleteUser(user);
                break;
            default:
                break;
        }

    } catch (JMSException e) {
        logger.info("########## failed to get message text! ###########");
        e.printStackTrace();
        }
    }
}
