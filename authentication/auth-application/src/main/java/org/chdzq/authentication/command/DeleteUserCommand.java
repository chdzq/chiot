package org.chdzq.authentication.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Value;
import org.chdzq.common.core.ddd.ICommand;

/**
 * 删除用户命令
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/12 20:04
 */
@Data
public class DeleteUserCommand implements ICommand {

    /**
     * 用户Id
     */
    @NotBlank(message = "主键不能为空")
    private Long id;

    public DeleteUserCommand(Long id) {
        this.id = id;
    }
}
