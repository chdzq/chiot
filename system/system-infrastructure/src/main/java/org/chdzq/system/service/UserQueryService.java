package org.chdzq.system.service;

import org.chdzq.common.core.entity.Page;
import org.chdzq.system.entity.AuthInfo;
import org.chdzq.system.query.QueryAuthInfo;
import org.chdzq.system.query.UserListQuery;
import org.chdzq.system.query.model.UserVO;

/**
 * 用户查询服务
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/16 16:41
 */
public interface UserQueryService {

    /**
     * 查询授权信息
     * @param param 授权查询
     * @return 授权信息
     */
    AuthInfo getAuthInfo(QueryAuthInfo param);


    /**
     * 查询列表数据
     * @param param
     * @return
     */
    Page<? extends UserVO> list(UserListQuery param);
}
