package org.chdzq.system.repository.impl;

import lombok.AllArgsConstructor;
import org.chdzq.system.convert.SystemConvertor;
import org.chdzq.system.entity.AuthInfo;
import org.chdzq.system.entity.User;
import org.chdzq.system.repository.SystemUserRoleRepository;
import org.chdzq.system.repository.UserRepository;
import org.chdzq.system.repository.dao.SystemUserMapper;
import org.chdzq.system.repository.po.SystemUserDO;
import org.chdzq.common.mybatis.core.service.ServiceImplX;
import org.springframework.stereotype.Repository;
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
public class UserRepositoryImpl extends ServiceImplX<SystemUserMapper, SystemUserDO> implements UserRepository {

    @Override
    public void create(User entity) {
        Assert.isNull(entity.getId(), "创建时主键需要为空");
        SystemUserDO user = SystemConvertor.INSTANCE.user2UserDo(entity);
        if (Objects.isNull(user)) {
            return;
        }
        save(user);
        userRoleRepository.saveUserRelationship(user.getId(), entity.getRoles());
    }

    @Override
    public void update(User entity) {
        Assert.notNull(entity.getId(), "更新时主键不能为空");

        SystemUserDO user = SystemConvertor.INSTANCE.user2UserDo(entity);
        if (Objects.isNull(user)) {
            return;
        }

        updateById(user);
        userRoleRepository.saveUserRelationship(user.getId(), entity.getRoles());
    }

    @Override
    public void deleteById(Long id) {
        if (Objects.isNull(id)) {
            return;
        }
        removeById(id);
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

    private SystemUserRoleRepository userRoleRepository;
}
