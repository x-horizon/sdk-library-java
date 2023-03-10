package cn.srd.itcp.sugar.tool.core.pipeline;

import io.vavr.*;

import java.util.LinkedList;
import java.util.List;

/**
 * 消费链，该链条上包含多个接收五个形参，无出参的 {@link NodeConsumer5}
 *
 * @param <C1> {@link NodeConsumer5} 形参类型 1
 * @param <C2> {@link NodeConsumer5} 形参类型 2
 * @param <C3> {@link NodeConsumer5} 形参类型 3
 * @param <C4> {@link NodeConsumer5} 形参类型 4
 * @param <C5> {@link NodeConsumer5} 形参类型 5
 * @author wjm
 * @since 2023-03-10 09:20:11
 */
public class ChainConsumer5<C1, C2, C3, C4, C5> implements Chain {

    /**
     * collection of {@link NodeConsumer5}
     */
    private final List<NodeConsumer5<C1, C2, C3, C4, C5>> nodes = new LinkedList<>();

    /**
     * 添加 {@link NodeConsumer5}
     *
     * @param node {@link NodeConsumer5}
     * @return this
     */
    public ChainConsumer5<C1, C2, C3, C4, C5> addNode(NodeConsumer5<C1, C2, C3, C4, C5> node) {
        nodes.add(node);
        return this;
    }

    /**
     * 添加 {@link NodeConsumer5} 以及该节点的互斥条件，若互斥条件生效，则该节点不会执行
     *
     * @param node      {@link NodeConsumer5}
     * @param predicate 互斥条件
     * @param p1        互斥条件的入参 1
     * @param <P1>      互斥条件参数类型 1
     * @return this
     */
    public <P1> ChainConsumer5<C1, C2, C3, C4, C5> addNode(NodeConsumer5<C1, C2, C3, C4, C5> node, Function1<P1, Boolean> predicate, P1 p1) {
        if (predicate.apply(p1)) {
            nodes.add(node);
        }
        return this;
    }

    /**
     * 添加 {@link NodeConsumer5} 以及该节点的互斥条件，若互斥条件生效，则该节点不会执行
     *
     * @param node      {@link NodeConsumer5}
     * @param predicate 互斥条件
     * @param p1        互斥条件的入参 1
     * @param p2        互斥条件的入参 2
     * @param <P1>      互斥条件参数类型 1
     * @param <P2>      互斥条件参数类型 2
     * @return this
     */
    public <P1, P2> ChainConsumer5<C1, C2, C3, C4, C5> addNode(NodeConsumer5<C1, C2, C3, C4, C5> node, Function2<P1, P2, Boolean> predicate, P1 p1, P2 p2) {
        if (predicate.apply(p1, p2)) {
            nodes.add(node);
        }
        return this;
    }

    /**
     * 添加 {@link NodeConsumer5} 以及该节点的互斥条件，若互斥条件生效，则该节点不会执行
     *
     * @param node      {@link NodeConsumer5}
     * @param predicate 互斥条件
     * @param p1        互斥条件的入参 1
     * @param p2        互斥条件的入参 2
     * @param p3        互斥条件的入参 3
     * @param <P1>      互斥条件参数类型 1
     * @param <P2>      互斥条件参数类型 2
     * @param <P3>      互斥条件参数类型 3
     * @return this
     */
    public <P1, P2, P3> ChainConsumer5<C1, C2, C3, C4, C5> addNode(NodeConsumer5<C1, C2, C3, C4, C5> node, Function3<P1, P2, P3, Boolean> predicate, P1 p1, P2 p2, P3 p3) {
        if (predicate.apply(p1, p2, p3)) {
            nodes.add(node);
        }
        return this;
    }

    /**
     * 添加 {@link NodeConsumer5} 以及该节点的互斥条件，若互斥条件生效，则该节点不会执行
     *
     * @param node      {@link NodeConsumer5}
     * @param predicate 互斥条件
     * @param p1        互斥条件的入参 1
     * @param p2        互斥条件的入参 2
     * @param p3        互斥条件的入参 3
     * @param p4        互斥条件的入参 4
     * @param <P1>      互斥条件参数类型 1
     * @param <P2>      互斥条件参数类型 2
     * @param <P3>      互斥条件参数类型 3
     * @param <P4>      互斥条件参数类型 4
     * @return this
     */
    public <P1, P2, P3, P4> ChainConsumer5<C1, C2, C3, C4, C5> addNode(NodeConsumer5<C1, C2, C3, C4, C5> node, Function4<P1, P2, P3, P4, Boolean> predicate, P1 p1, P2 p2, P3 p3, P4 p4) {
        if (predicate.apply(p1, p2, p3, p4)) {
            nodes.add(node);
        }
        return this;
    }

    /**
     * 添加 {@link NodeConsumer5} 以及该节点的互斥条件，若互斥条件生效，则该节点不会执行
     *
     * @param node      {@link NodeConsumer5}
     * @param predicate 互斥条件
     * @param p1        互斥条件的入参 1
     * @param p2        互斥条件的入参 2
     * @param p3        互斥条件的入参 3
     * @param p4        互斥条件的入参 4
     * @param p5        互斥条件的入参 5
     * @param <P1>      互斥条件参数类型 1
     * @param <P2>      互斥条件参数类型 2
     * @param <P3>      互斥条件参数类型 3
     * @param <P4>      互斥条件参数类型 4
     * @param <P5>      互斥条件参数类型 5
     * @return this
     */
    public <P1, P2, P3, P4, P5> ChainConsumer5<C1, C2, C3, C4, C5> addNode(NodeConsumer5<C1, C2, C3, C4, C5> node, Function5<P1, P2, P3, P4, P5, Boolean> predicate, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5) {
        if (predicate.apply(p1, p2, p3, p4, p5)) {
            nodes.add(node);
        }
        return this;
    }

    /**
     * 执行该链上的节点
     *
     * @param c1 {@link NodeConsumer5#handle(Object, Object, Object, Object, Object)} 入参 1
     * @param c2 {@link NodeConsumer5#handle(Object, Object, Object, Object, Object)} 入参 2
     * @param c3 {@link NodeConsumer5#handle(Object, Object, Object, Object, Object)} 入参 3
     * @param c4 {@link NodeConsumer5#handle(Object, Object, Object, Object, Object)} 入参 4
     * @param c5 {@link NodeConsumer5#handle(Object, Object, Object, Object, Object)} 入参 5
     * @return {@link Pipeline}
     */
    public Pipeline execute(C1 c1, C2 c2, C3 c3, C4 c4, C5 c5) {
        nodes.forEach(node -> node.handle(c1, c2, c3, c4, c5));
        return Pipeline.getInstance();
    }

}
