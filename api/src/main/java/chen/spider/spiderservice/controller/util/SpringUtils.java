package chen.spider.spiderservice.controller.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by chpy on 18/6/10.
 */
@Component
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext _applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        _applicationContext = applicationContext;
    }

    public static Object getBean(String beanName){
        return _applicationContext.getBean(beanName);
    }
}
