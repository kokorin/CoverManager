package covermanager.util;

import java.util.List;

public class ListUtil {
    private ListUtil(){}

    public static <T> void addOrUpdate(List<T> list, T item, T original) {
        int index = list.indexOf(original);
        if (index == -1) {
            list.add(item);
        } else {
            list.set(index, item);
        }
    }
}
