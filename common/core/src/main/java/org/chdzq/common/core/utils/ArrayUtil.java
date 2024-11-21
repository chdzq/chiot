package org.chdzq.common.core.utils;

/**
 * @author chdzq
 * @version 1.0
 * @description: ArrayUtils
 * @date 2024/11/20 22:44
 */
public class ArrayUtil {
    private ArrayUtil() {
    }

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    public static boolean isArray(Object obj) {
        return obj != null && obj.getClass().isArray();
    }
}
