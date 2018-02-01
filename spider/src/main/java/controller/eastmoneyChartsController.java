package controller;

import com.alibaba.fastjson.TypeReference;
import entity.eastmoney.api;
import entity.eastmoney.yybIncreaseEntity;
import entity.eastmoney.yybJson;
import util.DateUtil;
import util.httpHelper;

import java.text.MessageFormat;
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

	public eastmoneyChartsController(boolean enableLoop) {
		super(enableLoop);
	}

	@Override
	public List<yybIncreaseEntity> getData() {

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
		//遍历获取数据
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
			api<yybJson> resJson = JSON.parseObject(response, new TypeReference<api<yybJson>>() {
			});
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}