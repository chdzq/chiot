package org.chdzq.system.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.chdzq.common.core.ddd.ICommand;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.common.core.utils.Assert;
import org.chdzq.common.core.utils.ValidationUtil;
import org.chdzq.common.core.validation.InEnum;
import org.chdzq.system.entity.Role;
import org.chdzq.system.repository.RoleRepository;

/**
 * 创建角色
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 15:55
 */
@Data
public class CreateRoleCommand implements ICommand<Role, Long> {

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String name;

    /**
     * 角色编码
     */
    @NotBlank(message = "角色编码不能为空")
    private String code;

    /**
     * 显示顺序
     */
    private Integer sort = 1;

    /**
     * 角色状态(1-正常；0-停用)
     */
    @InEnum(StatusEnum.class)
    private Integer status = StatusEnum.ENABLE.getCode();

    /**
     * 校验
     * @param roleRepository 仓库
     */
    public void validate(RoleRepository roleRepository) {
        ValidationUtil.validate(this);
        Long idByCode = roleRepository.getIdByCode(this.getCode());
        Assert.isNull(idByCode, "角色编码已存在");

        Long idByName = roleRepository.getIdByName(this.getName());
        Assert.isNull(idByName, "角色名称已存在");
    }

    @Override
    public Role buildEntity() {
        Role role = new Role();
        role.setName(name);
        role.setCode(code);
        role.setSort(sort);
        role.setStatus(StatusEnum.getByCode(status));
        return role;
    }
}
