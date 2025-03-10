package org.chdzq.system.repository.impl;

import lombok.AllArgsConstructor;
import org.chdzq.common.core.constants.Constant;
import org.chdzq.common.mybatis.core.query.WrapperX;
import org.chdzq.system.convert.SystemInfraConvertor;
import org.chdzq.system.entity.AuthInfo;
import org.chdzq.system.entity.User;
import org.chdzq.system.repository.SystemUserRoleRepository;
import org.chdzq.system.repository.UserRepository;
import org.chdzq.system.repository.dao.SystemUserMapper;
import org.chdzq.system.repository.po.SystemUserDO;
import org.chdzq.common.mybatis.core.service.ServiceImplX;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 用户存储实现类
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 02:36
 */
@Repository
@AllArgsConstructor
public class SystemUserRepositoryImpl extends ServiceImplX<SystemUserMapper, SystemUserDO> implements UserRepository {

    @Override
    public User get(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        return baseMapper.selectEntityBy(WrapperX.<SystemUserDO>lambdaQuery().eq(SystemUserDO::getId, id));
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(User entity) {
        Assert.isNull(entity.getId(), "创建时主键需要为空");
        SystemUserDO user = SystemInfraConvertor.INSTANCE.user2UserDo(entity);
        if (Objects.isNull(user)) {
            return;
        }
        save(user);
        doAuthorizeRoles(entity);
    }

    private void doAuthorizeRoles(User user) {
        if (Objects.isNull(user.getRoles())) {
            return;
        }
        userRoleRepository.authorizeUserRoles(user.getId(), user.getRoles());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(User entity) {
        Assert.notNull(entity.getId(), "更新时主键不能为空");

        SystemUserDO user = SystemInfraConvertor.INSTANCE.user2UserDo(entity);
        if (Objects.isNull(user)) {
            return;
        }

        updateById(user);
        doAuthorizeRoles(entity);
    }

    @Override
    public void delete(User user) {
        if (Objects.isNull(user.getId())) {
            return;
        }
        removeById(user.getId());
    }

    @Override
    public AuthInfo getAuthInfoByUsername(String username) {
        if (!StringUtils.hasText(username)) {
            return null;
        }
        AuthInfo user = baseMapper.selectUserAuthInfo(username);
        return user;
    }

    @Override
    public Boolean isExistByKey(Long id) {
        SystemUserDO o = getById(id);
        return Objects.nonNull(o);
    }

    @Override
    public Boolean isUsernameAvailable(String username) {
        long count = count(SystemUserDO::getUsername, username);
        return count <= 0;
    }

    @Override
    public Boolean isUsernameAvailable(Long id, String username) {
        SystemUserDO one = getById(id);
        if(Objects.equals(one.getUsername(), username)) {
            return Boolean.TRUE;
        }
        return !isUsernameAvailable(username);
    }

    @Override
    public User getEntityByUserName(String username) {
        if (!StringUtils.hasText(username)) {
            return null;
        }
        return baseMapper.selectEntityBy(
                WrapperX.<SystemUserDO>lambdaQuery()
                        .eq(SystemUserDO::getUsername, username)
                        .eq(SystemUserDO::getDeleted, Constant.UNDELETED)
        );
    }

    private SystemUserRoleRepository userRoleRepository;
}
