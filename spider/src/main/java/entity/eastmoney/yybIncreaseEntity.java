package entity.eastmoney;

import entity.DeepCopy;

public class yybIncreaseEntity implements DeepCopy<yybIncreaseEntity> {

	public yybIncreaseEntity() {
	}

	public yybIncreaseEntity(String name, String id, double oneDayIncreaseRate, double oneDayIncreasePro, int oneDayBuyTimes, double threeDayIncreaseRate, double threeDayIncreasePro, int threeDayBuyTimes, double fiveDayIncreaseRate, double fiveDayIncreasePro, int fiveDayBuyTimes, double tenDayIncreaseRate, double tenDayIncreasePro, int tenDayBuyTimes) {
		this.name = name;
		this.id = id;
		this.oneDayIncreaseRate = oneDayIncreaseRate;
		this.oneDayIncreasePro = oneDayIncreasePro;
		this.oneDayBuyTimes = oneDayBuyTimes;
		this.threeDayIncreaseRate = threeDayIncreaseRate;
		this.threeDayIncreasePro = threeDayIncreasePro;
		this.threeDayBuyTimes = threeDayBuyTimes;
		this.fiveDayIncreaseRate = fiveDayIncreaseRate;
		this.fiveDayIncreasePro = fiveDayIncreasePro;
		this.fiveDayBuyTimes = fiveDayBuyTimes;
		this.tenDayIncreaseRate = tenDayIncreaseRate;
		this.tenDayIncreasePro = tenDayIncreasePro;
		this.tenDayBuyTimes = tenDayBuyTimes;
	}

	/**
	 * 营业部名称
	 */
	private String name;
	/**
	 * 营业部id
	 */
	private String id;
	/**
	 * 排名
	 */
	private double rate;
	/**
	 * 一天涨幅
	 */
	private double oneDayIncreaseRate;
	/**
	 * 一天上涨概率
	 */
	private double oneDayIncreasePro;
	/**
	 * 一天购买次数
	 */
	private int oneDayBuyTimes;
	/**
	 * 三天涨幅
	 */
	private double threeDayIncreaseRate;
	/**
	 * 三天上涨概率
	 */
	private double threeDayIncreasePro;
	/**
	 * 三天购买次数
	 */
	private int threeDayBuyTimes;
	/**
	 * 五天涨幅
	 */
	private double fiveDayIncreaseRate;
	/**
	 * 五天上涨概率
	 */
	private double fiveDayIncreasePro;
	/**
	 * 五天购买次数
	 */
	private int fiveDayBuyTimes;
	/**
	 * 十天涨幅
	 */
	private double tenDayIncreaseRate;
	/**
	 * 十天上涨概率
	 */
	private double tenDayIncreasePro;
	/**
	 * 十天购买次数
	 */
	private int tenDayBuyTimes;


	@Override
	public yybIncreaseEntity copy() {
		return new yybIncreaseEntity(
				name, id, oneDayIncreaseRate, oneDayIncreasePro, oneDayBuyTimes, threeDayIncreaseRate, threeDayIncreasePro, threeDayBuyTimes, fiveDayIncreaseRate, fiveDayIncreasePro, fiveDayBuyTimes, tenDayIncreaseRate, tenDayIncreasePro, tenDayBuyTimes
		);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getRate() {
		if (rate == 0) {
			if (this.oneDayIncreaseRate >= 10 || this.threeDayBuyTimes < 3) {
				this.rate = -100;
			}
			rate = this.fiveDayIncreaseRate / 5 * 2 + this.fiveDayIncreasePro * 5 + this.threeDayIncreaseRate / 3 + this.threeDayIncreasePro * 3;
		}
		return rate;
	}

	public double getOneDayIncreaseRate() {
		return oneDayIncreaseRate;
	}

	public void setOneDayIncreaseRate(double oneDayIncreaseRate) {
		this.oneDayIncreaseRate = oneDayIncreaseRate;
	}

	public double getOneDayIncreasePro() {
		return oneDayIncreasePro;
	}

	public void setOneDayIncreasePro(double oneDayIncreasePro) {
		this.oneDayIncreasePro = oneDayIncreasePro;
	}

	public int getOneDayBuyTimes() {
		return oneDayBuyTimes;
	}

	public void setOneDayBuyTimes(int oneDayBuyTimes) {
		this.oneDayBuyTimes = oneDayBuyTimes;
	}

	public double getThreeDayIncreaseRate() {
		return threeDayIncreaseRate;
	}

	public void setThreeDayIncreaseRate(double threeDayIncreaseRate) {
		this.threeDayIncreaseRate = threeDayIncreaseRate;
	}

	public double getThreeDayIncreasePro() {
		return threeDayIncreasePro;
	}

	public void setThreeDayIncreasePro(double threeDayIncreasePro) {
		this.threeDayIncreasePro = threeDayIncreasePro;
	}

	public int getThreeDayBuyTimes() {
		return threeDayBuyTimes;
	}

	public void setThreeDayBuyTimes(int threeDayBuyTimes) {
		this.threeDayBuyTimes = threeDayBuyTimes;
	}

	public double getFiveDayIncreaseRate() {
		return fiveDayIncreaseRate;
	}

	public void setFiveDayIncreaseRate(double fiveDayIncreaseRate) {
		this.fiveDayIncreaseRate = fiveDayIncreaseRate;
	}

	public double getFiveDayIncreasePro() {
		return fiveDayIncreasePro;
	}

	public void setFiveDayIncreasePro(double fiveDayIncreasePro) {
		this.fiveDayIncreasePro = fiveDayIncreasePro;
	}

	public int getFiveDayBuyTimes() {
		return fiveDayBuyTimes;
	}

	public void setFiveDayBuyTimes(int fiveDayBuyTimes) {
		this.fiveDayBuyTimes = fiveDayBuyTimes;
	}

	public double getTenDayIncreaseRate() {
		return tenDayIncreaseRate;
	}

	public void setTenDayIncreaseRate(double tenDayIncreaseRate) {
		this.tenDayIncreaseRate = tenDayIncreaseRate;
	}

	public double getTenDayIncreasePro() {
		return tenDayIncreasePro;
	}

	public void setTenDayIncreasePro(double tenDayIncreasePro) {
		this.tenDayIncreasePro = tenDayIncreasePro;
	}

	public int getTenDayBuyTimes() {
		return tenDayBuyTimes;
	}

	public void setTenDayBuyTimes(int tenDayBuyTimes) {
		this.tenDayBuyTimes = tenDayBuyTimes;
	}
}
