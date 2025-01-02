package org.chdzq.system.command;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.chdzq.common.core.enums.StatusEnum;

/**
 * 更新角色
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 15:55
 */
@Data
public class UpdateRoleCommand {
    /**
     * 主键
     */
    @NotBlank(message = "角色Id不能为空")
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
    private StatusEnum status = StatusEnum.ENABLE;
}
