package chen.spider.service;

import chen.site.dao.mysql.idao.stockDetailRepository;
import chen.spider.common.thread.ThreadHandler;
import chen.spider.pojo.stockDetail;
import com.sun.deploy.net.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by chpy on 18/3/21.
 */
@Service
public class stockDetailService {
    @Autowired
    stockDetailRepository stockDetailRepository;
    //获取股票每日价格
    private final String stockHistoryPriceUrl = "http://pdfm2.eastmoney.com/EM_UBG_PDTI_Fast/api/js?id={0}&TYPE=k&style=top&num={1}";

    public Map<String, List<stockDetail>> getStockDetail(List<String> codes, int days) {
        ThreadHandler<Map<String, List<stockDetail>>> handler = new ThreadHandler<>();
        for (String code : codes) {
            handler.addTask(()->{
               String url = MessageFormat.format(stockHistoryPriceUrl,code,days);
                HttpUtils
            });
        }
        return null;
    }
}
