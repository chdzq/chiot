package org.chdzq.system.repository.impl;

import org.chdzq.common.core.utils.Assert;
import org.chdzq.common.mybatis.core.service.ServiceImplX;
import org.chdzq.system.convert.SystemInfraConvertor;
import org.chdzq.system.entity.Role;
import org.chdzq.system.repository.RoleRepository;
import org.chdzq.system.repository.dao.SystemRoleMapper;
import org.chdzq.system.repository.po.SystemRoleDO;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 角色资源类
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 16:16
 */
@Service
public class SystemRoleRepositoryImpl extends ServiceImplX<SystemRoleMapper, SystemRoleDO> implements RoleRepository {
    @Override
    public Boolean isExistByKey(Long id) {
        SystemRoleDO po = getById(id);
        return Objects.nonNull(po);
    }

    @Override
    public Long getIdByCode(String code) {
        return baseMapper.selectIdByCode(code);
    }

    @Override
    public Long getIdByName(String name) {
        return baseMapper.selectIdByName(name);
    }

    @Override
    public void create(Role entity) {
        Assert.isNull(entity.getId(), "创建时主键需要为空");
        SystemRoleDO po = SystemInfraConvertor.INSTANCE.role2RoleDO(entity);
        save(po);
    }

    @Override
    public void update(Role entity) {
        Assert.notNull(entity.getId(), "更新时主键不能为空");
        SystemRoleDO po = SystemInfraConvertor.INSTANCE.role2RoleDO(entity);
        updateById(po);
    }

    @Override
    public void deleteById(Long id) {
        if (Objects.isNull(id)) {
            return;
        }
        removeById(id);
    }
}
