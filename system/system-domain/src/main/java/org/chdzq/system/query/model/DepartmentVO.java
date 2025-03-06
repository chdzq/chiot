package org.chdzq.system.query.model;

import lombok.Value;

/**
 * 部门展示列表
 *
 * @author chdzq
 * @create 2025/2/27 21:24
 */
@Value
public class DepartmentVO {
    /**
     * 主键
     */
    private Long id;

    /**
     * 父节点id
     */
    private Long parentId;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门编码
     */
    private String code;

    /**
     * 状态(1:正常;0:禁用)
     */
    private Integer status;

}
