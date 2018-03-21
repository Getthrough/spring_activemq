package mq;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * author: getthrough
 * date: 2018/3/21
 * description:
 */
public class MyApplicationContext implements ApplicationContextAware {

    private static ApplicationContext context;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MyApplicationContext.setContext(applicationContext);
//        MyApplicationContext.context = applicationContext;
    }

    public static <T> T getBean(String beanName) {
        if (context == null)
            throw new RuntimeException("applicationContext is null!");
        return (T) context.getBean(beanName);
    }

    public static void setContext(ApplicationContext context) {
        MyApplicationContext.context = context;
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
