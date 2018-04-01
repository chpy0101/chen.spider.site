package chen.spider.service;

import chen.site.dao.mysql.idao.yybBuyStockRepository;
import chen.spider.common.DateUtil;
import chen.spider.common.ListUtil;
import chen.spider.pojo.yybBuyStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class yybBuyStockService {

    @Autowired
    yybBuyStockRepository yybBuyStockRepository;

    public yybBuyStock save(yybBuyStock entity) {
        return yybBuyStockRepository.save(entity);
    }

    public List<yybBuyStock> save(List<yybBuyStock> entitys) {
        return ListUtil.toList(yybBuyStockRepository.save(entitys));
    }

    public List<yybBuyStock> getRecommendStockByDay(Date time) {
        return yybBuyStockRepository.findStockBuyTime(DateUtil.formatDate(time, DateUtil.YYYYMMDD));
    }



}
