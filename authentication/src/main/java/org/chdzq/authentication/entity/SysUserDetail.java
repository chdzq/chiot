package org.chdzq.authentication.entity;

import lombok.Builder;
import lombok.Data;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.system.entity.AuthInfo;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 系统用户信息(包含用户名、密码和权限)
 * 用户名和密码用于认证，认证成功之后授予权限
 * @author chdzq
 * @version 1.0
 * @date 2024/11/29 17:09
 */
@Data
@Builder
public class SysUserDetail implements UserDetails, CredentialsContainer {
    /**
     * 扩展字段：用户ID
     */
    private Long userId;

    /**
     * 扩展字段：部门ID
     */
    private Long deptId;

    /**
     * 用户角色数据权限集合
     */
    private Integer dataScope;

    /**
     * 默认字段
     */
    private String username;
    private String password;
    private Boolean enabled;
    private Set<? extends GrantedAuthority> authorities;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked = true;

    private boolean credentialsNonExpired = true;

    private Set<String> permissions;

    public SysUserDetail(Long userId, Long deptId, Integer dataScope, String username, String password, Boolean enabled, Set<? extends GrantedAuthority> authorities, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, Set<String> permissions) {
        this.userId = userId;
        this.deptId = deptId;
        this.dataScope = dataScope;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.permissions = permissions;
    }

    /**
     * 系统管理用户
     */
    public static SysUserDetail newSysUserDetail(AuthInfo user) {
        Set<GrantedAuthority> authorities;
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            authorities = user.getRoles().stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
        } else {
            authorities = null;
        }

        SysUserDetailBuilder builder = SysUserDetail.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .password("{bcrypt}" + user.getPassword())
                .authorities(authorities)
                .enabled(Objects.equals(StatusEnum.ENABLE, user.getStatus()))
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .accountNonExpired(true);
        if (Objects.nonNull(user.getDataScope())) {
            builder.dataScope(user.getDataScope().getValue());
        }
        return builder.build();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }


    @Override
    public void eraseCredentials() {
        this.password = null;
    }
}
