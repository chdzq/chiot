package org.chdzq.authentication.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户和角色关联表
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 00:32
 */
@TableName("sys_user_role")
@Data
public class SystemUserRole {

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.NONE)
    private Long userId;

    /**
     * 角色ID
     */
    @TableId(value = "role_id", type = IdType.NONE)
    private Long roleId;

}
