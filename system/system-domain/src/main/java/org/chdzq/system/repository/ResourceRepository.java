package org.chdzq.system.repository;

import org.chdzq.common.core.ddd.IBaseRepository;
import org.chdzq.system.entity.AuthInfo;
import org.chdzq.system.entity.Resource;

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
     * 根据主键查询是否存在
     * @param id 主键
     * @return
     */
    Boolean isExistByKey(Long id);

    /**
     * 根据主键列表查询是否全部存在
     * @param ids 主键列表
     * @return
     */
    Boolean isExistByKeys(List<Long> ids);


    /**
     * 根据主键查询权限编码
     * @param id
     * @return
     */
    String getPermissionByKey(Long id);

    /**
     * 根据节点查询父节点
     * @param id 节点
     * @return 父节点ID
     */
    Long getParentIdByKey(Long id);


    /**
     * 根据父节点和编码查询资源主键
     * @param parentId 父节点
     * @param code 编码
     * @return 主键
     */
    Long getResourceIdByCode(Long parentId, String code);

    /**
     * 根据父节点和编码查询资源主键
     * @param parentId 父节点
     * @param name 名称
     * @return 主键
     */
    Long getResourceIdByName(Long parentId, String name);
}
