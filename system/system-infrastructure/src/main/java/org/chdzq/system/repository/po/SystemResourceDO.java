package org.chdzq.system.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.chdzq.common.mybatis.domain.BaseDO;

/**
 * 资源
 *
 * @author chdzq
 * @version 1.0
 * @date 2024/11/28 00:20
 */
@TableName("sys_resource")
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemResourceDO extends BaseDO {
    /**
     * 菜单ID
     */
    @TableId(type = IdType.ASSIGN_ID)
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
     * 权限标识
     */
    private String permission;

    /**
     * 显示状态(1:显示;0:隐藏)
     */
    private Integer enabled;

    /**
     * 排序
     */
    private Integer sort;

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
