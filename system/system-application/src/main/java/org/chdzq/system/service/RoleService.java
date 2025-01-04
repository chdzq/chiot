package org.chdzq.system.service;

import org.chdzq.system.command.*;

/**
 * 角色服务
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 16:57
 */
public interface RoleService {
    /**
     * 创建
     * @param cmd
     */
    void create(RoleCreateCommand cmd);

    /**
     * 更新
     * @param cmd
     */
    void update(RoleUpdateCommand cmd);

    /**
     * 删除
     * @param cmd
     */
    void delete(RoleDeleteCommand cmd);


    /**
     * 授权
     * @param command
     */
    void authorize(RoleAuthorizeCommand command);
}
