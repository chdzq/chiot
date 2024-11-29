package org.chdzq.authentication.model;

import lombok.Data;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.system.entity.UserAuthInfo;
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
    private Collection<GrantedAuthority> authorities;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private Set<String> permissions;

    /**
     * 系统管理用户
     */
    public SysUserDetail(UserAuthInfo user) {
        this.setUserId(user.getUserId());
        this.setUsername(user.getUsername());
        this.setDeptId(user.getDeptId());
        this.setDataScope(user.getDataScope());
        this.setPassword("{bcrypt}" + user.getPassword());
        this.setEnabled(Objects.equals(StatusEnum.ENABLE.getValue(), user.getStatus()));
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            authorities = user.getRoles().stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
        }
        this.setPermissions(user.getPermissions());
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
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
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
