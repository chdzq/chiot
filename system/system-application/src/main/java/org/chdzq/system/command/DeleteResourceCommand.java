package org.chdzq.system.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Value;
import org.chdzq.common.core.ddd.ICommand;

/**
 * 删除资源
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/12/31 11:19
 */
@Value
public class DeleteResourceCommand implements ICommand {

    /**
     * 用户Id
     */
    Long id;

    public DeleteResourceCommand(
            @NotBlank(message = "主键不能为空") Long id)
    {
        this.id = id;
    }
}
