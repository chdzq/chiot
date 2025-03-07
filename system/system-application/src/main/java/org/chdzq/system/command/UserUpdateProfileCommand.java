package org.chdzq.system.command;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.chdzq.common.core.ddd.ICommand;
import org.chdzq.common.core.enums.DataScopeEnum;
import org.chdzq.common.core.enums.GenderEnum;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.common.core.utils.Assert;
import org.chdzq.common.core.utils.ValidationUtil;
import org.chdzq.common.core.validation.InEnum;
import org.chdzq.common.core.vo.EmailNumber;
import org.chdzq.common.core.vo.PhoneNumber;
import org.chdzq.system.entity.Department;
import org.chdzq.system.entity.Role;
import org.chdzq.system.entity.User;
import org.chdzq.system.repository.DepartmentRepository;
import org.chdzq.system.repository.UserRepository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * 更新用户简介
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/12 13:36
 */
@Data
public class UserUpdateProfileCommand implements ICommand<User, Long> {

    /**
     * 用户Id
     */
    @NotNull(message = "主键不能为空")
    private Long id;

    /**
     * 昵称
     * 必填
     */
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
     * 性别(1->男；2->女)
     * 必填
     */
    @InEnum(value = GenderEnum.class, message = "性别类型错误")
    private Integer gender;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 校验
     * @param userRepository 仓库
     */
    public void validate(UserRepository userRepository, DepartmentRepository departmentRepository) {
        ValidationUtil.validate(this);

        User user = userRepository.get(id);
        Assert.notNull(user, "用户不存在");
    }

    @Override
    public User buildEntity() {

        User entity = new User();
        entity.setId(id);
        entity.setNickname(nickname);
        if (StringUtils.hasText(mobile)) {
            entity.setMobile(PhoneNumber.make(mobile));
        }

        if (StringUtils.hasText(email)) {
            entity.setEmail(EmailNumber.make(email));
        }

        entity.setGender(GenderEnum.getByCode(gender));
        entity.setAvatar(avatar);
        return entity;
    }
}
