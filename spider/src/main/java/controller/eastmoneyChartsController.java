package controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import entity.eastmoney.api;
import entity.eastmoney.yybIncreaseEntity;
import entity.eastmoney.yybJson;
import entity.eastmoney.yybStockInfo;
import util.DateUtil;
import util.httpHelper;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class eastmoneyChartsController extends abstractController<List<yybIncreaseEntity>> {

	private static final String YYB_URL = "http://datainterface3.eastmoney.com//EM_DataCenter_V3/api/LHBYYBPH/GetLHBYYBPH";
	private static final String START_URL = "http://data.eastmoney.com/DataCenter_V3/stock2016/BusinessRanking/pagesize=50,page=1,sortRule=-1,sortType=AvgRate2DC,startDate={0},endDate={1},gpfw=0,js=var%20data_tab_1.html";
	private static final String YYBSTOCK_URL = "http://datainterface3.eastmoney.com//EM_DataCenter_V3/api/YYBJXMX/GetYYBJXMX?tkn=eastmoney&salesCode={0}&tdir=&dayNum=&startDateTime={1}&endDateTime={2}&sortfield=&sortdirec=1&pageNum=1&pageSize={3}&cfg=yybjymx";
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final Map<String, Integer> YYB_FIELDMAP = new HashMap<String, Integer>() {
		{
			put("AvgRate1DC", 0);
			put("BCount1DC", 0);
			put("UpRate1DC", 0);
			put("AvgRate3DC", 0);
			put("BCount3DC", 0);
			put("UpRate3DC", 0);
			put("AvgRate5DC", 0);
			put("BCount5DC", 0);
			put("UpRate5DC", 0);
			put("AvgRate10DC", 0);
			put("BCount10DC", 0);
			put("UpRate10DC", 0);
			put("SalesName", 0);
			put("SalesCode", 0);
		}
	};

	private static final Map<String, Integer> YYB_STOCK_FIELDMAP = new HashMap<String, Integer>() {
		{
			put("ActBuyNum", 0);
			put("ActSellNum", 0);
			put("CPrice", 0);
			put("RChange1DC", 0);
			put("RChange3DC", 0);
			put("RChange5DC", 0);
			put("SCode", 0);
			put("SalesCode", 0);
			put("TDate", 0);
		}
	};

	public eastmoneyChartsController(boolean enableLoop) {
		super(enableLoop);
	}

	@Override
	public List<yybIncreaseEntity> getData() {

		//获取营业部信息。并筛选
		List<yybIncreaseEntity> list = getyybInfo(DateUtil.addMonth(DateUtil.nowDate(), -3), DateUtil.nowDate());
		//排序顺序五天，三天涨幅靠前，
		List<yybIncreaseEntity> data = list.stream().filter(t -> t.getRate() > 0).sorted((x1, x2) -> {
			return Double.compare(x1.getRate(), x2.getRate());
		}).collect(Collectors.toList());
		return null;
	}

	/**
	 * 获取营业部列表
	 *
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<yybIncreaseEntity> getyybInfo(Date startTime, Date endTime) {

		List<yybIncreaseEntity> yybInfo = new ArrayList<>();

		//获取总页数
		Integer pageCount = 50;
		String startStr = DateUtil.formatDate(startTime, DATE_FORMAT);
		String endStr = DateUtil.formatDate(endTime, DATE_FORMAT);
		String startResponse = httpHelper.get(MessageFormat.format(START_URL, startTime, endTime), null);
		String regex = "(?<=\"pages\":)[\\d]+?(?=,)";
		Pattern pattern = Pattern.compile(regex);
		Matcher pageMatcher = pattern.matcher(startResponse);
		if (pageMatcher.find()) {
			pageCount = Integer.parseInt(pageMatcher.group(0));
		}
		Map<String, String> paramsConfi = new HashMap<>();
		paramsConfi.put("tkn", "eastmoney");
		paramsConfi.put("cfg", "yybph");
		paramsConfi.put("salesCode", "");
		paramsConfi.put("monthNum", "");
		paramsConfi.put("startDateTime", startStr);
		paramsConfi.put("endDateTime", endStr);
		paramsConfi.put("sortfield", "AvgRate2DC");
		paramsConfi.put("sortdirec", "1");
		paramsConfi.put("pageNum", "");
		paramsConfi.put("pageSize", "100");
		for (int i = 1; i < pageCount; i++) {
			paramsConfi.replace("pageNum", Integer.toString(i));
			String response = httpHelper.get(YYB_URL, paramsConfi);
			api<List<yybJson>> resJson = JSON.parseObject(response, new TypeReference<api<List<yybJson>>>() {
			});
			if (resJson.getData() != null) {
				//获取数据结构
				String[] fieldNameAry = resJson.getData().get(0).getFieldName().split(",");
				for (int f = 0; f < fieldNameAry.length; f++) {
					if (YYB_FIELDMAP.containsKey(fieldNameAry[f])) {
						YYB_FIELDMAP.replace(fieldNameAry[f], f);
					}
				}
				//赋值
				for (String valueStr : resJson.getData().get(0).getData()) {
					String[] value = valueStr.split("\\|");
					yybIncreaseEntity entity = new yybIncreaseEntity();
					entity.setOneDayBuyTimes(Integer.parseInt(value[YYB_FIELDMAP.get("BCount1DC")]));
					if (entity.getOneDayBuyTimes() > 0) {
						entity.setOneDayIncreaseRate(Double.parseDouble(value[YYB_FIELDMAP.get("AvgRate1DC")]));
						entity.setOneDayIncreasePro(Double.parseDouble(value[YYB_FIELDMAP.get("UpRate1DC")]));
					}
					entity.setThreeDayBuyTimes(Integer.parseInt(value[YYB_FIELDMAP.get("BCount3DC")]));
					if (entity.getThreeDayBuyTimes() > 0) {
						entity.setThreeDayIncreaseRate(Double.parseDouble(value[YYB_FIELDMAP.get("AvgRate3DC")]));
						entity.setThreeDayIncreasePro(Double.parseDouble(value[YYB_FIELDMAP.get("UpRate3DC")]));
					}
					entity.setFiveDayBuyTimes(Integer.parseInt(value[YYB_FIELDMAP.get("BCount5DC")]));
					if (entity.getFiveDayBuyTimes() > 0) {
						entity.setFiveDayIncreaseRate(Double.parseDouble(value[YYB_FIELDMAP.get("AvgRate5DC")]));
						entity.setFiveDayIncreasePro(Double.parseDouble(value[YYB_FIELDMAP.get("UpRate5DC")]));
					}
					entity.setTenDayBuyTimes(Integer.parseInt(value[YYB_FIELDMAP.get("BCount10DC")]));
					if (entity.getTenDayBuyTimes() > 0) {
						entity.setTenDayIncreaseRate(Double.parseDouble(value[YYB_FIELDMAP.get("AvgRate10DC")]));
						entity.setTenDayIncreasePro(Double.parseDouble(value[YYB_FIELDMAP.get("UpRate10DC")]));
					}
					entity.setName(value[YYB_FIELDMAP.get("SalesName")]);
					entity.setId(value[YYB_FIELDMAP.get("SalesCode")]);

					yybInfo.add(entity);
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(e.toString());
				e.printStackTrace();
			}
		}
		return yybInfo;
	}

	/**
	 * 获取营业部购买股票
	 *
	 * @param buyCode
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<yybStockInfo> getStockInfo(String buyCode, String startTime, String endTime) {
		ArrayList<yybStockInfo> data = new ArrayList<yybStockInfo>();
		try {
			final String pageSize = "20";
			String response = httpHelper.get(MessageFormat.format(YYBSTOCK_URL, buyCode, startTime, endTime, pageSize.toString()), null);
			api<List<yybJson>> resJson = JSON.parseObject(response, new TypeReference<api<List<yybJson>>>() {
			});
			if (resJson != null) {
				String[] fieldNameAry = resJson.getData().get(0).getFieldName().split(",");
				for (int f = 0; f < fieldNameAry.length; f++) {
					if (YYB_STOCK_FIELDMAP.containsKey(fieldNameAry[f])) {
						YYB_STOCK_FIELDMAP.replace(fieldNameAry[f], f);
					}
				}
				//赋值
				for (String valueStr : resJson.getData().get(0).getData()) {
					String[] value = valueStr.split("\\|");
					yybStockInfo entity = new yybStockInfo();
					entity.setBuyNum(Double.parseDouble(value[YYB_FIELDMAP.get("ActBuyNum")]));
					entity.setBuyPrice(Double.parseDouble(value[YYB_FIELDMAP.get("CPrice")]));
					entity.setShellNum(Double.parseDouble(value[YYB_FIELDMAP.get("ActSellNum")]));
					entity.setOneDayRate(Double.parseDouble(value[YYB_FIELDMAP.get("RChange1DC")]));
					entity.setThreeDayRate(Double.parseDouble(value[YYB_FIELDMAP.get("RChange3DC")]));
					entity.setFiveDayRate(Double.parseDouble(value[YYB_FIELDMAP.get("RChange5DC")]));
					entity.setStockCode(value[YYB_FIELDMAP.get("SCode")]);
					entity.setYybCode(value[YYB_FIELDMAP.get("SalesCode")]);
					entity.setBuyTime(DateUtil.convertStringToDate(value[YYB_FIELDMAP.get("TDate")]));
					data.add(entity);
				}
			}
		} catch (Exception ex) {

		}
		return data;
	}
}
