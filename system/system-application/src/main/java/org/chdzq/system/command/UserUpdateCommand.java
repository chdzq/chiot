package org.chdzq.system.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.chdzq.common.core.utils.Assert;
import org.chdzq.common.core.utils.ValidationUtil;
import org.chdzq.system.entity.Department;
import org.chdzq.system.entity.Role;
import org.chdzq.system.entity.User;
import org.chdzq.common.core.ddd.ICommand;
import org.chdzq.common.core.enums.DataScopeEnum;
import org.chdzq.common.core.enums.GenderEnum;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.common.core.validation.InEnum;
import org.chdzq.common.core.vo.EmailNumber;
import org.chdzq.common.core.vo.PhoneNumber;
import org.chdzq.system.repository.DepartmentRepository;
import org.chdzq.system.repository.UserRepository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * 更新用户
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/12 13:36
 */
@Data
public class UserUpdateCommand implements ICommand<User, Long> {

    /**
     * 用户Id
     */
    @NotNull(message = "主键不能为空")
    private Long id;

    /**
     * 账户名
     * 必填
     */
    private String username;

    /**
     * 昵称
     * 必填
     */
    private String nickname;

    /**
     * 部门ID
     */
    private Long departmentId;

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

    /**
     * 校验
     * @param userRepository 仓库
     */
    public void validate(UserRepository userRepository, DepartmentRepository departmentRepository) {
        ValidationUtil.validate(this);

        User user = userRepository.get(id);
        Assert.notNull(user, "用户不存在");

        //查询是否存在当前用户
        if (StringUtils.hasText(username) && !Objects.equals(username, user.getUsername())) {
            User user2 = userRepository.getEntityByUserName(username);
            Assert.isTrue(Objects.isNull(user2) || !Objects.equals(id, user2.getId()), "用户名不可用");
        }

        //判断部门是否存在
        if (Objects.nonNull(departmentId)) {
            Assert.notNull(departmentRepository.get(departmentId), "部门不存在");
        }
    }

    @Override
    public User buildEntity() {

        User entity = new User();
        entity.setId(id);
        entity.setUsername(username);
        entity.setNickname(nickname);
        if (StringUtils.hasText(mobile)) {
            entity.setMobile(PhoneNumber.make(mobile));
        }

        if (StringUtils.hasText(email)) {
            entity.setEmail(EmailNumber.make(email));
        }

        if (Objects.isNull(status)) {
            entity.setStatus(StatusEnum.ENABLE);
        } else {
            entity.setStatus(StatusEnum.getByCode(status));
        }

        entity.setGender(GenderEnum.getByCode(gender));

        if (Objects.isNull(dataScope)) {
            entity.setDataScope(DataScopeEnum.SELF);
        } else {
            entity.setDataScope(DataScopeEnum.getByCode(dataScope));
        }

        if (!CollectionUtils.isEmpty(roles)) {
            List<Role> list = roles.stream().map(Role::new).toList();
            entity.setRoles(list);
        }

        if (Objects.nonNull(departmentId)) {
            Department department = new Department();
            department.setId(departmentId);
            entity.setDepartment(department);
        }
        return entity;
    }
}
