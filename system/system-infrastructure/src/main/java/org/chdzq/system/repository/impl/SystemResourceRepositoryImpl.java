package org.chdzq.system.repository.impl;

import org.chdzq.common.mybatis.core.query.WrapperX;
import org.chdzq.common.mybatis.core.service.ServiceImplX;
import org.chdzq.system.convert.SystemInfraConvertor;
import org.chdzq.system.entity.Resource;
import org.chdzq.system.repository.ResourceRepository;
import org.chdzq.system.repository.dao.SystemResourceMapper;
import org.chdzq.system.repository.po.SystemResourceDO;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
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
        Serializable existedByKey = baseMapper.isExistedByKey(WrapperX.<SystemResourceDO>lambdaQuery()
                .eq(SystemResourceDO::getId, id));
        return Objects.nonNull(existedByKey);
    }

    @Override
    public Boolean isExistByKeys(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Boolean.FALSE;
        }
        List<Serializable> keys = baseMapper.isExistedByKeys(WrapperX.<SystemResourceDO>lambdaQuery()
                .in(SystemResourceDO::getId, ids));
        return keys.size() == ids.size();
    }

    @Override
    public void create(Resource entity) {
        Assert.isNull(entity.getId(), "创建时主键需要为空");
        SystemResourceDO resourceDO = SystemInfraConvertor.INSTANCE.resource2ResourceDO(entity);
        if(Objects.isNull(resourceDO)) {
            return;
        }
        this.save(resourceDO);
    }

    @Override
    public void update(Resource entity) {
        Assert.notNull(entity.getId(), "更新时主键不能为空");
        SystemResourceDO resourceDO = SystemInfraConvertor.INSTANCE.resource2ResourceDO(entity);
        if(Objects.isNull(resourceDO)) {
            return;
        }
        this.updateById(resourceDO);
    }

    @Override
    public void delete(Resource entity) {
        if (Objects.isNull(entity.getId())) {
            return;
        }
        removeById(entity.getId());
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
