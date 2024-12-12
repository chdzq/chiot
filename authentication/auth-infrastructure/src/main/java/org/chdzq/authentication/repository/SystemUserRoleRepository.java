package org.chdzq.authentication.repository;

import org.chdzq.authentication.entity.Role;

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
    void saveUserRelationship(Long userId, List<Role> roles);
}
