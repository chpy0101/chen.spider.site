package chen.spider.spiderservice.util;

public class DoubleUtil {
	public static double parseDouble(String value) {
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException ex) {
			return 0;
		}
	}
}
