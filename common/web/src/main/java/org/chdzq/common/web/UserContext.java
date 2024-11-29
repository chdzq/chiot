package org.chdzq.common.web;

import com.alibaba.ttl.TransmittableThreadLocal;
import org.springframework.util.StringUtils;

/**
 * 用户上下文
 * @author chdzq
 * @version 1.0
 * @date 2024/11/21 23:35
 */
public final class UserContext {

    private UserContext() {}
    static final TransmittableThreadLocal<String> USER_ID = new TransmittableThreadLocal<>();

    /**
     * 获取用户ID
     * @return
     */
    public static String getUserId() {
        return USER_ID.get();
    }

    /**
     * 更新用户ID
     * @param userId
     */
    public static void setUserId(String userId) {
        USER_ID.set(userId);
    }

    /**
     * 移除
     */
    public static void clearUserId() {
        USER_ID.remove();
    }

    /**
     * 是否登录
     * @return
     */
    public Boolean isLogin() {
        return StringUtils.hasLength(getUserId());
    }
}
