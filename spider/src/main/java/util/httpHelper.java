package util;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.stream.Collectors;

public class httpHelper {

    public static String get(String url, Map<String, String> params) {
        return get(url, params, "utf-8");
    }

    public static String get(String url, Map<String, String> params, String encoding) {
        try {
            String paramStr = String.join("&", params.entrySet().stream().map(
                    t -> t.getKey() + "=" + getUrlEncoding(t.getValue())).collect(Collectors.toList()));
            URL uri = new URL(url + "?" + paramStr);
            HttpURLConnection con = (HttpURLConnection) uri.openConnection();

            con.setRequestMethod("GET");
            con.setConnectTimeout(60000);
            con.setDoOutput(true);
            con.connect();


        } catch (
                Exception ex)

        {
            LogUtil.error("get request failed", ex);
        }
        return "";
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
