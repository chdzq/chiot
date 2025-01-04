package org.chdzq.system.repository;

import org.chdzq.system.entity.Role;

import java.util.List;

/**
 * 系统用户角色资源库
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/12 11:46
 */
public interface SystemUserRoleRepository {

    /**
     * 保存用户的角色列表关系
     * @param userId
     * @param roles
     */
    void authorizeUserRoles(Long userId, List<Role> roles);

}
