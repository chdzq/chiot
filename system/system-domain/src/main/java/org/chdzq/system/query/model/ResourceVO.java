package org.chdzq.system.query.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Value;

/**
 * 资源展示类
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/4 18:51
 */
@Value
@Builder
public class ResourceVO {

    /**
     * 菜单ID
     */
    Long id;

    /**
     * 名称
     */
    String name;

    /**
     * 编码
     */
    String code;

    /**
     * 菜单类型(1-菜单；2-目录；3-外链；4-按钮权限)
     */
    Integer type;

    /**
     * 路由路径(浏览器地址栏路径)
     */
    String path;

    /**
     * 组件路径(vue页面完整路径，省略.vue后缀)
     */
    String component;

    /**
     * 是否授权(1:已授权;0:未授权)
     */
    Integer authorized;

}
