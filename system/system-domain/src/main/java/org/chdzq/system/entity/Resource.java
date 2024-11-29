package org.chdzq.system.entity;

import lombok.Data;
import org.chdzq.common.core.enums.StatusEnum;
import org.chdzq.common.core.repository.IBaseEntity;

/**
 * 资源
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 01:36
 */
@Data
public class Resource implements IBaseEntity {
    /**
     * 菜单ID
     */
    private Long id;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单类型(1-菜单；2-目录；3-外链；4-按钮权限)
     */
    private Integer type;

    /**
     * 路由路径(浏览器地址栏路径)
     */
    private String path;

    /**
     * 组件路径(vue页面完整路径，省略.vue后缀)
     */
    private String component;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 显示状态(1:显示;0:隐藏)
     */
    private StatusEnum visible;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 菜单图标
     */
    private String icon;

    @Override
    public Long getIdentity() {
        return id;
    }
}
