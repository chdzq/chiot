package org.chdzq.system.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.chdzq.common.core.ddd.IBaseEntity;
import org.chdzq.common.core.enums.DataScopeEnum;
import org.chdzq.common.core.enums.GenderEnum;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.common.core.vo.EmailNumber;
import org.chdzq.common.core.vo.PhoneNumber;

import java.util.List;

/**
 * 用户
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 01:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements IBaseEntity<Long> {
    /**
     * 用户id
     */
    private Long id;

    /**
     * 账户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private Password password;

    /**
     * 盐
     */
    private String salt;

    /**
     * email
     */
    private EmailNumber email;

    /**
     * 手机号
     */
    private PhoneNumber mobile;

    /**
     * 状态: 1->启用;0->禁用
     */
    private StatusEnum status;

    /**
     * 性别(1->男；2->女)
     */
    private GenderEnum gender;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 角色
     */
    private List<Role> roles;

    /**
     * 数据权限
     */
    private DataScopeEnum dataScope;

    @Override
    public Long getIdentifier() {
        return id;
    }

}
