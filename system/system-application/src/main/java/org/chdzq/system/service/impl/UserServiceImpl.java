package org.chdzq.system.service.impl;

import org.chdzq.system.convert.SystemConvert;
import org.chdzq.system.entity.Role;
import org.chdzq.system.entity.User;
import org.chdzq.system.entity.UserAuthInfo;
import org.chdzq.system.query.QueryAuthInfo;
import org.chdzq.system.repository.UserRepository;
import org.chdzq.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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
    public UserAuthInfo query(QueryAuthInfo param) {
        User user = userRepository.findByUsername(param.getUsername());
        UserAuthInfo userAuthInfo = SystemConvert.INSTANCE.userConvert2AuthInfo(user);
        return userAuthInfo;
    }
}
