package org.chdzq.system.repository.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.chdzq.common.mybatis.core.mapper.BaseMapperX;
import org.chdzq.system.entity.Department;
import org.chdzq.system.query.DepartmentQuery;
import org.chdzq.system.repository.po.SystemDepartmentDO;

import java.util.List;

/**
 * 部门
 *
 * @author chdzq
 * @create 2025/2/18 18:10
 */
@Mapper
public interface SystemDepartmentMapper extends BaseMapperX<SystemDepartmentDO> {

    /**
     * 根据主键查询 部门
     * @param id 主键
     * @return
     */
    Department selectEntityById(Long id);

    /**
     * 根据部门编码查询 部门
     * @param code 编码
     * @return
     */
    Department selectEntityByCode(String code);

    /**
     * 根据部门名称查询 部门
     * @param name 名称
     * @return
     */
    Department selectEntityByName(String name);

    /**
     * 递归查询部门及父节点
     * @param query
     * @return
     */
    List<SystemDepartmentDO> recursiveSelectDepartmentsBy(@Param("query") DepartmentQuery query);
}
