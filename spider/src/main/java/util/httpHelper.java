package util;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class httpHelper {

	public static String get(String url, Map<String, String> params) {
		return get(url, params, "utf-8");
	}

	public static String get(String url, Map<String, String> params, String encoding) {
		try {
			URL uri = new URL(url);
			HttpURLConnection con = (HttpURLConnection) uri.openConnection();

			con.setRequestMethod("GET");
			con.setConnectTimeout(60000);
			con.setDoOutput(true);
			con.connect();



		} catch (Exception ex) {
			LogUtil.error("get request failed", ex);
		}
		return "";
	}
}
