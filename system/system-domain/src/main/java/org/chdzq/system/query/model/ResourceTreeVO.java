package org.chdzq.system.query.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.chdzq.common.core.tree.TreeInterface;

import java.util.List;

/**
 * 资源展示类
 *
 * @author chdzq
 * @version 1.0
 * @date 2025/1/2 14:41
 */
@Data
public class ResourceTreeVO implements TreeInterface<Long, ResourceTreeVO> {
    /**
     * 菜单ID
     */
    private Long id;

    /**
     * 父菜单ID
     */
    @JsonIgnore
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
     * 子节点列表
     */
    private List<ResourceTreeVO> children;

    @Override
    public Long _getTreeKey() {
        return id;
    }

    @Override
    public Long _getTreeParentKey() {
        return parentId;
    }

    @Override
    public void _setTreeChildren(List<ResourceTreeVO> list) {
        children = list;
    }

    @Override
    public List<ResourceTreeVO> _getTreeChildren() {
        return children;
    }
}
