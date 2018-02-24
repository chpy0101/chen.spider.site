package eastmoney;

import java.util.Date;

public class yybBuyStock {
	public String buyCode;
	public String buyName;
	public String stockCode;
	public String stockName;
	public Date buyDate;
	public Double recommedScore;
	public Integer buyCount;

	public String getBuyCode() {
		return buyCode;
	}

	public void setBuyCode(String buyCode) {
		this.buyCode = buyCode;
	}

	public String getBuyName() {
		return buyName;
	}

	public void setBuyName(String buyName) {
		this.buyName = buyName;
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

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Double getRecommedScore() {
		return recommedScore;
	}

	public void setRecommedScore(Double recommedScore) {
		this.recommedScore = recommedScore;
	}

	public Integer getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}
}
