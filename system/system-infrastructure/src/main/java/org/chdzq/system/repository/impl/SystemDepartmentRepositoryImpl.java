package org.chdzq.system.repository.impl;

import org.chdzq.common.mybatis.core.service.ServiceImplX;
import org.chdzq.system.convert.SystemInfraConvertor;
import org.chdzq.system.entity.Department;
import org.chdzq.system.repository.DepartmentRepository;
import org.chdzq.system.repository.dao.SystemDepartmentMapper;
import org.chdzq.system.repository.po.SystemDepartmentDO;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 系统部门仓库实现类
 *
 * @author chdzq
 * @create 2025/2/18 18:11
 */
@Repository
public class SystemDepartmentRepositoryImpl extends ServiceImplX<SystemDepartmentMapper, SystemDepartmentDO>
        implements DepartmentRepository {
    @Override
    public void create(Department entity) {
        SystemDepartmentDO departDO = SystemInfraConvertor.INSTANCE.department2SystemDepartDO(entity);
        if (Objects.isNull(departDO)) {
            return;
        }
        save(departDO);
    }

    @Override
    public void update(Department entity) {
        SystemDepartmentDO departDO = SystemInfraConvertor.INSTANCE.department2SystemDepartDO(entity);
        if (Objects.isNull(departDO)) {
            return;
        }
        updateById(departDO);
    }

    @Override
    public void delete(Department entity) {
        if (Objects.isNull(entity) || Objects.isNull(entity.getId())) {
            return;
        }
        removeById(entity.getId());
    }

    @Override
    public Department get(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return baseMapper.selectEntityById(id);
    }

    @Override
    public Department getByCode(String code) {
        if (!StringUtils.hasText(code)) {
            return null;
        }
        return baseMapper.selectEntityByCode(code);
    }

    @Override
    public Department getByName(String name) {
        if (!StringUtils.hasText(name)) {
            return null;
        }
        return baseMapper.selectEntityByName(name);
    }
}
