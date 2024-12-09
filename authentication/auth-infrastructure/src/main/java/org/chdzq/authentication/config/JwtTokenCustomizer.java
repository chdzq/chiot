package org.chdzq.authentication.config;

import org.chdzq.authentication.model.SysUserDetail;
import org.chdzq.common.core.constants.JwtClaimConstant;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * JWT 自定义字段
 *@see <a href="https://docs.spring.io/spring-authorization-server/reference/guides/how-to-custom-claims-authorities.html">Add custom claims to JWT access tokens </a>
 * @author chdzq
 * @version 1.0
 * @date 2024/12/2 10:28
 */
@Component
public class JwtTokenCustomizer implements OAuth2TokenCustomizer<JwtEncodingContext> {
    @Override
    public void customize(JwtEncodingContext context) {
        if (OAuth2TokenType.ACCESS_TOKEN.equals(context.getTokenType()) && context.getPrincipal() instanceof UsernamePasswordAuthenticationToken) {
            // Customize headers/claims for access_token
            Optional.ofNullable(context.getPrincipal().getPrincipal()).ifPresent(principal -> {
                JwtClaimsSet.Builder claims = context.getClaims();
                if (principal instanceof SysUserDetail userDetails) { // 系统用户添加自定义字段

                    claims.claim(JwtClaimConstant.USER_ID, userDetails.getUserId());
                    claims.claim(JwtClaimConstant.USERNAME, userDetails.getUsername());
                    if (Objects.nonNull(userDetails.getDeptId())) {
                        claims.claim(JwtClaimConstant.DEPT_ID, userDetails.getDeptId());
                    }
                    if (Objects.nonNull(userDetails.getDataScope())) {
                        claims.claim(JwtClaimConstant.DATA_SCOPE, userDetails.getDataScope());
                    }

                    // 这里存入角色至JWT，解析JWT的角色用于鉴权的位置: ResourceServerConfig#jwtAuthenticationConverter
                    var authorities = AuthorityUtils.authorityListToSet(context.getPrincipal().getAuthorities())
                            .stream()
                            .collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
                    claims.claim(JwtClaimConstant.AUTHORITIES, authorities);

                }
            });
        }
    }
}
