package org.chdzq.system.service;

import org.chdzq.system.entity.UserAuthInfo;
import org.chdzq.system.query.QueryAuthInfo;

/**
 * 用户服务
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 16:26
 */
public interface UserService {


    /**
     * 查询授权信息
     * @param param 授权查询
     * @return 授权信息
     */
    UserAuthInfo query(QueryAuthInfo param);
}
