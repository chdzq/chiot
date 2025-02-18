package org.chdzq.system.service.impl;

import org.chdzq.common.core.tree.TreeUtil;
import org.chdzq.common.mybatis.core.service.ServiceImplX;
import org.chdzq.system.convert.SystemInfraConvertor;
import org.chdzq.system.query.DepartmentQuery;
import org.chdzq.system.query.model.DepartmentTreeVO;
import org.chdzq.system.repository.dao.SystemDepartmentMapper;
import org.chdzq.system.repository.po.SystemDepartmentDO;
import org.chdzq.system.service.DepartmentQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门查询
 *
 * @author chdzq
 * @create 2025/2/18 19:00
 */
@Service
public class DepartmentQueryServiceImpl extends ServiceImplX<SystemDepartmentMapper, SystemDepartmentDO>
        implements DepartmentQueryService {
    @Override
    public List<DepartmentTreeVO> tree(DepartmentQuery query) {
        List<SystemDepartmentDO> departmentDOS = baseMapper.recursiveSelectDepartmentsBy(query);
        List<DepartmentTreeVO> treeVOList = SystemInfraConvertor.INSTANCE.departmentDOList2SystemDepartTreeVOList(departmentDOS);
        return TreeUtil.buildTree(treeVOList);
    }
}
