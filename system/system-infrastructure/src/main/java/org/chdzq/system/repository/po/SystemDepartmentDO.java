package org.chdzq.system.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.chdzq.common.mybatis.domain.BaseDO;

/**
 * 部门
 *
 * @author chdzq
 * @create 2025/2/18 17:58
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("sys_department")
public class SystemDepartmentDO extends BaseDO {
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门编号
     */
    private String code;

    /**
     * 父节点id
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 状态(1:正常;0:禁用)
     */
    private Integer status;
}
