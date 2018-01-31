package util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class httpHelper {

	private static final String DEFAULT_CODING = "utf-8";

	private static final HashMap<String, String> HEADER_CONF = new HashMap<String, String>() {
		{
			put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36HH=17 DOL/HELLO_GWF_");
			//put("", "");
		}
	};

	public static String get(String url, Map<String, String> params) {
		return get(url, params, DEFAULT_CODING, null);
	}

	public static String get(String url, Map<String, String> params, Map<String, String> headers) {
		return get(url, params, DEFAULT_CODING, headers);
	}

	public static String get(String url, Map<String, String> params, String encoding, Map<String, String> headers) {
		String responseRes = "";
		HttpURLConnection con = null;
		BufferedReader br = null;
		String paramStr = "";
		try {
			if (params != null)
				paramStr = String.join("&", params.entrySet().stream().map(
						t -> t.getKey() + "=" + getUrlEncoding(t.getValue())).collect(Collectors.toList()));
			URL uri = new URL(url + "?" + paramStr);
			con = (HttpURLConnection) uri.openConnection();

			con.setRequestMethod("GET");
			setHttpHeads(con, HEADER_CONF);
			if (headers != null) {
				setHttpHeads(con, headers);
			}
			con.setConnectTimeout(60000);
			con.setDoInput(true);
			con.setDoOutput(true);
			con.connect();

			int code = con.getResponseCode();
			if (code == 200) {
				StringBuffer resultBuffer = new StringBuffer();
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), encoding));
				String temp;
				while ((temp = br.readLine()) != null) {
					resultBuffer.append(temp);
				}
				responseRes = resultBuffer.toString();
			}
		} catch (Exception ex) {
			LogUtil.error("get request failed", ex);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				} finally {
					br = null;
					if (con != null) {
						con.disconnect();
						con = null;
					}
				}
			}
			if (con != null) {
				con.disconnect();
				con = null;
			}
		}
		return responseRes;
	}

	public static String post(String url, Map<String, String> params) {
		String paramStr = String.join("&", params.entrySet().stream().map(
				t -> t.getKey() + "=" + getUrlEncoding(t.getValue())).collect(Collectors.toList()));

		return post(url, paramStr, "application/x-www-form-urlencoded", null);
	}

	public static String post(String url, Map<String, String> params, Map<String, String> headers) {
		String paramStr = String.join("&", params.entrySet().stream().map(
				t -> t.getKey() + "=" + getUrlEncoding(t.getValue())).collect(Collectors.toList()));

		return post(url, paramStr, "application/x-www-form-urlencoded", headers);
	}

	public static String post(String url, String params, String contentType, Map<String, String> headers) {
		String responseRes = "";
		HttpURLConnection con = null;
		OutputStreamWriter osw = null;
		BufferedReader br = null;
		try {

			URL uri = new URL(url);
			con = (HttpURLConnection) uri.openConnection();

			con.setRequestMethod("POST");
			con.setConnectTimeout(60000);
			setHttpHeads(con, HEADER_CONF);
			if (headers != null)
				setHttpHeads(con, headers);
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type", contentType);
			setHttpHeads(con, HEADER_CONF);
			con.connect();

			if (params != null && params.length() > 0) {
				osw = new OutputStreamWriter(con.getOutputStream(), DEFAULT_CODING);
				osw.write(params);
				osw.flush();
			}
			int code = con.getResponseCode();
			if (code == 200) {
				StringBuffer resultBuffer = new StringBuffer();
				br = new BufferedReader(new InputStreamReader(con.getInputStream(), DEFAULT_CODING));
				String temp;
				while ((temp = br.readLine()) != null) {
					resultBuffer.append(temp);
				}
				responseRes = resultBuffer.toString();
			}
		} catch (Exception ex) {
			LogUtil.error("get request failed", ex);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					br = null;
					throw new RuntimeException(e);
				} finally {
					if (con != null) {
						con.disconnect();
						con = null;
					}
				}
				if (osw != null) {
					try {
						osw.close();
					} catch (IOException e) {
						throw new RuntimeException(e);
					} finally {
						osw = null;
						if (con != null) {
							con.disconnect();
							con = null;
						}
					}
				}
				if (con != null) {
					con.disconnect();
					con = null;
				}
			}
		}
		return responseRes;
	}

	private static void setHttpHeads(HttpURLConnection conn, Map<String, String> heads) {
		for (Map.Entry<String, String> item : heads.entrySet()) {
			conn.setRequestProperty(item.getKey(), item.getValue());
		}
	}

	private static String getUrlEncoding(String text) {
		String endcodingText = "error";
		try {
			endcodingText = URLEncoder.encode(text, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			LogUtil.error("getUrlEncoding", e);
		}
		return endcodingText;
	}
}
