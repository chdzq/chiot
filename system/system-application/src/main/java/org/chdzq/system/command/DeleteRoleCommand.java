package org.chdzq.system.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Value;

/**
 * 删除角色命令
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 16:56
 */
@Value
public class DeleteRoleCommand {
    /**
     * 用户Id
     */
    Long id;

    public DeleteRoleCommand(
            @NotBlank(message = "主键不能为空") Long id)
    {
        this.id = id;
    }
}
