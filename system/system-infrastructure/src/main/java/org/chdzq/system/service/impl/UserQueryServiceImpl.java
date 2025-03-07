package org.chdzq.system.service.impl;

import lombok.AllArgsConstructor;
import org.chdzq.common.core.constants.Constant;
import org.chdzq.common.core.entity.Page;
import org.chdzq.common.mybatis.core.service.ServiceImplX;
import org.chdzq.common.security.utils.UserContext;
import org.chdzq.system.convert.SystemInfraConvertor;
import org.chdzq.system.entity.AuthInfo;
import org.chdzq.system.query.QueryAuthInfo;
import org.chdzq.system.query.UserPageQuery;
import org.chdzq.system.query.model.*;
import org.chdzq.system.repository.dao.SystemUserMapper;
import org.chdzq.system.repository.po.SystemDepartmentDO;
import org.chdzq.system.repository.po.SystemRoleDO;
import org.chdzq.system.repository.po.SystemUserDO;
import org.chdzq.system.service.DepartmentQueryService;
import org.chdzq.system.service.ResourceQueryService;
import org.chdzq.system.service.RoleQueryService;
import org.chdzq.system.service.UserQueryService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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

    private final DepartmentQueryService departmentQueryService;

    @Override
    public AuthInfo getAuthInfo(QueryAuthInfo param) {
        String username = param.getUsername();
        if (!StringUtils.hasText(username)) {
            return null;
        }
        return baseMapper.selectUserAuthInfo(username);
    }

    @Override
    public Page<? extends UserPageVO> page(UserPageQuery param) {
        Page<SystemUserDO> page = customPage(param, (p, q) -> baseMapper.queryPageList(p, q));
        Set<Long> deptIdSet = page.getList().stream().map(SystemUserDO::getDepartmentId).collect(Collectors.toSet());
        List<SystemDepartmentDO> departmentList = departmentQueryService.listByIds(deptIdSet);
        Map<Long, SystemDepartmentDO> departmentMap = departmentList.stream()
                .collect(Collectors.toMap(SystemDepartmentDO::getId,(a)->a));
        SystemInfraConvertor instance = SystemInfraConvertor.INSTANCE;
        return page.map((a)-> instance.userDo2UserPageVO(a, departmentMap.get(a.getDepartmentId())));
    }

    @Override
    public UserInfo getCurrentUserInfo() {
        Long userId = UserContext.getUserId();
        SystemUserDO userDO = getById(userId);

        Set<String> roleCodes = UserContext.getRoles();
        List<RoleVO> roles = roleQueryService.listByCodes(roleCodes);

        List<ResourceTreeVO> resources;
        if (Objects.nonNull(roleCodes) && roleCodes.contains(Constant.ROOT_ROLE_CODE)) {
            resources = resourceQueryService.tree();
        } else {
            List<Long> roleIds = roles.stream().map(RoleVO::getId).toList();
            resources = resourceQueryService.listByRoleIds(roleIds);
        }

        return convertor.userDo2UserInfo(userDO, roles, resources);
    }

    @Override
    public UserVO detail(Long userId) {
        if (Objects.isNull(userId)) {
            return null;
        }
        SystemUserDO userDO = getById(userId);
        Assert.notNull(userDO, "查询的用户不存在");
        SystemDepartmentDO department = departmentQueryService.getById(userDO.getDepartmentId());
        List<SystemRoleDO> roleDOList = roleQueryService.listByUserId(userDO.getId());
        List<Long> roleIds = roleDOList.stream().map(SystemRoleDO::getId).toList();
        return SystemInfraConvertor.INSTANCE.userDo2UserVO(userDO, department, roleIds);
    }

    @Override
    public UserProfileVO getUserProfile(Long userId) {
        if (Objects.isNull(userId)) {
            return null;
        }
        SystemUserDO userDO = getById(userId);
        Assert.notNull(userDO, "查询的用户不存在");
        SystemDepartmentDO department = departmentQueryService.getById(userDO.getDepartmentId());
        List<SystemRoleDO> roleDOList = roleQueryService.listByUserId(userDO.getId());
        List<String> roleNames = roleDOList.stream().map(SystemRoleDO::getName).distinct().toList();
        return SystemInfraConvertor.INSTANCE.userDo2UserProfileVO(userDO, department, roleNames);
    }
}
