package org.chdzq.system.repository;

import org.chdzq.common.core.ddd.IBaseRepository;
import org.chdzq.system.entity.Role;

/**
 * 角色存储
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 02:08
 */
public interface RoleRepository extends IBaseRepository<Role, Long> {

    /**
     * 根据主键查询是否存在
     * @param id 主键
     * @return
     */
    Boolean isExistByKey(Long id);


    /**
     * 根据编码查询资源主键
     * @param code 编码
     * @return 主键
     */
    Long getIdByCode(String code);

    /**
     * 根据编码查询资源主键
     * @param name 名称
     * @return 主键
     */
    Long getIdByName(String name);
}
