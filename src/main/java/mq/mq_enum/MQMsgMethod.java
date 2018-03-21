package mq.mq_enum;

/**
 * author: getthrough
 * date: 2018/3/21
 * description:
 */
public enum MQMsgMethod {
    USER_ADD("user.add","userService"),// 方法的名称, 和具体处理该方法的服务类
    USER_DELETE("user.delete","userService"),
    ORDER_UPDATE("order.update","orderService");// 此处user 和 order 应属于不同的队列

    private String methodName;
    private String serviceName;

    // 该构造主要用于消息的消费
    MQMsgMethod(String methodName, String serviceName) {
        this.methodName = methodName;
        this.serviceName = serviceName;
    }
    // 该构造主要用于消息的发送
    MQMsgMethod(String methodName) {
        this.methodName = methodName;
        this.serviceName = null;
    }

    /**
     * 根据方法名称获取服务类
     * @param methodName
     * @return
     */
    public static MQMsgMethod getMQMsgMethodByMethodName(String methodName) {
        MQMsgMethod[] mqMsgMethods = MQMsgMethod.values();
        MQMsgMethod result = null;
        if (mqMsgMethods.length > 0) {
            for (MQMsgMethod mqMsgMethod : mqMsgMethods) {
                if (mqMsgMethod.getMethodName().equals(methodName)) {
                    result = mqMsgMethod;
                    break;
                }
            }
        }
        return result;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
