package org.chdzq.common.core.ddd;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 上下文
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/11 23:56
 */
public class AbilityContext {

    private static final ThreadLocal<Map<String, Object>> CONTEXT = new TransmittableThreadLocal<>();

    /**
     * 初始化上下文
     */
    protected static void initContext() {
        Map<String, Object> con = getContext();
        con.clear();
    }

    /**
     * 清除上下文
     */
    protected static void clearContext() {
        CONTEXT.remove();
    }

    /**
     * 获取上下文内容
     *
     * @param key
     * @param <T>
     * @return
     */
    @Nullable
    public static <T> T get(String key) {
        Map<String, Object> con = CONTEXT.get();
        if (Objects.isNull(con)) {
            return null;
        }
        return (T) con.get(key);
    }

    /**
     * 设置上下文参数
     *
     * @param key
     * @param value
     */
    public static void put(String key, Object value) {
        Map<String, Object> con = getContext();
        con.put(key, value);
    }

    private static Map<String, Object> getContext() {
        Map<String, Object> con = CONTEXT.get();
        if (Objects.isNull(con)) {
            CONTEXT.set(new HashMap<>());
            con = CONTEXT.get();
        }
        return con;
    }
}
