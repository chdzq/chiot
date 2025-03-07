package org.chdzq.common.core.utils;

import java.util.UUID;

/**
 * TODO
 *
 * @author chdzq
 * @create 2025/3/7 11:40
 */
public class IdUtil {
    // ------------------------------------------------------------------- UUID

    /**
     * 获取随机UUID
     *
     * @return 随机UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String simpleUUID() {
        return randomUUID().replaceAll("-", "");
    }
}
