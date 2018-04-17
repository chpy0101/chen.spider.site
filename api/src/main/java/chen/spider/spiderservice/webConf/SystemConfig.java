package chen.spider.spiderservice.webConf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by chpy on 18/4/9.
 */
@Configuration
@Import({myBootConfig.class, AMQPConfig.class})
public class SystemConfig {

}
