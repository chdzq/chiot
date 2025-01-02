package org.chdzq.system.service.impl;

import org.chdzq.common.core.entity.Page;
import org.chdzq.common.mybatis.core.service.ServiceImplX;
import org.chdzq.system.convert.SystemInfraConvertor;
import org.chdzq.system.query.RolePageQuery;
import org.chdzq.system.query.model.RoleVO;
import org.chdzq.system.repository.dao.SystemRoleMapper;
import org.chdzq.system.repository.po.SystemRoleDO;
import org.chdzq.system.service.RoleQueryService;
import org.springframework.stereotype.Service;

/**
 * 角色查询服务
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 17:35
 */
@Service
public class RoleQueryServiceImpl extends ServiceImplX<SystemRoleMapper, SystemRoleDO> implements RoleQueryService {
    @Override
    public Page<? extends RoleVO> page(RolePageQuery param) {
        Page<SystemRoleDO> roleDOPage = baseMapper.selectCustomPage(param, (p, q)->baseMapper.queryPageList(p, q));

        Page<? extends RoleVO> result = roleDOPage.map(SystemInfraConvertor.INSTANCE::roleDo2RoleVO);
        return result;
    }
}
