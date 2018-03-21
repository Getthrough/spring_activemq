package mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;

/**
 * author: getthrough
 * date: 2018/3/8
 * description: 连接工厂的包装类
 */
public class ActiveMqConnectionFactoryDecoration implements ConnectionFactory {
    /**apache 提供的连接池*/
//    private PooledConnectionFactory pooledConnectionFactory;
    private String brokerURL;
    private String userName;
    private String password;
    private String maxConntections;

    private ActiveMQConnectionFactory activeMQConnectionFactory;

    public ActiveMqConnectionFactoryDecoration() {
    }

    public void run() throws JMSException {
        activeMQConnectionFactory.setBrokerURL(brokerURL);
        activeMQConnectionFactory.setUserName(userName);
        activeMQConnectionFactory.setPassword(password);
        activeMQConnectionFactory.createConnection();
//        pooledConnectionFactory = new PooledConnectionFactory();
//        pooledConnectionFactory.setMaxConnections(Integer.parseInt(maxConntections));
//        pooledConnectionFactory.setConnectionFactory(activeMQConnectionFactory);
//        pooledConnectionFactory.createConnection(userName, password);
    }

    public void stop() {
//        if (null != pooledConnectionFactory) {
//            pooledConnectionFactory.stop();
//        }
    }

    public Connection createConnection() throws JMSException {
//        return pooledConnectionFactory.createConnection();
        return activeMQConnectionFactory.createConnection();
    }

    public Connection createConnection(String userName, String password) throws JMSException {
//        return pooledConnectionFactory.createConnection(userName, password);
        return activeMQConnectionFactory.createConnection(userName, password);
    }

    public void setActiveMQConnectionFactory(ActiveMQConnectionFactory activeMQConnectionFactory) {
        this.activeMQConnectionFactory = activeMQConnectionFactory;
    }

    public String getBrokerURL() {
        return brokerURL;
    }

    public void setBrokerURL(String brokerURL) {
        this.brokerURL = brokerURL;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMaxConntections() {
        return maxConntections;
    }

    public void setMaxConntections(String maxConntections) {
        this.maxConntections = maxConntections;
    }
}
