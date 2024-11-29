package org.chdzq.authentication.service;

import lombok.AllArgsConstructor;
import org.chdzq.authentication.model.SysUserDetail;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.system.entity.UserAuthInfo;
import org.chdzq.system.fegin.UserFeignClient;
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

    private final UserFeignClient userFeignClient;

    /**
     * 根据用户名获取用户信息(用户名、密码和角色权限)
     * 用户名、密码用于后续认证，认证成功之后将权限授予用户
     *
     * @param username 用户名
     * @return {@link  SysUserDetail}
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        UserAuthInfo userAuthInfo = userFeignClient.getUserAuthInfo(username);

        Assert.isTrue(userAuthInfo != null, "用户不存在");

        if (!StatusEnum.ENABLE.getValue().equals(userAuthInfo.getStatus())) {
            throw new DisabledException("该账户已被禁用!");
        }

        return new SysUserDetail(userAuthInfo);
    }
}
