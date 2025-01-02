package org.chdzq.system.repository.impl;

import org.chdzq.common.mybatis.core.service.ServiceImplX;
import org.chdzq.system.convert.SystemConvertor;
import org.chdzq.system.entity.Resource;
import org.chdzq.system.repository.ResourceRepository;
import org.chdzq.system.repository.dao.SystemResourceMapper;
import org.chdzq.system.repository.po.SystemResourceDO;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * 资源的仓库
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 09:43
 */
@Service
public class SystemResourceRepositoryImpl extends ServiceImplX<SystemResourceMapper, SystemResourceDO> implements ResourceRepository {
    @Override
    public Boolean isExistByKey(Long id) {
        SystemResourceDO resourceDO = getById(id);
        return Objects.nonNull(resourceDO);
    }

    @Override
    public void create(Resource entity) {
        Assert.isNull(entity.getId(), "创建时主键需要为空");
        SystemResourceDO resourceDO = SystemConvertor.INSTANCE.resource2ResourceDO(entity);
        if(Objects.isNull(resourceDO)) {
            return;
        }
        this.save(resourceDO);
    }

    @Override
    public void update(Resource entity) {
        Assert.notNull(entity.getId(), "更新时主键不能为空");
        SystemResourceDO resourceDO = SystemConvertor.INSTANCE.resource2ResourceDO(entity);
        if(Objects.isNull(resourceDO)) {
            return;
        }
        this.updateById(resourceDO);
    }

    @Override
    public void deleteById(Long id) {
        if (Objects.isNull(id)) {
            return;
        }
        removeById(id);
    }

    @Override
    public String getPermissionByKey(Long id) {
        SystemResourceDO resourceDO = getById(id);
        return resourceDO.getPermission();
    }

    @Override
    public Long getParentIdByKey(Long id) {
        SystemResourceDO resourceDO = getById(id);
        return resourceDO.getParentId();
    }

    @Override
    public Long getResourceIdByCode(Long parentId, String code) {
        return baseMapper.selectResourceIdByCode(parentId, code);
    }

    @Override
    public Long getResourceIdByName(Long parentId, String name) {
        return baseMapper.selectResourceIdByName(parentId, name);
    }
}
