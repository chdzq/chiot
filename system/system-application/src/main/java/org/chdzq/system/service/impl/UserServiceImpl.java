package org.chdzq.system.service.impl;

import lombok.AllArgsConstructor;
import org.chdzq.system.entity.Password;
import org.chdzq.system.command.CreateUserCommand;
import org.chdzq.system.command.DeleteUserCommand;
import org.chdzq.system.command.UpdateUserCommand;
import org.chdzq.system.entity.AuthInfo;
import org.chdzq.system.entity.User;
import org.chdzq.system.query.QueryAuthInfo;
import org.chdzq.system.repository.UserRepository;
import org.chdzq.system.adapter.PasswordCoder;
import org.chdzq.system.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
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
    public void createUser(@Validated CreateUserCommand cmd) {
        User user = cmd.toEntity();

        //查询是否存在当前用户
        Boolean available = userRepository.isUsernameAvailable(user.getUsername());
        Assert.isTrue(available, "当前用户名已存在");

        // 设置默认加密密码
        String password = DEFAULT_PASSWORD;
        user.setPassword(new Password(password, passwordService));

        userRepository.save(user);
    }

    @Override
    public void updateUser(@Validated UpdateUserCommand cmd) {
        User user = cmd.toEntity();

        Boolean exist = userRepository.isExistByKey(user.getId());
        Assert.isTrue(exist, "用户不存在");

        //查询是否存在当前用户
        if (StringUtils.hasText(user.getUsername())) {
            Boolean available = userRepository.isUsernameAvailable(user.getId(), user.getUsername());
            Assert.isTrue(available, "用户名不可用");
        }

        userRepository.save(user);
    }

    @Override
    public void deleteUser(DeleteUserCommand cmd) {
        Long id = cmd.getId();
        Boolean exist = userRepository.isExistByKey(id);
        Assert.isTrue(exist, "用户不存在");
        userRepository.deleteById(id);
    }
}
