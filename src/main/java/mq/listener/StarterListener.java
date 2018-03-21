package mq.listener;

import com.alibaba.fastjson.JSON;
import mq.ActiveMqConnectionFactoryDecoration;
import mq.ActiveMqSender;
import mq.bean.MessageContent;
import mq.bean.User;
import mq.mq_enum.MQMsgMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.jms.JMSException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * author: getthrough
 * date: 2018/3/12
 * description:
 */
public class StarterListener implements ServletContextListener {

    private Logger logger = LoggerFactory.getLogger(StarterListener.class);

    public void contextInitialized(ServletContextEvent sce) {
        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());
        ActiveMqConnectionFactoryDecoration connectionFactory =
                (ActiveMqConnectionFactoryDecoration) context.getBean("activeMqConnectionFactoryDecoration");
        try {
            // 连接 mq server
            connectionFactory.run();
            logger.info("###############");
//            ActiveMqSender.sendMqMessage("hey, now is" + new Date() + "!");
            // 改写成
            ActiveMqSender.sendMqMessage(JSON.toJSONString(new MessageContent(MQMsgMethod.USER_ADD.getMethodName(),
                    JSON.toJSONString(new User("lina",18,"zhejiang","123@gmail.com")))));
        } catch (JMSException e) {
            logger.info("####### failed to connect mq server! ########");
            e.printStackTrace();
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        // doSomething
    }
}
