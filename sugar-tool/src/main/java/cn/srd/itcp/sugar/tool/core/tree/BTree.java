package cn.srd.itcp.sugar.tool.core.tree;

import cn.srd.itcp.sugar.tool.core.object.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * the BTree defined, BTree is a tree with infinite leaf nodes
 *
 * @author wjm
 * @since 2023-06-26 15:56:37
 */
public interface BTree {

    /**
     * get children
     *
     * @param <T> the children element type
     * @return children
     */
    <T extends BTree> List<T> getChildren();

    /**
     * <pre>
     * find all path by the BTree.
     *
     * example:
     *
     * the BTree defined is:
     *       1
     *     / | \
     *    2  3  4
     *   / \
     *  5   6
     *
     * all the BTree paths are:
     * [
     *   [1, 2, 5],
     *   [1, 2, 6],
     *   [1, 3],
     *   [1, 4]
     * ]
     * </pre>
     *
     * @param root the root node
     * @param <T>  the children element type
     * @return all the BTree paths
     */
    default <T extends BTree> List<List<T>> getAllPath(T root) {
        List<List<T>> paths = new ArrayList<>();
        if (Objects.isNull(root)) {
            return paths;
        }
        dfs(root, new ArrayList<>(), paths);
        return paths;
    }

    /**
     * find tree all paths, use depth-first traversal algorithm
     *
     * @param node  the tree node
     * @param path  one path
     * @param paths all paths
     * @param <T>   the children element type
     */
    @SuppressWarnings("unchecked")
    default <T extends BTree> void dfs(T node, List<T> path, List<List<T>> paths) {
        path.add(node);
        if (Objects.isEmpty(node.getChildren())) {
            paths.add(new ArrayList<>(path));
        } else {
            node.getChildren().forEach(child -> dfs((T) child, path, paths));
        }
        path.remove(path.size() - 1);
    }

}
