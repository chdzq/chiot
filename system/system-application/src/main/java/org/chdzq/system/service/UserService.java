package org.chdzq.system.service;

import org.chdzq.system.command.UserCreateCommand;
import org.chdzq.system.command.UserDeleteCommand;
import org.chdzq.system.command.UserUpdateCommand;
import org.chdzq.system.entity.AuthInfo;
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
    AuthInfo getAuthInfo(QueryAuthInfo param);

    /**
     * 创建用户
     * @param cmd
     */
    void create(UserCreateCommand cmd);

    /**
     * 更新用户
     * @param cmd
     */
    void update(UserUpdateCommand cmd);

    /**
     * 删除用户
     * @param cmd
     */
    void delete(UserDeleteCommand cmd);

}
