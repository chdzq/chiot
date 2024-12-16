package org.chdzq.common.core.utils;

import org.chdzq.common.core.constants.JwtClaimConstant;
import org.chdzq.common.core.constants.SystemConstant;
import org.chdzq.common.core.enums.DataScopeEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 用户上下文数据提供服务者
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/13 17:15
 */
public interface UserContextProvider {

    /**
     * 当前用户ID
     */
    Long getUserId();

    /**
     * 当前用户名
     */
    String getUsername();

    /**
     * 当前用户角色集合
     */
    Set<String> getRoles();

    /**
     * 获取部门ID
     */
    Long getDeptId();

    /**
     * 是否是root用户
     * @return
     */
    Boolean isRoot();


    /**
     * 过期时间
     * @return
     */
    Long getExp();

    /**
     * 获取数据权限范围
     *
     * @return 数据权限范围
     */
    DataScopeEnum getDataScope();
}
