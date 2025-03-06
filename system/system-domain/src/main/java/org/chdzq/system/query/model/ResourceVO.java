package org.chdzq.system.query.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Objects;

/**
 * 资源展示类
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/4 18:51
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResourceVO {

    /**
     * 菜单ID
     */
    private Long id;

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
     * 是否授权(1:已授权;0:未授权)
     */
    private Integer authorized;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 跳转路径
     */
    private String link;

    /**
     * 【目录】只有一个子路由是否始终显示(1:是 0:否)
     */
    private Integer alwaysShow;

    /**
     * 是否隐藏(1-是 0-否)
     */
    private Integer hidden;

    /**
     * 【菜单】是否开启页面缓存(1:是 0:否)
     */
    private Integer keepAlive;
}
