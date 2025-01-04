package org.chdzq.system.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.chdzq.common.mybatis.core.service.ServiceImplX;
import org.chdzq.system.entity.Resource;
import org.chdzq.system.repository.SystemRoleResourceService;
import org.chdzq.system.repository.dao.SystemRoleMapper;
import org.chdzq.system.repository.dao.SystemRoleResourceMapper;
import org.chdzq.system.repository.po.SystemResourceDO;
import org.chdzq.system.repository.po.SystemRoleResourceDO;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 角色资源授权
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/4 09:10
 */
@Repository
public class SystemRoleResourceServiceImpl extends ServiceImplX<SystemRoleResourceMapper, SystemRoleResourceDO> implements SystemRoleResourceService {

    @Override
    public void authorizeRoleResources(Long roleId, List<Resource> resources) {
        if (roleId == null) {
            return;
        }
        removeById(roleId);
        if (CollectionUtils.isEmpty(resources)) {
            return;
        }

        List<SystemRoleResourceDO> list = resources.stream().map((resource) -> {
            SystemRoleResourceDO roleResourceDO = new SystemRoleResourceDO();
            roleResourceDO.setRoleId(roleId);
            roleResourceDO.setResourceId(resource.getId());
            return roleResourceDO;
        }).toList();
        saveBatch(list);
    }
}
