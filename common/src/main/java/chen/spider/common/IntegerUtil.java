package chen.spider.common;

/**
 * Created by chpy on 18/4/5.
 */
public class IntegerUtil {

    /**
     * @param value
     * @return Positive return 1 Zero return 0 Negative return -1
     */
    public static int positiveOrNegative(int value) {
        return value >> 31 | (~((~value + 1) >> 31) + 1);
    }
}
