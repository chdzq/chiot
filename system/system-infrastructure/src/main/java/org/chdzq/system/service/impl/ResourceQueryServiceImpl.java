package org.chdzq.system.service.impl;

import org.chdzq.common.core.tree.TreeUtil;
import org.chdzq.common.mybatis.core.query.WrapperX;
import org.chdzq.common.mybatis.core.service.ServiceImplX;
import org.chdzq.system.convert.SystemInfraConvertor;
import org.chdzq.system.query.model.ResourceTreeVO;
import org.chdzq.system.repository.dao.SystemResourceMapper;
import org.chdzq.system.repository.po.SystemResourceDO;
import org.chdzq.system.service.ResourceQueryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 资源查询服务
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 14:59
 */
@Service
public class ResourceQueryServiceImpl extends ServiceImplX<SystemResourceMapper, SystemResourceDO> implements ResourceQueryService {


    @Override
    public List<ResourceTreeVO> tree() {

        List<SystemResourceDO> resources = list(
                WrapperX.<SystemResourceDO>lambdaQuery().orderByDesc(SystemResourceDO::getSort)
        );
        List<ResourceTreeVO> voList = SystemInfraConvertor.INSTANCE.resourceDo2ResourceTreeVOList(resources);
        return TreeUtil.buildTree(voList);
    }
}
