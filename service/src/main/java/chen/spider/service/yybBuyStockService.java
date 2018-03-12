package chen.spider.service;

import chen.site.dao.mysql.idao.yybBuyStockRepository;
import chen.spider.common.ListUtil;
import chen.spider.pojo.yybBuyStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class yybBuyStockService {

    @Autowired
    yybBuyStockRepository yybBuyStockRepository;

    private final String stockHistoryPriceUrl = "http://pdfm2.eastmoney.com/EM_UBG_PDTI_Fast/api/js?id=0023692&TYPE=k&style=top&num=10";

    public yybBuyStock save(yybBuyStock entity) {
        return yybBuyStockRepository.save(entity);
    }

    public List<yybBuyStock> save(List<yybBuyStock> entitys) {
        return ListUtil.toList(yybBuyStockRepository.save(entitys));
    }


}
