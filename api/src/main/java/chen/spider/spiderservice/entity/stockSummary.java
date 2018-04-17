package chen.spider.spiderservice.entity;


import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by chpy on 18/4/12.
 */

public class stockSummary {
    private String stockCode;
    private String stockName;
    private Integer recommedDay;
    private String buyName;
    private double startPrice;
    private double endPrice;
    private Date buyTime;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public Integer getRecommedDay() {
        return recommedDay;
    }

    public void setRecommedDay(Integer recommedDay) {
        this.recommedDay = recommedDay;
    }

    public String getBuyName() {
        return buyName;
    }

    public void setBuyName(String buyName) {
        this.buyName = buyName;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public double getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(double endPrice) {
        this.endPrice = endPrice;
    }

    public double getIncreaRate() {
        double differPrice = endPrice - startPrice;
        return new BigDecimal(differPrice).divide(new BigDecimal(startPrice), 2).doubleValue();
    }

}
