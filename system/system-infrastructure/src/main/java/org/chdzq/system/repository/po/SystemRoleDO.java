package org.chdzq.system.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.chdzq.common.mybatis.domain.BaseDO;

/**
 * 角色
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 00:16
 */
@TableName("sys_role")
@EqualsAndHashCode(callSuper = true)
@Data
public class SystemRoleDO extends BaseDO {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色编码
     */
    private String code;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 角色状态(1-正常；0-停用)
     */
    private Integer status;
}
