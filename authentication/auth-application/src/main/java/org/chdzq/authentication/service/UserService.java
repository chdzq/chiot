package org.chdzq.authentication.service;

import org.chdzq.authentication.command.CreateUserCommand;
import org.chdzq.authentication.command.DeleteUserCommand;
import org.chdzq.authentication.command.UpdateUserCommand;
import org.chdzq.authentication.entity.SysUserDetail;
import org.chdzq.authentication.entity.User;
import org.chdzq.authentication.query.QueryAuthInfo;

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
    User get(QueryAuthInfo param);

    /**
     * 创建用户
     * @param cmd
     */
    void createUser(CreateUserCommand cmd);

    /**
     * 更新用户
     * @param cmd
     */
    void updateUser(UpdateUserCommand cmd);

    /**
     * 删除用户
     * @param cmd
     */
    void deleteUser(DeleteUserCommand cmd);

}
