package chen.spider.spiderservice.webConf;

import chen.spider.common.LogUtil;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chpy on 18/4/8.
 */
@Configuration
public class AMQPConfig {
    private static final String HOST = "localhost";
    public static final String QUEUE_NAME = "test";
    public static final String YYBINFO_QUEUE_NAME = "yybInfo";

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(HOST);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true); //必须要设置
        return connectionFactory;
    }

    //@Bean
    public Connection getConnection(ConnectionFactory factory) {
        if (factory != null) {
                return factory.createConnection();
        }
        LogUtil.error("rabbit factory is null host:" + HOST);
        throw new RuntimeException("rabbit factory is null");
    }
}
