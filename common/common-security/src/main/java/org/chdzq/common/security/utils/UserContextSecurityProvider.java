package org.chdzq.common.security.utils;

import org.chdzq.common.core.constants.JwtClaimConstant;
import org.chdzq.common.core.constants.SystemConstant;
import org.chdzq.common.core.enums.DataScopeEnum;
import org.chdzq.common.core.utils.UserContextProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Spring Security 工具类
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/27 15:12
 */
@ConditionalOnClass(AuthenticationConfiguration.class)
@Component
public class UserContextSecurityProvider implements UserContextProvider {


    @Override
    public Long getUserId() {
        Map<String, Object> tokenAttributes = getTokenAttributes();
        return getLong(tokenAttributes.get(JwtClaimConstant.USER_ID));
    }

    @Override
    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return authentication.getName();
        }
        return null;
    }

    private Map<String, Object> getTokenAttributes() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken jwtAuthenticationToken) {
            return jwtAuthenticationToken.getTokenAttributes();
        }
        return new HashMap<>();
    }


    /**
     * 获取用户角色集合
     */
    @Override
    public Set<String> getRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return AuthorityUtils.authorityListToSet(authentication.getAuthorities())
                    .stream()
                    .collect(Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet));
        }
        return null;
    }

    /**
     * 获取部门ID
     */
    @Override
    public Long getDeptId() {
        Map<String, Object> tokenAttributes = getTokenAttributes();
        return getLong(tokenAttributes.get(JwtClaimConstant.DEPT_ID));
    }

    @Override
    public Boolean isRoot() {
        Set<String> roles = getRoles();
        return roles != null && roles.contains(SystemConstant.ROOT_ROLE_CODE);
    }

    private String getJti() {
        Map<String, Object> tokenAttributes = getTokenAttributes();
        if (tokenAttributes != null) {
            return String.valueOf(tokenAttributes.get(JwtClaimConstant.JTI));
        }
        return null;
    }


    @Override
    public Long getExp() {
        Map<String, Object> tokenAttributes = getTokenAttributes();
        return getLong(tokenAttributes.get(JwtClaimConstant.EXP));
    }

    /**
     * 获取数据权限范围
     *
     * @return 数据权限范围
     * @see DataScopeEnum
     */
    @Override
    public DataScopeEnum getDataScope() {
        Map<String, Object> tokenAttributes = getTokenAttributes();
        Integer scopeValue = getInteger(tokenAttributes.get(JwtClaimConstant.DATA_SCOPE));
        return DataScopeEnum.getByCode(scopeValue);
    }

    private Long getLong(Object obj) {
        if (Objects.isNull(obj)) {
            return null;
        }
        if (obj instanceof Long) {
            return (Long) obj;
        }
        if (obj instanceof String) {
            return Long.parseLong((String) obj);
        }
        return Long.parseLong(obj.toString());
    }

    private Integer getInteger(Object obj) {
        if (Objects.isNull(obj)) {
            return null;
        }
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        if (obj instanceof String) {
            return Integer.parseInt((String) obj);
        }
        return Integer.parseInt(obj.toString());
    }
}
