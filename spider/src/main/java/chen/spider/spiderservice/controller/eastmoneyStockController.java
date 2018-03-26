package chen.spider.spiderservice.controller;

import chen.spider.spiderservice.entity.eastmoney.stockPriceInfo;

import java.util.List;

/**
 * Created by chpy on 18/3/26.
 */
public class eastmoneyStockController extends abstractController<stockPriceInfo> {

    private List<String> codes;

    private int days;

    public eastmoneyStockController(boolean enableLoop) {
        super(enableLoop);
    }

    public eastmoneyStockController(boolean enableLoop, List<String> codes, int days) {
        super(enableLoop);
        this.codes = codes;
        this.days = days;
    }

    @Override
    public stockPriceInfo getData() {
        return null;
    }
}
