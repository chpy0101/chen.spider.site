package entity.eastmoney;

public class yybStockSellInfo {
    public String code;
    public double sell;
    public double buy;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getSell() {
        return sell;
    }

    public void setSell(double sell) {
        this.sell = sell;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public void addSell(double value) {
        this.sell += value;
    }

    public void addBuy(double value) {
        this.buy += value;
    }

}
