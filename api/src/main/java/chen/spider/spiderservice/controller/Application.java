package chen.spider.spiderservice.controller;

import chen.spider.service.yybBuyStockService;
import chen.spider.spiderservice.controller.webConf.ThreadConfigSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by chpy on 18/1/22.
 */

@SpringBootApplication
@EnableJpaRepositories("chen.site.dao.mysql.idao")
@ComponentScan("chen")
@EntityScan("chen.spider.pojo")
@EnableScheduling
public class Application {

	@Autowired
	public static yybBuyStockService yybBuyStockService;


	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);

	}
}
