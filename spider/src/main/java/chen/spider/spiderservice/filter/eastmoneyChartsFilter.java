package chen.spider.spiderservice.filter;

import chen.spider.spiderservice.entity.eastmoney.yybIncreaseEntity;
import chen.spider.spiderservice.entity.eastmoney.yybStockInfo;
import chen.spider.spiderservice.entity.eastmoney.yybStockSellInfo;
import chen.spider.spiderservice.util.CollectorsUtil;
import chen.spider.common.DateUtil;

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
			boolean result = false;
			//去除三个月买入次数少于10次的营业部
			if (t.getOneDayBuyTimes() < 30)
				return result;
			//过滤购买的股票信息
			List<String> removeCodes = new ArrayList<>();//需要移除的股票代码
			//获取营业部涨幅收益最大化的天数
			int rateMaxDays = t.getMaxRateDay();
			List<yybStockInfo> stocks = t.getBuyStock().stream().filter(s -> {
				return !s.getStockCode().startsWith("3") && DateUtil.addDay(s.getBuyTime(), rateMaxDays).after(DateUtil.nowDate());
			}).collect(Collectors.toList());//移除新股
			Map<String, yybStockSellInfo> mapInfo = stocks.stream().collect(Collectors.groupingBy(yybStockInfo::getStockCode, CollectorsUtil.summingToObject((a, b) -> {
				if (b == null)
					b = new HashMap<>();
				if (!b.containsKey("buy"))
					b.put("buy", "0");
				b.replace("buy", b.get("buy") + "-" + Double.toString(a.getBuyNum()));
				if (!b.containsKey("shell"))
					b.put("shell", "0");
				b.replace("shell", b.get("shell") + "-" + Double.toString(a.getShellNum()));
				if (!b.containsKey("price"))
					b.put("price", Double.toString(a.getBuyPrice()));
			}, a -> {
				yybStockSellInfo shell = new yybStockSellInfo();
				String[] buyAtr = a.get("buy").split("-");
				String[] shellAtr = a.get("shell").split("-");
				shell.setPrice(Double.parseDouble(a.get("price")));
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
				//股数少于100w股的剔除
				if (stock.getBuy() / stock.getPrice() < 100 * 10000) {
					removeCodes.add(item.getKey());
				}
			}
			//界限日期
			Date lastWorkDay = DateUtil.getDayFormat(DateUtil.addDay(DateUtil.nowDate(), -3));
			//移除列表中的股票和界限日期前 的股票
			List<yybStockInfo> finalStocks = stocks.stream()
					.filter(s -> removeCodes.indexOf(s.getStockCode()) < 0 && s.getBuyTime().getTime() < lastWorkDay.getTime())
					.collect(Collectors.toList());
			if (finalStocks == null || finalStocks.size() <= 0)
				result = false;
			else {
				t.setBuyStock(finalStocks);
				result = true;
			}
			return result;
		}).collect(Collectors.toList());
	}
}
