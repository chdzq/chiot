package org.chdzq.authentication.service.impl;

import org.chdzq.authentication.entity.SysUserDetail;
import org.chdzq.authentication.entity.User;
import org.chdzq.authentication.query.QueryAuthInfo;
import org.chdzq.authentication.repository.UserRepository;
import org.chdzq.authentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务 实现类
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 16:26
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User get(QueryAuthInfo param) {
        User user = userRepository.findByUsername(param.getUsername());
        return user;
    }
}
