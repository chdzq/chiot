package org.chdzq.system.service;

import org.chdzq.system.query.DepartmentQuery;
import org.chdzq.system.query.model.DepartmentTreeVO;
import org.chdzq.system.query.model.DepartmentVO;
import org.chdzq.system.repository.po.SystemDepartmentDO;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 部门查询服务类
 *
 * @author chdzq
 * @create 2025/2/18 18:57
 */
public interface DepartmentQueryService {

    /**
     * 查询部门树
     * @param query 查询
     * @return
     */
    List<DepartmentTreeVO> tree(DepartmentQuery query);

    /**
     * 根据主键查询详情
     * @param id
     * @return
     */
    DepartmentVO view(Long id);

    /**
     * 根据ID列表查询部门列表
     * @param idList
     * @return
     */
    List<SystemDepartmentDO>listByIds(Collection<? extends Serializable> idList);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    SystemDepartmentDO getById(Serializable id);
}
