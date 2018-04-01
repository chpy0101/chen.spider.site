package chen.spider.spiderservice.controller;

import chen.spider.common.DateUtil;
import chen.spider.common.type.DifferTimeType;
import chen.spider.pojo.yybBuyStock;
import chen.spider.service.yybBuyStockService;
import chen.spider.spiderservice.entity.eastmoney.stockPriceInfo;
import chen.spider.spiderservice.entity.eastmoney.yybIncreaseEntity;
import chen.spider.spiderservice.filter.eastmoneyChartsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
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
    private HttpServletRequest request;

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
                temp.setMaxHoldDay(t.getMaxRateDay());
                return temp;
            });
            return stock;
        }).distinct().collect(Collectors.toList());
        if (stockInfos == null)
            return "未获取到有效的股票信息";
        List<yybBuyStock> result = yybBuyStockService.save(stockInfos);
        return "OK";
    }

    @RequestMapping("/trend")
    public String getStockTrend(@RequestParam(name = "date") Date date) {
        //获取制定日期的推荐股票
        List<yybBuyStock> stocks = yybBuyStockService.getRecommendStockByDay(date);
        List<String> codes = stocks.stream().map(t -> t.getStockCode()).collect(Collectors.toList());
        //获取相差天数
        int days = DateUtil.getDifferTimes(new Date(), date, DifferTimeType.DAY_MILLISECOND);
        eastmoneyStockController controller = new eastmoneyStockController(false, codes, days + 1);
        List<stockPriceInfo> data = controller.getData();
        Map<String, List<stockPriceInfo>> result = data.stream().collect(Collectors.groupingBy(stockPriceInfo::getStockCode));
        result.forEach((key,value)->value.sort(new Comparator<stockPriceInfo>() {
            //TODO...
            @Override
            public int compare(stockPriceInfo o1, stockPriceInfo o2) {
                return 0;
            }
        }));
        return "";
    }
}
