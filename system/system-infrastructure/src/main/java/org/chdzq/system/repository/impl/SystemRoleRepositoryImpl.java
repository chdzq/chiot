package org.chdzq.system.repository.impl;

import lombok.AllArgsConstructor;
import org.chdzq.common.core.constants.RedisConstant;
import org.chdzq.common.core.enums.ResourceEnum;
import org.chdzq.common.core.utils.Assert;
import org.chdzq.common.mybatis.core.query.LambdaQueryWrapperX;
import org.chdzq.common.mybatis.core.query.WrapperX;
import org.chdzq.common.mybatis.core.service.ServiceImplX;
import org.chdzq.system.convert.SystemInfraConvertor;
import org.chdzq.system.entity.Resource;
import org.chdzq.system.entity.Role;
import org.chdzq.system.repository.RoleRepository;
import org.chdzq.system.repository.SystemRoleResourceService;
import org.chdzq.system.repository.dao.SystemRoleMapper;
import org.chdzq.system.repository.po.SystemRoleDO;
import org.chdzq.system.repository.po.SystemRoleResourceDO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 角色资源类
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 16:16
 */
@Repository
@AllArgsConstructor
public class SystemRoleRepositoryImpl extends ServiceImplX<SystemRoleMapper, SystemRoleDO> implements RoleRepository {

    private SystemRoleResourceService roleResourceService;

    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public Boolean isExistByKey(Long id) {
        Serializable key = baseMapper.isExistedByKey(
                WrapperX.<SystemRoleDO>lambdaQuery()
                        .eq(SystemRoleDO::getId, id)
        );
        return Objects.nonNull(key);
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
    @Transactional(rollbackFor = Exception.class)
    public void create(Role entity) {
        Assert.isNull(entity.getId(), "创建时主键需要为空");
        SystemRoleDO po = SystemInfraConvertor.INSTANCE.role2RoleDO(entity);
        save(po);
        doAuthorization(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Role entity) {
        Assert.notNull(entity.getId(), "更新时主键不能为空");
        SystemRoleDO po = SystemInfraConvertor.INSTANCE.role2RoleDO(entity);
        updateById(po);
        doAuthorization(entity);
    }

    /**
     * 处理授权逻辑
     * @param role
     */
    private void doAuthorization(Role role) {
        if (Objects.isNull(role.getResource())) {
            return;
        }
        roleResourceService.authorizeRoleResources(role.getId(), role.getResource());
        doSaveResourcesToRedis(role);
    }

    /**
     * 保存角色的授权的按钮权限到redis
     * @param role 角色
     */
    public void doSaveResourcesToRedis(Role role) {
        SystemRoleDO po = getById(role.getId());

        // 清理权限缓存
        String roleCode = po.getCode();
        redisTemplate.opsForHash().delete(RedisConstant.ROLE_PERMS_PREFIX, roleCode);

        Set<String> permissions = role.getResource()
                .stream()
                .filter((r) -> Objects.equals(r.getType(), ResourceEnum.BUTTON))
                .map(Resource::getPermission)
                .collect(Collectors.toSet());

        if (CollectionUtils.isEmpty(permissions)) {
            return;
        }
        redisTemplate.opsForHash().put(RedisConstant.ROLE_PERMS_PREFIX, roleCode, permissions);
    }

    @Override
    public void delete(Role entity) {
        if (Objects.isNull(entity.getId())) {
            return;
        }
        removeById(entity.getId());
    }
}
