package org.chdzq.system.service;

import org.chdzq.common.core.entity.Page;
import org.chdzq.system.entity.AuthInfo;
import org.chdzq.system.query.QueryAuthInfo;
import org.chdzq.system.query.UserPageQuery;
import org.chdzq.system.query.model.UserInfo;
import org.chdzq.system.query.model.UserPageVO;
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
    Page<? extends UserPageVO> page(UserPageQuery param);


    /**
     * 查询当前用户的登录信息
     * @return
     */
    UserInfo getCurrentUserInfo();

    /**
     * 根据用户id 查询用户的详细信息
     * @param userId
     * @return
     */
    UserVO detail(Long userId);
}
