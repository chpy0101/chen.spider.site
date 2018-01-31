package entity.eastmoney;

import entity.DeepCopy;

public class yybIncreaseEntity implements DeepCopy<yybIncreaseEntity> {

	public yybIncreaseEntity(String name, String id, int rate, double oneDayIncreaseRate, double oneDayIncreasePro, double threeDayIncreaseRate, double threeDayIncreasePro, double fiveDayIncreaseRate, double fiveDayIncreasePro) {
		this.name = name;
		this.id = id;
		this.rate = rate;
		this.oneDayIncreaseRate = oneDayIncreaseRate;
		this.oneDayIncreasePro = oneDayIncreasePro;
		this.threeDayIncreaseRate = threeDayIncreaseRate;
		this.threeDayIncreasePro = threeDayIncreasePro;
		this.fiveDayIncreaseRate = fiveDayIncreaseRate;
		this.fiveDayIncreasePro = fiveDayIncreasePro;
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
	 * 三天涨幅
	 */
	private double threeDayIncreaseRate;
	/**
	 * 三天上涨概率
	 */
	private double threeDayIncreasePro;
	/**
	 * 五天涨幅
	 */
	private double fiveDayIncreaseRate;
	/**
	 * 五天上涨概率
	 */
	private double fiveDayIncreasePro;


	@Override
	public yybIncreaseEntity copy() {
		return new yybIncreaseEntity(
				name, id, rate, oneDayIncreaseRate, oneDayIncreasePro, threeDayIncreaseRate, threeDayIncreasePro, fiveDayIncreaseRate, fiveDayIncreasePro
		);
	}
}
