package org.horizon.library.java.tool.lang.collection;

import com.google.errorprone.annotations.CanIgnoreReturnValue;

import java.util.List;

/**
 * the capable {@link BTree} describe the id and parent id structure
 *
 * @param <Key> the children element id type
 * @param <T>   the children element type
 * @author wjm
 * @since 2024-07-02 23:52
 */
public interface BTreeNode<Key, T extends BTreeNode<Key, T>> extends BTree<T, BTreeNode<Key, T>> {

    /**
     * get id
     *
     * @return id
     */
    Key getId();

    /**
     * get parent id
     *
     * @return parent id
     */
    Key getParentId();

    /**
     * set the children
     *
     * @param children the children
     * @return the tree self
     */
    @CanIgnoreReturnValue
    T setChildren(List<T> children);

}