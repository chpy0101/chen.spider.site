package chen.spider.service;

import chen.site.dao.mysql.idao.stockDetailRepository;
import chen.spider.pojo.stockDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by chpy on 18/3/21.
 */
@Service
public class stockDetailService {
    @Autowired
    stockDetailRepository stockDetailRepository;


    public Map<String, List<stockDetail>> getStockDetail(List<String> codes, int days) {

        return null;
    }
}
