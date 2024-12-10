package org.chdzq.authentication.oauth2.oidc;

import lombok.extern.slf4j.Slf4j;
import org.chdzq.authentication.entity.User;
import org.chdzq.authentication.query.QueryAuthInfo;
import org.chdzq.authentication.service.UserService;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;

/**
 * 自定义 OIDC 用户信息服务
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/29 14:58
 */
@Slf4j
@Service
public class CustomOidcUserInfoService {

    private final UserService userService;

    public CustomOidcUserInfoService(UserService userService) {
        this.userService = userService;
    }

    public OidcUserInfo loadUserByUsername(String username) {
        User user = null;
        try {
            user = userService.get(new QueryAuthInfo(username));
            if (user == null) {
                return null;
            }
            return createOidcUserInfoBy(user);
        } catch (Exception e) {
            log.error("获取用户信息失败", e);
            return null;
        }
    }

    private OidcUserInfo createOidcUserInfoBy(User user) {
        return OidcUserInfo.builder()
                .claim(CustomClaimNames.USER_NAME, user.getUsername())
                .claim(CustomClaimNames.STATUS, user.getStatus())
                .subject(user.getUsername())
                .nickname(user.getNickname())
                .phoneNumber(user.getMobile())
                .email(user.getEmail())
                .profile(user.getAvatar())
                .build();
    }

}
