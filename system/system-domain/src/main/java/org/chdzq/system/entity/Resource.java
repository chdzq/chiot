package org.chdzq.system.entity;

import lombok.Builder;
import lombok.Data;
import org.chdzq.common.core.ddd.IBaseEntity;
import org.chdzq.common.core.enums.ResourceEnum;
import org.chdzq.common.core.enums.StatusEnum;

/**
 * 资源
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 01:36
 */
@Data
public class Resource implements IBaseEntity<Long> {
    /**
     * 菜单ID
     */
    private Long id;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * 菜单类型(1-菜单；2-目录；3-外链；4-按钮权限)
     */
    private ResourceEnum type;

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
    private StatusEnum enabled;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 菜单图标
     */
    private String icon;

    @Override
    public Long getIdentifier() {
        return id;
    }

    public Resource() {
    }

    public Resource(Long id) {
        this.id = id;
    }

    @Builder
    public Resource(Long id, Long parentId, String name, String code, Integer type, String path, String component, String permission, Integer enabled, Integer sort, String icon) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.code = code;
        this.type = ResourceEnum.getByCode(type);
        this.path = path;
        this.component = component;
        this.permission = permission;
        this.enabled = StatusEnum.getByCode(enabled);
        this.sort = sort;
        this.icon = icon;
    }
}
