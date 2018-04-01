package chen.spider.spiderservice.controller;

import chen.spider.common.DateUtil;
import chen.spider.common.LogUtil;
import chen.spider.common.thread.ThreadHandler;
import chen.spider.common.thread.ThreadManager;
import chen.spider.spiderservice.entity.eastmoney.stockPriceInfo;
import chen.spider.spiderservice.util.doubleUtil;
import chen.spider.spiderservice.util.httpHelper;
import com.alibaba.fastjson.JSONObject;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by chpy on 18/3/26.
 */
public class eastmoneyStockController extends abstractController<List<stockPriceInfo>> {

    //获取股票每日价格
    private final String STOCKHISTORYPRICEURL = "http://pdfm.eastmoney.com/EM_UBG_PDTI_Fast/api/js?rtntype=5&id={0}&type=k&num={1}&style=top";

    private List<String> codes;

    private int days;

    private eastmoneyStockController(boolean enableLoop) {
        super(enableLoop);
    }

    public eastmoneyStockController(boolean enableLoop, List<String> codes, int days) {
        super(enableLoop);
        this.codes = codes;
        this.days = days;
    }

    @Override
    public List<stockPriceInfo> getData() {
        if (codes == null) {
            throw new RuntimeException("没有设置需要搜集的股票");
        }
        List<stockPriceInfo> stockPriceInfoList = new ArrayList<>();
        ThreadHandler<List<stockPriceInfo>> threadHandler = new ThreadHandler<>();
        //添加多线程任务
        for (String item : codes) {
            //委托添加任务
            threadHandler.addTask(() -> {
                List<stockPriceInfo> result = new ArrayList<>();
                String url = MessageFormat.format(STOCKHISTORYPRICEURL, item, days);
                String response = httpHelper.get(url, null);
                LogUtil.info("[Request] url:" + url + "\rresponse:" + response);
                if (response.isEmpty()) {
                    response = httpHelper.get(url, null);
                }
                if (!response.isEmpty()) {
                    response = response.substring(1, response.length() - 1);
                    JSONObject object = JSONObject.parseObject(response);
                    String[] data = object.getJSONArray("data").toArray(new String[]{});
                    String codeName = object.getString("name");
                    for (String stock : data) {
                        String[] strAry = stock.split(",");
                        stockPriceInfo info = new stockPriceInfo();
                        info.setDate(DateUtil.convertStringToDate(strAry[0], DateUtil.YYYYMMDD));
                        info.setStartPrice(doubleUtil.parseDouble(strAry[1]));
                        info.setEndPrice(doubleUtil.parseDouble(strAry[2]));
                        info.setStockCode(item);
                        info.setStockName(codeName);
                        result.add(info);
                    }
                }
                return result;
            });
        }
        //开启多线程
        ThreadManager.beginWork(threadHandler);
        List<Future<List<stockPriceInfo>>> threadHandlerResults = threadHandler.getResults();

        while (threadHandlerResults.size() > 0) {
            Iterator<Future<List<stockPriceInfo>>> item = threadHandlerResults.iterator();
            while (item.hasNext()) {
                Future<List<stockPriceInfo>> t = item.next();
                //获取一个结果就从list中移除该Future
                if (t.isDone()) {
                    try {
                        stockPriceInfoList.addAll(t.get());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    item.remove();
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return stockPriceInfoList;
    }
}
