package mq.bean;

/**
 * author: getthrough
 * date: 2018/3/21
 * description:
 */
public class MessageContent {
    private String mqName;// 该消息的名称,用来具体做什么处理
    private String content;// 消息具体内容

    public MessageContent(){}

    public MessageContent(String mqName, String content) {
        this.mqName = mqName;
        this.content = content;
    }

    public String getMqMsgMethod() {
        return mqName;
    }

    public void setMqMsgMethod(String mqName) {
        this.mqName = mqName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
