package org.chdzq.authentication.repository.impl;

import org.chdzq.authentication.convert.AuthConvertor;
import org.chdzq.authentication.entity.User;
import org.chdzq.authentication.repository.po.SystemUserDO;
import org.chdzq.authentication.repository.UserRepository;
import org.chdzq.common.mybatis.core.service.ServiceImplX;

import org.chdzq.authentication.repository.dao.SystemUserMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 * 用户存储实现类
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 02:36
 */
@Repository
public class UserRepositoryImpl extends ServiceImplX<SystemUserMapper, SystemUserDO> implements UserRepository {
    @Override
    public void save(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public User findById(Long id) {
        SystemUserDO user = getById(id);
        return null;
    }

    @Override
    public User findByUsername(String username) {
        if (!StringUtils.hasText(username)) {
            return null;
        }
        SystemUserDO userDO = getOne(SystemUserDO::getUsername, username);
        if (userDO == null) {
            return null;
        }
        User user = AuthConvertor.INSTANCE.userDo2User(userDO);
        return user;
    }
}
