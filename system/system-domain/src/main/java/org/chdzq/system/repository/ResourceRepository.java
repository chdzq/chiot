package org.chdzq.system.repository;

import org.chdzq.common.core.ddd.IBaseRepository;
import org.chdzq.system.entity.AuthInfo;
import org.chdzq.system.entity.Resource;
import org.chdzq.system.query.model.ResourceVO;

import java.util.List;

/**
 * 资源存储
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 02:09
 */
public interface ResourceRepository extends IBaseRepository<Resource, Long> {
    /**
     * 根据父节点和编码查询资源
     * @param parentId 父节点
     * @param code 编码
     * @return 主键
     */
    Resource getResourceInParentByCode(Long parentId, String code);

    /**
     * 根据父节点和编码查询资源
     * @param parentId 父节点
     * @param name 名称
     * @return 主键
     */
    Resource getResourceInParentByName(Long parentId, String name);

    /**
     * 根据ID列表查询资源列表
     * @param resourceIds
     * @return
     */
    List<Resource> getResources(List<Long> resourceIds);
}
