package org.chdzq.system.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.chdzq.common.core.ddd.ICommand;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.common.core.utils.Assert;
import org.chdzq.common.core.utils.ValidationUtil;
import org.chdzq.common.core.validation.InEnum;
import org.chdzq.system.entity.Role;
import org.chdzq.system.repository.RoleRepository;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 更新角色
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 15:55
 */
@Data
public class RoleUpdateCommand implements ICommand<Role, Long> {
    /**
     * 主键
     */
    @NotNull(message = "角色Id不能为空")
    private Long id;

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

        Boolean exist = roleRepository.isExistByKey(id);
        Assert.isTrue(exist, "角色不存在");
        if (StringUtils.hasText(code)) {
            Long idByCode = roleRepository.getIdByCode(code);
            Assert.isTrue(Objects.isNull(idByCode) || Objects.equals(id, idByCode), "角色编码已存在");
        }

        if (StringUtils.hasText(name)) {
            Long idByName = roleRepository.getIdByName(name);
            Assert.isTrue(Objects.isNull(idByName) || Objects.equals(id, idByName), "角色名称已存在");
        }
    }

    @Override
    public Role buildEntity() {
        Role role = new Role();
        role.setId(id);
        role.setName(name);
        role.setCode(code);
        role.setSort(sort);
        role.setStatus(StatusEnum.getByCode(status));
        return role;
    }
}
