package org.chdzq.system.service.impl;

import lombok.AllArgsConstructor;
import org.chdzq.common.core.entity.Page;
import org.chdzq.common.mybatis.core.service.ServiceImplX;
import org.chdzq.common.security.utils.UserContext;
import org.chdzq.system.convert.SystemInfraConvertor;
import org.chdzq.system.entity.AuthInfo;
import org.chdzq.system.query.QueryAuthInfo;
import org.chdzq.system.query.UserPageQuery;
import org.chdzq.system.query.model.*;
import org.chdzq.system.repository.dao.SystemUserMapper;
import org.chdzq.system.repository.po.SystemUserDO;
import org.chdzq.system.service.ResourceQueryService;
import org.chdzq.system.service.RoleQueryService;
import org.chdzq.system.service.UserQueryService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;

/**
 * 用户查询服务
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/17 00:10
 */
@Service
@AllArgsConstructor
public class UserQueryServiceImpl extends ServiceImplX<SystemUserMapper, SystemUserDO> implements UserQueryService {
    private final SystemInfraConvertor convertor = SystemInfraConvertor.INSTANCE;

    private final RoleQueryService roleQueryService;

    private final ResourceQueryService resourceQueryService;

    @Override
    public AuthInfo getAuthInfo(QueryAuthInfo param) {
        String username = param.getUsername();
        if (!StringUtils.hasText(username)) {
            return null;
        }
        return baseMapper.selectUserAuthInfo(username);
    }

    @Override
    public Page<? extends UserVO> page(UserPageQuery param) {
        Page<SystemUserDO> page = customPage(param, (p, q) -> baseMapper.queryPageList(p, q));
        return page.map(SystemInfraConvertor.INSTANCE::userDo2UserVO);
    }

    @Override
    public UserInfo getCurrentUserInfo() {
        Long userId = UserContext.getUserId();
        SystemUserDO userDO = getById(userId);

        Set<String> roleCodes = UserContext.getRoles();

        List<RoleVO> roles = roleQueryService.listByCodes(roleCodes);
        List<Long> roleIds = roles.stream().map(RoleVO::getId).toList();
        List<ResourceTreeVO> resources = resourceQueryService.listByRoleIds(roleIds);

        return convertor.userDo2UserInfo(userDO, roles, resources);
    }
}
