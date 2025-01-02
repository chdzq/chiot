package org.chdzq.system.query.model;

import lombok.Value;

/**
 * 角色展示类
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 16:00
 */
@Value
public class RoleVO {

    /**
     * 主键
     */
    Long id;

    /**
     * 角色名称
     */
    String name;

    /**
     * 角色编码
     */
    String code;

    /**
     * 显示顺序
     */
    Integer sort;

    /**
     * 角色状态(1-正常；0-停用)
     */
    Integer status;

}
