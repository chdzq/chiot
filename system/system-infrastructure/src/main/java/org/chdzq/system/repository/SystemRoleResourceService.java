package org.chdzq.system.repository;

import org.chdzq.system.entity.Resource;
import org.chdzq.system.entity.Role;
import org.chdzq.system.repository.po.SystemResourceDO;
import org.chdzq.system.repository.po.SystemRoleResourceDO;

import java.util.List;

/**
 * 角色资源
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/4 09:04
 */
public interface SystemRoleResourceService {

    /**
     * 保存用户的角色列表关系
     * @param resources 关系吧
     */
    void authorizeRoleResources(Long roleId, List<Resource> resources);

}
