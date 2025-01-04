package org.chdzq.system.repository;

import org.chdzq.system.repository.po.SystemResourceDO;

import java.util.List;

/**
 * 系统资源
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/4 15:34
 */
public interface SystemResourceService extends ResourceRepository {

    /**
     * 根据 角色Id 查询所有的资源
     * @param roleId
     * @return
     */
    List<SystemResourceDO> getByRoleId(Long roleId);
}
