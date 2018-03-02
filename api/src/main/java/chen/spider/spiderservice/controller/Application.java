package chen.spider.spiderservice.controller;

import chen.spider.service.yybBuyStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by chpy on 18/1/22.
 */

@SpringBootApplication
@EnableJpaRepositories("chen.site.dao.mysql.idao")
@ComponentScan("chen")
@EntityScan("chen.spider.pojo")
public class Application {

	@Autowired
	public static yybBuyStockService yybBuyStockService;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
}
