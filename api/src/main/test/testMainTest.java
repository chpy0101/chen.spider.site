

import chen.spider.pojo.yybBuyStock;
import chen.spider.service.yybBuyStockService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Date;

/**
 * testMain Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>���� 1, 2018</pre>
 */
@SpringBootApplication
@EnableJpaRepositories("chen.site.dao.mysql.idao")
@EntityScan
public class testMainTest {
	static yybBuyStock entity;

	@Before
	public void before() throws Exception {
		entity = new yybBuyStock();
		entity.setBuyCode("00001");
		entity.setBuyCount(1000);
		entity.setBuyDate(new Date());
		entity.setBuyName("test");
		entity.setRecommedScore(0.5);
		entity.setStockName("testStock");
		entity.setStockCode("s00001");
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: save()
	 */
	@Test
	public void testSave() throws Exception {
//TODO: Test goes here...
		yybBuyStockService service = new yybBuyStockService();
		service.save(entity);
	}


} 
