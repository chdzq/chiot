package org.chdzq.system.service.impl;

import lombok.AllArgsConstructor;
import org.chdzq.system.adapter.PasswordCoder;
import org.chdzq.system.command.UserCreateCommand;
import org.chdzq.system.command.UserDeleteCommand;
import org.chdzq.system.command.UserUpdateCommand;
import org.chdzq.system.entity.AuthInfo;
import org.chdzq.system.entity.Password;
import org.chdzq.system.entity.User;
import org.chdzq.system.query.QueryAuthInfo;
import org.chdzq.system.repository.UserRepository;
import org.chdzq.system.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

/**
 * 用户服务 实现类
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 16:26
 */
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private PasswordCoder passwordService;

    private UserRepository userRepository;

    @Override
    public AuthInfo getAuthInfo(QueryAuthInfo param) {
        AuthInfo user = userRepository.getAuthInfoByUsername(param.getUsername());
        return user;
    }

    final private static String DEFAULT_PASSWORD = "password";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(@Validated UserCreateCommand cmd) {
        cmd.validate(userRepository);
        User user = cmd.buildEntity();

        // 设置默认加密密码
        String password = DEFAULT_PASSWORD;
        user.setPassword(new Password(password, passwordService));

        userRepository.create(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(@Validated UserUpdateCommand cmd) {
        cmd.validate(userRepository);
        User user = cmd.buildEntity();
        userRepository.update(user);
    }

    @Override
    public void delete(UserDeleteCommand cmd) {
        cmd.validate(userRepository);
        userRepository.delete(cmd.buildEntity());
    }
}
