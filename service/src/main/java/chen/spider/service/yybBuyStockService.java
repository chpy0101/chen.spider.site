package chen.spider.service;

import chen.site.dao.mysql.idao.yybBuyStockRepository;
import chen.spider.pojo.yybBuyStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class yybBuyStockService {

	@Autowired
	yybBuyStockRepository yybBuyStockRepository;

	public yybBuyStock save(yybBuyStock entity) {
		return yybBuyStockRepository.save(entity);
	}
}
