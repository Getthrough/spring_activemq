package mq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * author: getthrough
 * date: 2018/3/12
 * description:
 */
public abstract class ParentMessageListener implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(ParentMessageListener.class);
    /**处理消息*/
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage)message;
        try {
            String msgContent = textMessage.getText();
            // 处理消息内容
            doWithMsg(msgContent);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对收到的文本消息进行具体处理
     * @param msgContent 消息内容
     */
    private void doWithMsg(String msgContent) {
        logger.info("#######received mqMsg:" + msgContent + "#######");
    }

    /**消息前处理*/
    public abstract void beforeHandling();
    /**消息后处理*/
    public abstract void afterHandling();

}
