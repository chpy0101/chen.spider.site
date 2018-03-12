package chen.spider.pojo;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "yybbuystock")
public class yybBuyStock {
	private Long id;
	private String buyCode;
	private String buyName;
	private String stockCode;
	private String stockName;
	private Date buyDate;
	private Double recommedScore;
	private Integer buyCount;
	private Date recommendDay;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "buyCode")
	public String getBuyCode() {
		return buyCode;
	}

	public void setBuyCode(String buyCode) {
		this.buyCode = buyCode;
	}

	@Column(name = "buyName")
	public String getBuyName() {
		return buyName;
	}

	public void setBuyName(String buyName) {
		this.buyName = buyName;
	}

	@Column(name = "stockCode")
	public String getStockCode() {
		return stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

	@Column(name = "stockName")
	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	@Column(name = "buyDate")
	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	@Column(name = "recommedScore")
	public Double getRecommedScore() {
		return recommedScore;
	}

	public void setRecommedScore(Double recommedScore) {
		this.recommedScore = recommedScore;
	}

	@Column(name = "buyCount")
	public Integer getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}

	@Column(name = "recommendDay")
	public Date getRecommendDay() {
		return recommendDay;
	}

	public void setRecommendDay(Date recommendDay) {
		this.recommendDay = recommendDay;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = result * 31 + (this.getBuyCode() == null ? 0 : this.getBuyCode().hashCode());
		result = result * 31 + (this.getStockCode() == null ? 0 : this.getStockCode().hashCode());
		result = result * 31 + (this.getBuyDate() == null ? 0 : this.getBuyDate().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof yybBuyStock))
			return false;
		yybBuyStock stock = (yybBuyStock) obj;
		return stock.getBuyCode() == this.getBuyCode() && stock.getStockCode() == this.getStockCode() && stock.getBuyDate().equals(this.getBuyDate());
	}
}
