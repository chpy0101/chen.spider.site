package chen.spider.spiderservice.entity.eastmoney;

import java.util.Date;

public class yybStockInfo {

    private double buyNum;
    private double shellNum;
    private double buyPrice;
    private double oneDayRate;
    private double threeDayRate;
    private double fiveDayRate;
    private String stockCode;
    private String yybCode;
    private Date buyTime;

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

    public String getYybCode() {
        return yybCode;
    }

    public void setYybCode(String yybCode) {
        this.yybCode = yybCode;
    }

    public double getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(double buyNum) {
        this.buyNum = buyNum;
    }

    public double getShellNum() {
        return shellNum;
    }

    public void setShellNum(double shellNum) {
        this.shellNum = shellNum;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getOneDayRate() {
        return oneDayRate;
    }

    public void setOneDayRate(double oneDayRate) {
        this.oneDayRate = oneDayRate;
    }

    public double getThreeDayRate() {
        return threeDayRate;
    }

    public void setThreeDayRate(double threeDayRate) {
        this.threeDayRate = threeDayRate;
    }

    public double getFiveDayRate() {
        return fiveDayRate;
    }

    public void setFiveDayRate(double fiveDayRate) {
        this.fiveDayRate = fiveDayRate;
    }
}
