package chen.spider.common.type;

/**
 * Created by chpy on 18/3/31.
 */
public enum DifferTimeType {

    DAY_MILLISECOND(1000 * 60 * 60 * 24),
    HOUR_MILLISECOND(1000 * 60 * 60),
    MINUTE_MILLISECOND(1000 * 60),
    SENCOND_MILLISECOND(1000);

    public int value;

    DifferTimeType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
