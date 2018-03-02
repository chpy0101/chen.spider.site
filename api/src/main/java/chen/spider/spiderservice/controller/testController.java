package chen.spider.spiderservice.controller;

import chen.spider.pojo.yybBuyStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by chpy on 18/3/1.
 */
@RestController
@RequestMapping("/test")
public class testController {

    @Autowired
    chen.spider.service.yybBuyStockService yybBuyStockService;

    @RequestMapping("/")
    public String test() {
        yybBuyStock entity = new yybBuyStock();
        entity.setBuyCode("00001");
        entity.setBuyCount(1000);
        entity.setBuyDate(new Date());
        entity.setBuyName("test");
        entity.setRecommedScore(0.5);
        entity.setStockName("testStock");
        entity.setStockCode("s00001");
        yybBuyStock result = yybBuyStockService.save(entity);
        return "OK";
    }
}
