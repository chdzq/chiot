package org.chdzq.system.repository;

import org.chdzq.common.core.ddd.IBaseRepository;
import org.chdzq.system.entity.Department;

/**
 * 部门仓库
 *
 * @author chdzq
 * @create 2025/2/18 18:08
 */
public interface DepartmentRepository extends IBaseRepository<Department, Long>{

    /**
     * 根据编码查询
     * @param code
     * @return
     */
    Department getByCode(String code);

    /**
     * 根据名称查询
     * @param name
     * @return
     */
    Department getByName(String name);
}
