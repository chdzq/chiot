package org.chdzq.system.query.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.chdzq.common.core.tree.TreeInterface;

import java.util.List;

/**
 * 部门树
 *
 * @author chdzq
 * @create 2025/2/18 18:02
 */
@Data
public class DepartmentTreeVO implements TreeInterface<Long, DepartmentTreeVO> {
    /**
     * 主键
     */
    private Long id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门编码
     */
    private String code;

    /**
     * 父节点id
     */
    @JsonIgnore
    private Long parentId;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 状态(1:正常;0:禁用)
     */
    private Integer status;

    /**
     * 子节点列表
     */
    private List<DepartmentTreeVO> children;


    @Override
    public Long _getTreeKey() {
        return id;
    }

    @Override
    public Long _getTreeParentKey() {
        return parentId;
    }

    @Override
    public void _setTreeChildren(List<DepartmentTreeVO> list) {
        children = list;
    }

    @Override
    public List<DepartmentTreeVO> _getTreeChildren() {
        return children;
    }
}
