package org.chdzq.system.service;

import org.chdzq.system.query.ResourceQuery;
import org.chdzq.system.query.model.ResourceTreeVO;
import org.chdzq.system.query.model.ResourceVO;
import org.chdzq.system.query.model.RoleVO;

import java.util.Collection;
import java.util.List;

/**
 * 资源查询服务
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 14:40
 */
public interface ResourceQueryService {

    /**
     * 查询列表
     * @return 列表结果
     */
    List<ResourceTreeVO> tree();

    /**
     * 根据条件查询
     * @param query 查询条件
     * @return 资源列表
     */
    List<ResourceVO> list(ResourceQuery query);

    /**
     * 根据角色编码列表查询所有的资源列表
     * @param roleIds
     * @return
     */
    List<ResourceVO> listByRoleIds(Collection<Long> roleIds);
}
