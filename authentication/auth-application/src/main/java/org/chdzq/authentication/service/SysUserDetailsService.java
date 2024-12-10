package org.chdzq.authentication.service;

import lombok.AllArgsConstructor;
import org.chdzq.authentication.entity.SysUserDetail;
import org.chdzq.authentication.entity.User;
import org.chdzq.authentication.query.QueryAuthInfo;
import org.chdzq.common.core.enums.StatusEnum;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 系统用户信息加载实现类
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/29 17:06
 */
@Service
@AllArgsConstructor
public class SysUserDetailsService implements UserDetailsService {

    private final UserService userService;

    /**
     * 根据用户名获取用户信息(用户名、密码和角色权限)
     * 用户名、密码用于后续认证，认证成功之后将权限授予用户
     *
     * @param username 用户名
     * @return {@link  SysUserDetail}
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.get(new QueryAuthInfo(username));

        Assert.isTrue(user != null, "用户不存在");

        if (!StatusEnum.ENABLE.equals(user.getStatus())) {
            throw new DisabledException("该账户已被禁用!");
        }

        return SysUserDetail.newSysUserDetail(user);
    }
}
