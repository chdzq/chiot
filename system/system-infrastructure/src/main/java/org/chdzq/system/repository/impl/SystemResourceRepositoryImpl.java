package org.chdzq.system.repository.impl;

import org.chdzq.common.core.constants.Constant;
import org.chdzq.common.mybatis.core.query.QueryWrapperX;
import org.chdzq.common.mybatis.core.query.WrapperX;
import org.chdzq.common.mybatis.core.service.ServiceImplX;
import org.chdzq.system.convert.SystemInfraConvertor;
import org.chdzq.system.entity.Resource;
import org.chdzq.system.repository.SystemResourceService;
import org.chdzq.system.repository.dao.SystemResourceMapper;
import org.chdzq.system.repository.po.SystemResourceDO;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.Condition;

/**
 * 资源的仓库
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 09:43
 */
@Service
public class SystemResourceRepositoryImpl extends ServiceImplX<SystemResourceMapper, SystemResourceDO> implements SystemResourceService {

    @Override
    public Resource get(Long id) {
        return baseMapper.selectEntityBy(
                WrapperX.<SystemResourceDO>lambdaQuery()
                        .eq(SystemResourceDO::getId, id)
                        .eq(SystemResourceDO::getDeleted, Constant.UNDELETED)
        );
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
    public Resource getResourceInParentByCode(Long parentId, String code) {
        return baseMapper.selectEntityBy(
                WrapperX.<SystemResourceDO>lambdaQuery()
                        .eq(SystemResourceDO::getDeleted, Constant.UNDELETED)
                        .eq(SystemResourceDO::getParentId, parentId)
                        .eq(SystemResourceDO::getCode, code)
        );
    }

    @Override
    public Resource getResourceInParentByName(Long parentId, String name) {
        return baseMapper.selectEntityBy(
                WrapperX.<SystemResourceDO>lambdaQuery()
                        .eq(SystemResourceDO::getDeleted, Constant.UNDELETED)
                        .eq(SystemResourceDO::getParentId, parentId)
                        .eq(SystemResourceDO::getName, name)
        );
    }

    @Override
    public List<SystemResourceDO> getByRoleId(Long roleId) {
        return baseMapper.selectByRoleId(roleId);
    }

    @Override
    public List<Resource> getResources(List<Long> resourceIds) {
        if (CollectionUtils.isEmpty(resourceIds)) {
            return new ArrayList<>();
        }
        List<SystemResourceDO> resources = listByIds(resourceIds);
        return SystemInfraConvertor.INSTANCE.resourceDo2ResourceList(resources);
    }
}
