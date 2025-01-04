package org.chdzq.system.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 角色资源关联表
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 00:35
 */
@TableName("sys_role_resource")
@Data
public class SystemRoleResourceDO {

    /**
     * 角色ID
     */
    @TableId(value = "role_id", type = IdType.NONE)
    private Long roleId;

    /**
     * 资源ID
     */
    private Long resourceId;
}
