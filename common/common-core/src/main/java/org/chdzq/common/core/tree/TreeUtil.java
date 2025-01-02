package org.chdzq.common.core.tree;

import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * 树形结构工具类
 * @author chdzq
 * @create 2025/1/2
 */
public class TreeUtil {

    private TreeUtil() {}

    private static <T> BinaryOperator<T> throwingMerger() {
        return (u,v) -> { throw new IllegalStateException(String.format("Duplicate key %s", u)); };
    }

    /**
     * 序列化树形结构 返回值为根节点列表
     * @param list 列表
     * @return 根节点列表
     * @param <K> key
     * @param <T> class
     */

    public static <K, T extends TreeInterface<K, T>> List<T> buildTree(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }

        Map<?, T> map = list.stream().collect(Collectors.toMap(TreeInterface::_getTreeKey, ($0)->$0, throwingMerger(), LinkedHashMap::new));

        List<T> nodes = new ArrayList<>(0);
        for (T item: list) {
            Object parentId = item._getTreeParentKey();
            //如果parentId为空或者取不到它的父节点就认为它是顶级节点
            if (Objects.isNull(map.get(parentId))) {
                List<T> children = item._getTreeChildren();
                //说明是顶级节点
                if (null == children) {
                    children = new ArrayList<>();
                }
                item._setTreeChildren(children);
                nodes.add(item);
            } else {
                //不是顶级节点，需要添加到父节点上
                T parent = map.get(parentId);
                List<T> parentChildren = parent._getTreeChildren();
                if (parentChildren == null) {
                    parentChildren = new ArrayList<>();
                }
                parentChildren.add(item);
                parent._setTreeChildren(parentChildren);
            }
        }
        return nodes;
    }

    /**
     * 序列化树形结构 返回值为唯一根节点，否则保持
     * @param list 列表
     * @return 根节点
     * @param <K> key
     * @param <T> class
     */
    public static <K, T extends TreeInterface<K, T>> T buildTreeNode(List<T> list) {
        List<T> nodes = buildTree(list);
        if (nodes.isEmpty()) {
            return null;
        }
        Assert.isTrue(nodes.size() > 1, "树形结构序列化失败");
        return nodes.get(0);
    }
}
