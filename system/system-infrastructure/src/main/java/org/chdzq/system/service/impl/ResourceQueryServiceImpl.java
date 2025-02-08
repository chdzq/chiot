package org.chdzq.system.service.impl;

import lombok.AllArgsConstructor;
import org.chdzq.common.core.tree.TreeUtil;
import org.chdzq.common.mybatis.core.query.WrapperX;
import org.chdzq.common.mybatis.core.service.ServiceImplX;
import org.chdzq.system.convert.SystemInfraConvertor;
import org.chdzq.system.query.ResourceQuery;
import org.chdzq.system.query.model.ResourceTreeVO;
import org.chdzq.system.query.model.ResourceVO;
import org.chdzq.system.repository.dao.SystemResourceMapper;
import org.chdzq.system.repository.po.SystemResourceDO;
import org.chdzq.system.service.ResourceQueryService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 资源查询服务
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 14:59
 */
@Service
@AllArgsConstructor
public class ResourceQueryServiceImpl extends ServiceImplX<SystemResourceMapper, SystemResourceDO> implements ResourceQueryService {

    private final SystemInfraConvertor convertor = SystemInfraConvertor.INSTANCE;

    @Override
    public List<ResourceTreeVO> tree() {

        List<SystemResourceDO> resources = list(
                WrapperX.<SystemResourceDO>lambdaQuery().orderByDesc(SystemResourceDO::getSort)
        );
        List<ResourceTreeVO> voList = SystemInfraConvertor.INSTANCE.resourceDo2ResourceTreeVOList(resources);
        return TreeUtil.buildTree(voList);
    }

    @Override
    public List<ResourceVO> list(ResourceQuery query) {
        List<SystemResourceDO> resources = baseMapper.selectByRoleId(query.getRoleId());
        return SystemInfraConvertor.INSTANCE.resourceDo2ResourceVOList(resources);
    }

    @Override
    public List<ResourceVO> listByRoleIds(Collection<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return new ArrayList<>(0);
        }
        List<SystemResourceDO> resources = baseMapper.selectByRoleIds(roleIds);
        return resources.stream().map(convertor::resourceDo2ResourceVO).distinct().toList();
    }
}
