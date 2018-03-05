package chen.spider.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by chpy on 18/3/3.
 */
public class ListUtil {
    public static <T> List<T> toList(Iterator<T> it) {
        if (it == null)
            return null;
        List<T> result = new ArrayList<T>();
        while (it.hasNext()) {
            result.add(it.next());
        }
        return result;
    }

    public static <T> List<T> toList(Iterable<T> it) {
        if (it == null)
            return null;
        return toList(it.iterator());
    }
}
