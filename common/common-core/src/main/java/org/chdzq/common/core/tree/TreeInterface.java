package org.chdzq.common.core.tree;

import java.util.List;

/**
 * Tree的基本接口
 * @author chdzq
 * @create 2025/1/2
 */
public interface TreeInterface<K, T extends TreeInterface<K, T>> {

    /**
     * 获取code
     * @return
     */
    K _getTreeKey();

    /**
     * 获取父节点
     * @return
     */
    K _getTreeParentKey();


    /**
     * 设置children
     * @param list
     */
    void _setTreeChildren(List<T> list);

    /**
     * 获取children
     * @return
     */
    List<T> _getTreeChildren();
}
