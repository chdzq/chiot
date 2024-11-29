package org.chdzq.authentication.oauth2.oidc;

import lombok.extern.slf4j.Slf4j;
import org.chdzq.system.entity.UserAuthInfo;
import org.chdzq.system.fegin.UserFeignClient;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    private final UserFeignClient userFeignClient;

    public CustomOidcUserInfoService(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    public OidcUserInfo loadUserByUsername(String username) {
        UserAuthInfo userAuthInfo = null;
        try {
            userAuthInfo = userFeignClient.getUserAuthInfo(username);
            if (userAuthInfo == null) {
                return null;
            }
            return createOidcUserInfoBy(userAuthInfo);
        } catch (Exception e) {
            log.error("获取用户信息失败", e);
            return null;
        }
    }

    private OidcUserInfo createOidcUserInfoBy(UserAuthInfo authInfo) {
        return OidcUserInfo.builder()
                .claim(CustomClaimNames.USER_NAME, authInfo.getUsername())
                .claim(CustomClaimNames.STATUS, authInfo.getStatus())
                .subject(authInfo.getUsername())
                .nickname(authInfo.getNickname())
                .phoneNumber(authInfo.getMobile())
                .email(authInfo.getEmail())
                .profile(authInfo.getAvatar())
                .build();
    }

}
