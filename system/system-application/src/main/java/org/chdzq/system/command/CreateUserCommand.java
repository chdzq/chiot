package org.chdzq.system.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.chdzq.system.entity.Role;
import org.chdzq.system.entity.User;
import org.chdzq.common.core.ddd.ICommand;
import org.chdzq.common.core.enums.DataScopeEnum;
import org.chdzq.common.core.enums.GenderEnum;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.common.core.validation.InEnum;
import org.chdzq.common.core.vo.EmailNumber;
import org.chdzq.common.core.vo.PhoneNumber;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * 创建用户
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/12 10:40
 */
@Data
public class CreateUserCommand implements ICommand {
    /**
     * 账户名
     * 必填
     */
    @NotBlank(message = "账户名不能为空")
    private String username;

    /**
     * 昵称
     * 必填
     */
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    /**
     * email
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态: 1->启用;0->禁用
     * 默认启用
     */
    @InEnum(value = StatusEnum.class, message = "状态类型错误")
    private Integer status;

    /**
     * 性别(1->男；2->女)
     * 必填
     */
    @NotNull(message = "性别不能为空")
    @InEnum(value = GenderEnum.class, message = "性别类型错误")
    private Integer gender;


    /**
     * 角色
     */
    private List<Long> roles;

    /**
     * 数据权限
     * 默认为全部
     */
    private Integer dataScope;


    public User toEntity() {
        User obj = new User();
        obj.setUsername(username);
        obj.setNickname(nickname);
        if (StringUtils.hasText(mobile)) {
            obj.setMobile(new PhoneNumber(mobile));
        }

        if (StringUtils.hasText(email)) {
            obj.setEmail(new EmailNumber(email));
        }

        if (Objects.isNull(status)) {
            obj.setStatus(StatusEnum.ENABLE);
        } else {
            obj.setStatus(StatusEnum.getByCode(status));
        }

        obj.setGender(GenderEnum.getByCode(gender));

        if (Objects.isNull(dataScope)) {
            obj.setDataScope(DataScopeEnum.SELF);
        } else {
            obj.setDataScope(DataScopeEnum.getByCode(dataScope));
        }

        if (!CollectionUtils.isEmpty(roles)) {
            List<Role> list = roles.stream().map((a) -> {
                Role role = new Role();
                role.setId(a);
                return role;
            }).toList();
            obj.setRoles(list);
        }

        return obj;
    }
}
