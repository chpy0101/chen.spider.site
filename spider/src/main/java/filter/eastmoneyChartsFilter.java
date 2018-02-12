package filter;

import entity.eastmoney.yybIncreaseEntity;
import entity.eastmoney.yybStockInfo;
import entity.eastmoney.yybStockSellInfo;
import util.CollectorsUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 营业部数据筛选器
 */
public class eastmoneyChartsFilter extends baseFilter<List<yybIncreaseEntity>> {

	/**
	 * 最近没有股票买卖的剔除
	 *
	 * @param data
	 * @return
	 */
	@Override
	public List<yybIncreaseEntity> filter(List<yybIncreaseEntity> data) {
		return data.stream().filter(t -> {
			boolean result = true;
			//第一天大涨。三天后转跌的剔除
			if (t.getOneDayIncreaseRate() >= 5 && t.getThreeDayIncreaseRate() <= 0) {
				result = false;
			}
			//过滤购买的股票信息
			else {
				List<yybStockInfo> stocks = t.getBuyStock();
				Map<String, yybStockSellInfo> mapInfo = stocks.stream()
						.collect(Collectors.groupingBy(yybStockInfo::getStockCode, CollectorsUtil.summingToObject((a, b) -> {
							if (b == null)
								b = new yybStockSellInfo();
							b.addBuy(a.getBuyNum());
							b.addSell(Math.abs(a.getShellNum()));
						})));
				//股票卖出额达到买入额一半

			}
			return result;
		}).collect(Collectors.toList());
	}
}
