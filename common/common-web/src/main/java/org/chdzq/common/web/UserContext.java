package org.chdzq.common.web;

import org.chdzq.common.core.utils.SpringUtil;
import org.chdzq.common.core.utils.UserContextProvider;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * 用户上下文
 * @author chdzq
 * @version 1.0
 * @date 2024/11/21 23:35
 */
@Configuration
public class UserContext implements ApplicationRunner {

    private static UserContextProvider CONTEXT_PROVIDER;
    /**
     * 获取用户ID
     * @return
     */
    public static Long getUserId() {
        return CONTEXT_PROVIDER.getUserId();
    }

    /**
     * 当前用户名
     */
    public static String getUsername() {
        return CONTEXT_PROVIDER.getUsername();
    }

    /**
     * 当前用户角色集合
     */
    public static Set<String> getRoles() {
        return CONTEXT_PROVIDER.getRoles();
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        CONTEXT_PROVIDER = SpringUtil.getBean(UserContextProvider.class);
    }
}
