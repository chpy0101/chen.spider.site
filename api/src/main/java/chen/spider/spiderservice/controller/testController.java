package chen.spider.spiderservice.controller;

import chen.spider.common.DateUtil;
import chen.spider.common.IntegerUtil;
import chen.spider.common.type.DifferTimeType;
import chen.spider.pojo.yybBuyStock;
import chen.spider.service.yybBuyStockService;
import chen.spider.spiderservice.contact.BaseRequest;
import chen.spider.spiderservice.contact.BaseResponse;
import chen.spider.spiderservice.entity.eastmoney.stockPriceInfo;
import chen.spider.spiderservice.entity.eastmoney.yybIncreaseEntity;
import chen.spider.spiderservice.entity.stockSummary;
import chen.spider.spiderservice.entity.testEntity;
import chen.spider.spiderservice.filter.eastmoneyChartsFilter;
import chen.spider.spiderservice.handler.MyRestTemplate;
import chen.spider.spiderservice.webConf.AMQPConfig;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by chpy on 18/3/1.
 */
@RestController
@RequestMapping("/test")
public class testController {

    @Autowired(required = false)
    Connection connection;

    @Autowired
    yybBuyStockService yybBuyStockService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MyRestTemplate restTemplate;

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
                temp.setRecommendDay(s.getBuyTime());
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
    @ResponseBody
    public List<stockSummary> getStockTrend(@RequestParam(name = "date") Date date) {
        List<stockSummary> summaries = new ArrayList<>();
        //获取制定日期的推荐股票
        List<yybBuyStock> stocks = yybBuyStockService.getRecommendStockByDay(date);
        if (stocks == null) {
            return summaries;
        }
        List<String> codes = stocks.stream().map(t -> t.getStockCode()).collect(Collectors.toList());
        //获取相差天数
        int days = DateUtil.getDifferTimes(new Date(), date, DifferTimeType.DAY_MILLISECOND);
        eastmoneyStockController controller = new eastmoneyStockController(false, codes, days + 1);
        List<stockPriceInfo> data = controller.getData();
        Map<String, List<stockPriceInfo>> result = data.stream().collect(Collectors.groupingBy(stockPriceInfo::getStockCode));
        //正序排序
        result.forEach((key, value) -> value.sort((v1, v2) -> {
            int differ = DateUtil.getDifferTimes(v1.getDate(), v2.getDate(), DifferTimeType.DAY_MILLISECOND);
            return IntegerUtil.positiveOrNegative(differ);
        }));

        //更具推荐日期计算股票涨幅
        for (yybBuyStock stockInfo : stocks) {
            if (result.containsKey(stockInfo.getStockCode())) {
                //推荐可以持有日期
                long limits = stockInfo.getMaxHoldDay();
                List<stockPriceInfo> stockDetail = result.get(stockInfo.getStockCode());
                if (stockDetail != null && stockDetail.size() > 0) {
                    limits = stockDetail.size() < limits ? stockDetail.size() : limits;
                    stockDetail = stockDetail.stream().limit(limits).collect(Collectors.toList());
                    stockSummary summary = new stockSummary();
                    summary.setBuyName(stockInfo.getBuyName());
                    summary.setBuyTime(stockInfo.getBuyDate());
                    summary.setRecommedDay(stockInfo.getMaxHoldDay());
                    summary.setStockCode(stockInfo.getStockCode());
                    summary.setStockName(stockInfo.getStockName());
                    summary.setStartPrice(stockDetail.get(0).getStartPrice());
                    stockPriceInfo listDetail = stockDetail.get(stockDetail.size() - 1);
                    summary.setEndPrice(listDetail.getEndPrice());

                    summaries.add(summary);
                }
            }
        }
        return summaries;
    }

    @RequestMapping("/rabbitmq")
    public String testRabbit() {
        try {
            Channel channel = connection.createChannel(false);
            AMQP.Queue.DeclareOk queueResult = channel.queueDeclare(AMQPConfig.QUEUE_NAME, false, false, false, null);
            for (String item : new java.lang.String[]{"test1", "test2", "test3", "test4"}) {
                channel.basicPublish("", AMQPConfig.QUEUE_NAME, null, item.getBytes());
                Thread.sleep(1000);
            }

            Thread thread = new Thread(() -> {
                Channel channel1 = connection.createChannel(false);
                try {
                    DefaultConsumer consumer = new DefaultConsumer(channel1) {
                        @Override
                        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                            String routeKey = envelope.getRoutingKey();
                            System.out.println("consumer-routeKey:" + routeKey);
                            String exchange = envelope.getExchange();
                            System.out.println("consumer-exchange:" + exchange);
                            String content = new String(body);
                            System.out.println("consumer-body:" + content);
                            //false 确认当前消息。true确认之前所有消息
                            channel1.basicAck(envelope.getDeliveryTag(), false);
                        }
                    };
                    channel1.basicConsume(AMQPConfig.QUEUE_NAME, false, "testConsumer", consumer);
                } catch (Exception ex) {
                }
            });
            thread.start();
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        return "OK";
    }

    @RequestMapping("/testRes")
    @ResponseBody
    public BaseResponse<String> testRes() {
        return BaseResponse.success().setData("success");
    }

    @RequestMapping(value = "/handler", method = RequestMethod.POST)
    @ResponseBody
    public BaseResponse<String> testHandler(@RequestBody BaseRequest<testEntity> request) throws JsonProcessingException {
        //org.springframework.web.client.RestTemplate
        restTemplate.postForEntity("https://www.baidu.com", request, String.class, "");
        return BaseResponse.success();
    }

}
