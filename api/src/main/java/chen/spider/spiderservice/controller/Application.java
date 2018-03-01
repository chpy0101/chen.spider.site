package chen.spider.spiderservice.controller;

import chen.spider.pojo.yybBuyStock;
import chen.spider.service.yybBuyStockService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;

/**
 * Created by chpy on 18/1/22.
 */

@SpringBootApplication
@EnableJpaRepositories("chen.site.dao.mysql.idao")
@EntityScan("chen.spider.pojo")
public class Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);

		yybBuyStockService service = new yybBuyStockService();
		yybBuyStock entity = new yybBuyStock();
		entity.setBuyCode("00001");
		entity.setBuyCount(1000);
		entity.setBuyDate(new Date());
		entity.setBuyName("test");
		entity.setRecommedScore(0.5);
		entity.setStockName("testStock");
		entity.setStockCode("s00001");
		yybBuyStock result = service.save(entity);
	}
}
