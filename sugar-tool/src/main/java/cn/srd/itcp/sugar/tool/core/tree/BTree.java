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
     * find all path from the BTree.
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
    default <T extends BTree> List<List<T>> getAllPaths(T root) {
        List<List<T>> paths = new ArrayList<>();
        if (Objects.isNull(root)) {
            return paths;
        }
        dfsSupportGetAllPaths(root, new ArrayList<>(), paths);
        return paths;
    }

    /**
     * <pre>
     * find all node and child from the BTree.
     *
     * example:
     *
     * the BTree defined is:
     *             1
     *           / | \
     *          2  3  4
     *         / \
     *        5   6
     *       / \
     *      7   8
     *     /   /
     *    9   10
     *   /
     *  11
     *
     * all node and child are:
     * [
     *   [1, 2],
     *   [2, 5],
     *   [5, 7],
     *   [7, 9],
     *   [9, 11],
     *   [5, 8],
     *   [8, 10],
     *   [2, 6],
     *   [1, 3],
     *   [1, 4],
     * ]
     * </pre>
     *
     * @param root the root node
     * @param <T>  the children element type
     * @return all node and child
     */
    default <T extends BTree> List<List<T>> getAllNodeAndChild(T root) {
        List<List<T>> result = new ArrayList<>();
        dfsSupportGetAllNodeAndChild(root, result);
        return result;
    }

    /**
     * <pre>
     * flatten the BTree.
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
     * after flatten:
     * [1, 2, 3, 4, 5, 6]
     * </pre>
     *
     * @param root the root node
     * @param <T>  the children element type
     * @return after flatten result
     */
    default <T extends BTree> List<T> flatten(T root) {
        List<T> output = new ArrayList<>();
        dfsSupportFlatten(root, output);
        return output;
    }

    /**
     * find BTree all paths support: use depth-first traversal algorithm, the time complexity is O(mn)
     *
     * @param node  the tree node
     * @param path  one path
     * @param paths all paths
     * @param <T>   the children element type
     */
    @SuppressWarnings("unchecked")
    private <T extends BTree> void dfsSupportGetAllPaths(T node, List<T> path, List<List<T>> paths) {
        path.add(node);
        if (Objects.isEmpty(node.getChildren())) {
            paths.add(new ArrayList<>(path));
        } else {
            node.getChildren().forEach(child -> dfsSupportGetAllPaths((T) child, path, paths));
        }
        path.remove(path.size() - 1);
    }

    /**
     * find BTree all node and child support: use depth-first traversal algorithm, the time complexity is O(mn)
     *
     * @param node   the tree node
     * @param output all node and child
     * @param <T>    the children element type
     */
    @SuppressWarnings("unchecked")
    private <T extends BTree> void dfsSupportGetAllNodeAndChild(T node, List<List<T>> output) {
        if (Objects.isNull(node)) {
            return;
        }
        node.getChildren().forEach(child -> {
            List<T> nodeAndChild = new ArrayList<>();
            nodeAndChild.add(node);
            nodeAndChild.add((T) child);
            output.add(nodeAndChild);
            dfsSupportGetAllNodeAndChild((T) child, output);
        });
    }

    /**
     * flatten BTree support: use depth-first traversal algorithm, the time complexity is O(mn)
     *
     * @param node   the tree node
     * @param output after flatten
     * @param <T>    the children element type
     */
    @SuppressWarnings("unchecked")
    private <T extends BTree> void dfsSupportFlatten(T node, List<T> output) {
        if (Objects.isNull(node)) {
            return;
        }
        output.add(node);
        node.getChildren().forEach(child -> dfsSupportFlatten((T) child, output));
    }

}
