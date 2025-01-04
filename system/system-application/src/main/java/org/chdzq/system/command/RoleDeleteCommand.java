package org.chdzq.system.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;
import org.chdzq.common.core.ddd.ICommand;
import org.chdzq.common.core.utils.Assert;
import org.chdzq.common.core.utils.ValidationUtil;
import org.chdzq.system.entity.Role;
import org.chdzq.system.repository.RoleRepository;

/**
 * 删除角色命令
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 16:56
 */
@Value
public class RoleDeleteCommand implements ICommand<Role, Long> {
    /**
     * 用户Id
     */
    Long id;

    public RoleDeleteCommand(
            @NotBlank(message = "主键不能为空") Long id)
    {
        this.id = id;
    }

    /**
     * 校验
     * @param roleRepository 仓库
     */
    public void validate(RoleRepository roleRepository) {
        ValidationUtil.validate(this);
        Boolean exist = roleRepository.isExistByKey(id);
        Assert.isTrue(exist, "角色不存在");
    }

    @Override
    public Role buildEntity() {
        return new Role(id);
    }
}
