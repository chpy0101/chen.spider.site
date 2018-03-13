package chen.spider.spiderservice.controller;

import chen.spider.pojo.yybBuyStock;
import chen.spider.spiderservice.controller.webConf.ThreadConfigSetting;
import chen.spider.spiderservice.entity.eastmoney.yybIncreaseEntity;
import chen.spider.spiderservice.filter.eastmoneyChartsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import chen.spider.service.yybBuyStockService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by chpy on 18/3/1.
 */
@RestController
@RequestMapping("/test")
public class testController {

	@Autowired
	yybBuyStockService yybBuyStockService;


	@Autowired
	ThreadConfigSetting threadConfigSetting;

	@RequestMapping("/")
	public String test() {

		eastmoneyChartsController spiderController = new eastmoneyChartsController(false);
		spiderController.addFilter(new eastmoneyChartsFilter());
		List<yybIncreaseEntity> yybEntity = spiderController.getData();

		List<yybBuyStock> stockInfos = yybEntity.stream().flatMap(t -> {
			Stream<yybBuyStock> stock = t.getBuyStock().stream().map(s -> {
				yybBuyStock temp = new yybBuyStock();
				temp.setStockCode(s.getStockCode());
				temp.setStockName(s.getStockName());
				temp.setRecommedScore(0d);
				temp.setBuyName("");
				temp.setBuyDate(s.getBuyTime());
				temp.setBuyCount(Double.valueOf(s.getBuyNum()).intValue());
				temp.setBuyCode(s.getYybCode());
				return temp;
			});
			return stock;
		}).distinct().collect(Collectors.toList());
		if (stockInfos == null)
			return "未获取到有效的股票信息";
		List<yybBuyStock> result = yybBuyStockService.save(stockInfos);
		return "OK";
	}
}
