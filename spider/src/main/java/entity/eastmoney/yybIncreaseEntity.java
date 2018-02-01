package entity.eastmoney;

import entity.DeepCopy;

public class yybIncreaseEntity implements DeepCopy<yybIncreaseEntity> {

	public yybIncreaseEntity(String name, String id, int rate, double oneDayIncreaseRate, double oneDayIncreasePro, int oneDayBuyTimes, double threeDayIncreaseRate, double threeDayIncreasePro, int threeDayBuyTimes, double fiveDayIncreaseRate, double fiveDayIncreasePro, int fiveDayBuyTimes, double tenDayIncreaseRate, double tenDayIncreasePro, int tenDayBuyTimes) {
		this.name = name;
		this.id = id;
		this.rate = rate;
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
	private int rate;
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
				name, id, rate, oneDayIncreaseRate, oneDayIncreasePro, oneDayBuyTimes, threeDayIncreaseRate, threeDayIncreasePro, threeDayBuyTimes, fiveDayIncreaseRate, fiveDayIncreasePro, fiveDayBuyTimes, tenDayIncreaseRate, tenDayIncreasePro, tenDayBuyTimes
		);
	}
}
