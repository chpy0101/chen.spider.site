package chen.spider.service;

import chen.site.dao.idao.yybBuyStockRepository;
import chen.spider.pojo.yybBuyStock;
import org.springframework.beans.factory.annotation.Autowired;

public class yybBuyStockService {

	@Autowired
	yybBuyStockRepository yybBuyStockRepository;

	public yybBuyStock save(yybBuyStock entity) {
		return yybBuyStockRepository.save(entity);
	}
}
