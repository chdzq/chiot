package org.chdzq.system.service;

import org.chdzq.system.query.DepartmentQuery;
import org.chdzq.system.query.model.DepartmentTreeVO;

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
}
