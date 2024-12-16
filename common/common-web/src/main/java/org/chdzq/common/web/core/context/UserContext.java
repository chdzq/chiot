package org.chdzq.common.web.core.context;

import org.chdzq.common.security.utils.UserContextSecurityProvider;

import java.util.Set;

/**
 * 用户上下文
 * @author chdzq
 * @version 1.0
 * @date 2024/11/21 23:35
 */
public class UserContext {

    private UserContext() {}

    /**
     * 获取用户ID
     * @return
     */
    public static Long getUserId() {
        return UserContextSecurityProvider.getUserId();
    }

    /**
     * 当前用户名
     */
    public static String getUsername() {
        return UserContextSecurityProvider.getUsername();
    }

    /**
     * 当前用户角色集合
     */
    public static Set<String> getRoles() {
        return UserContextSecurityProvider.getRoles();
    }
}
