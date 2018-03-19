package chen.spider.spiderservice.controller.util;

import chen.spider.common.thread.ThreadManager;
import chen.spider.spiderservice.controller.webConf.ThreadConfigSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by chpy on 18/3/19.
 */

/**
 * springapplication启动时执行一次
 */
@Component
@Order(value = 1)
public class ApplicationStartRunner implements ApplicationRunner {

    @Autowired
    ThreadConfigSetting threadConfigSetting;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        ThreadManager.init(threadConfigSetting.getCorepool(), threadConfigSetting.getMaxpool(), threadConfigSetting.getQueuesize(), threadConfigSetting.getAlives());
    }
}
