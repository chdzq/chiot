package org.chdzq.common.core.constants;

/**
 * Jwt 常量
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/27 13:46
 */
public interface JwtClaimConstant {
    /**
     * 用户ID
     */
    String USER_ID = "userId";

    /**
     * 用户名
     */
    String USERNAME = "username";

    /**
     * 部门ID
     */
    String DEPT_ID = "deptId";

    /**
     * 数据权限
     */
    String DATA_SCOPE = "dataScope";

    /**
     * 权限(角色Code)集合
     */
    String AUTHORITIES = "authorities";


    String JTI = "jti";

    String EXP = "exp";
}
