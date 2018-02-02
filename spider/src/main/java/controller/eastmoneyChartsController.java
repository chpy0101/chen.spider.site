package controller;

import com.alibaba.fastjson.TypeReference;
import entity.eastmoney.api;
import entity.eastmoney.yybIncreaseEntity;
import entity.eastmoney.yybJson;
import util.DateUtil;
import util.httpHelper;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;

public class eastmoneyChartsController extends abstractController<List<yybIncreaseEntity>> {

	private static final String YYB_URL = "http://datainterface3.eastmoney.com//EM_DataCenter_V3/api/LHBYYBPH/GetLHBYYBPH";
	private static final String START_URL = "http://data.eastmoney.com/DataCenter_V3/stock2016/BusinessRanking/pagesize=50,page=1,sortRule=-1,sortType=AvgRate2DC,startDate={0},endDate={1},gpfw=0,js=var%20data_tab_1.html";
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

	public eastmoneyChartsController(boolean enableLoop) {
		super(enableLoop);
	}

	@Override
	public List<yybIncreaseEntity> getData() {
		List<yybIncreaseEntity> yybInfo = new ArrayList<>();

		//获取总页数
		Integer pageCount = 50;
		String startTime = DateUtil.formatDate(DateUtil.addMonth(DateUtil.nowDate(), -6), DATE_FORMAT);
		String endTime = DateUtil.formatDate(DateUtil.nowDate(), DATE_FORMAT);
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
		paramsConfi.put("startDateTime", startTime);
		paramsConfi.put("endDateTime", endTime);
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
					String[] value = valueStr.split("|");
					yybIncreaseEntity entity = new yybIncreaseEntity();
					entity.setOneDayBuyTimes(Integer.parseInt(value[YYB_FIELDMAP.get("BCount1DC")]));
					entity.setOneDayIncreaseRate(Double.parseDouble(value[YYB_FIELDMAP.get("AvgRate1DC")]));
					entity.setOneDayIncreasePro(Double.parseDouble(value[YYB_FIELDMAP.get("UpRate1DC")]));
					entity.setThreeDayBuyTimes(Integer.parseInt(value[YYB_FIELDMAP.get("BCount3DC")]));
					entity.setThreeDayIncreaseRate(Double.parseDouble(value[YYB_FIELDMAP.get("AvgRate3DC")]));
					entity.setThreeDayIncreasePro(Double.parseDouble(value[YYB_FIELDMAP.get("UpRate3DC")]));
					entity.setFiveDayBuyTimes(Integer.parseInt(value[YYB_FIELDMAP.get("BCount5DC")]));
					entity.setFiveDayIncreaseRate(Double.parseDouble(value[YYB_FIELDMAP.get("AvgRate5DC")]));
					entity.setFiveDayIncreasePro(Double.parseDouble(value[YYB_FIELDMAP.get("UpRate5DC")]));
					entity.setTenDayBuyTimes(Integer.parseInt(value[YYB_FIELDMAP.get("BCount10DC")]));
					entity.setTenDayBuyTimes(Integer.parseInt(value[YYB_FIELDMAP.get("BCount10DC")]));
					entity.setTenDayBuyTimes(Integer.parseInt(value[YYB_FIELDMAP.get("BCount10DC")]));

					entity.setName(value[YYB_FIELDMAP.get("SalesName")]);
					entity.setId(value[YYB_FIELDMAP.get("SalesCode")]);

					yybInfo.add(entity);
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
