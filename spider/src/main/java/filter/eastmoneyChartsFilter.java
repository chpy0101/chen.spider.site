package filter;

import entity.eastmoney.yybIncreaseEntity;
import entity.eastmoney.yybStockInfo;
import entity.eastmoney.yybStockSellInfo;
import util.CollectorsUtil;
import util.DateUtil;

import java.util.*;
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
				return result;
			}
			//过滤购买的股票信息
			List<String> removeCodes = new ArrayList<>();//需要移除的
			List<yybStockInfo> stocks = t.getBuyStock().stream().filter(s -> !s.getStockCode().startsWith("3")).collect(Collectors.toList());
			Map<String, yybStockSellInfo> mapInfo = stocks.stream().collect(Collectors.groupingBy(yybStockInfo::getStockCode, CollectorsUtil.summingToObject((a, b) -> {
				if (b == null)
					b = new HashMap<>();
				if (!b.containsKey("buy"))
					b.put("buy", "0");
				b.replace("buy", b.get("buy") + "-" + Double.toString(a.getBuyNum()));
				if (!b.containsKey("shell"))
					b.put("shell", "0");
				b.replace("shell", b.get("shell") + "-" + Double.toString(a.getShellNum()));
			}, a -> {
				yybStockSellInfo shell = new yybStockSellInfo();
				String[] buyAtr = a.get("buy").split("-");
				String[] shellAtr = a.get("shell").split("-");
				for (int i = 0; i < buyAtr.length; i++) {
					if (!buyAtr[i].isEmpty())
						shell.addBuy(Double.parseDouble(buyAtr[i]));
					if (!shellAtr[i].isEmpty())
						shell.addSell(Double.parseDouble(shellAtr[i]));
				}
				return shell;
			})));
			//股票卖出额达到买入额一半
			for (Map.Entry<String, yybStockSellInfo> item : mapInfo.entrySet()) {
				yybStockSellInfo stock = item.getValue();
				if (stock.getSell() * 2 > stock.getBuy())
					removeCodes.add(item.getKey());
			}
			//获取上一个工作日
			Date lastWorkDay =
			//移除列表中的股票
			List<yybStockInfo> finalStocks = stocks.stream()
					.filter(s -> removeCodes.indexOf(s.getStockCode()) < 0 && s.getBuyTime().getTime() <)
					.collect(Collectors.toList());
			if (finalStocks == null || finalStocks.size() <= 0)
				result = false;
			else
				t.setBuyStock(finalStocks);
			return result;
		}).collect(Collectors.toList());
	}
}
