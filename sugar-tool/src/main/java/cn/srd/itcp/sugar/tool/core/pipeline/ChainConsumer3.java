package cn.srd.itcp.sugar.tool.core.pipeline;

import io.vavr.*;

import java.util.LinkedList;
import java.util.List;

/**
 * 消费链，该链条上包含多个接收三个形参，无出参的 {@link NodeConsumer3}
 *
 * @param <C1> {@link NodeConsumer3} 形参类型 1
 * @param <C2> {@link NodeConsumer3} 形参类型 2
 * @param <C3> {@link NodeConsumer3} 形参类型 3
 * @author wjm
 * @since 2023-03-10 09:20:11
 */
public class ChainConsumer3<C1, C2, C3> implements Chain {

    /**
     * collection of {@link NodeConsumer3}
     */
    private final List<NodeConsumer3<C1, C2, C3>> nodes = new LinkedList<>();

    /**
     * 添加节点
     *
     * @param node {@link NodeConsumer3}
     * @return this
     */
    public ChainConsumer3<C1, C2, C3> addNode(NodeConsumer3<C1, C2, C3> node) {
        nodes.add(node);
        return this;
    }

    /**
     * 添加节点以及该节点的生效条件，若生效条件不满足，则该节点不会执行
     *
     * @param node      {@link NodeConsumer3}
     * @param predicate 生效条件
     * @param p1        生效条件的入参 1
     * @param <P1>      生效条件参数类型 1
     * @return this
     */
    public <P1> ChainConsumer3<C1, C2, C3> addNode(NodeConsumer3<C1, C2, C3> node, Function1<P1, Boolean> predicate, P1 p1) {
        if (Boolean.TRUE.equals(predicate.apply(p1))) {
            return addNode(node);
        }
        return this;
    }

    /**
     * 添加节点以及该节点的生效条件，若生效条件不满足，则该节点不会执行
     *
     * @param node      {@link NodeConsumer3}
     * @param predicate 生效条件
     * @param p1        生效条件的入参 1
     * @param p2        生效条件的入参 2
     * @param <P1>      生效条件参数类型 1
     * @param <P2>      生效条件参数类型 2
     * @return this
     */
    public <P1, P2> ChainConsumer3<C1, C2, C3> addNode(NodeConsumer3<C1, C2, C3> node, Function2<P1, P2, Boolean> predicate, P1 p1, P2 p2) {
        if (Boolean.TRUE.equals(predicate.apply(p1, p2))) {
            return addNode(node);
        }
        return this;
    }

    /**
     * 添加节点以及该节点的生效条件，若生效条件不满足，则该节点不会执行
     *
     * @param node      {@link NodeConsumer3}
     * @param predicate 生效条件
     * @param p1        生效条件的入参 1
     * @param p2        生效条件的入参 2
     * @param p3        生效条件的入参 3
     * @param <P1>      生效条件参数类型 1
     * @param <P2>      生效条件参数类型 2
     * @param <P3>      生效条件参数类型 3
     * @return this
     */
    public <P1, P2, P3> ChainConsumer3<C1, C2, C3> addNode(NodeConsumer3<C1, C2, C3> node, Function3<P1, P2, P3, Boolean> predicate, P1 p1, P2 p2, P3 p3) {
        if (Boolean.TRUE.equals(predicate.apply(p1, p2, p3))) {
            return addNode(node);
        }
        return this;
    }

    /**
     * 添加节点以及该节点的生效条件，若生效条件不满足，则该节点不会执行
     *
     * @param node      {@link NodeConsumer3}
     * @param predicate 生效条件
     * @param p1        生效条件的入参 1
     * @param p2        生效条件的入参 2
     * @param p3        生效条件的入参 3
     * @param p4        生效条件的入参 4
     * @param <P1>      生效条件参数类型 1
     * @param <P2>      生效条件参数类型 2
     * @param <P3>      生效条件参数类型 3
     * @param <P4>      生效条件参数类型 4
     * @return this
     */
    public <P1, P2, P3, P4> ChainConsumer3<C1, C2, C3> addNode(NodeConsumer3<C1, C2, C3> node, Function4<P1, P2, P3, P4, Boolean> predicate, P1 p1, P2 p2, P3 p3, P4 p4) {
        if (Boolean.TRUE.equals(predicate.apply(p1, p2, p3, p4))) {
            return addNode(node);
        }
        return this;
    }

    /**
     * 添加节点以及该节点的生效条件，若生效条件不满足，则该节点不会执行
     *
     * @param node      {@link NodeConsumer3}
     * @param predicate 生效条件
     * @param p1        生效条件的入参 1
     * @param p2        生效条件的入参 2
     * @param p3        生效条件的入参 3
     * @param p4        生效条件的入参 4
     * @param p5        生效条件的入参 5
     * @param <P1>      生效条件参数类型 1
     * @param <P2>      生效条件参数类型 2
     * @param <P3>      生效条件参数类型 3
     * @param <P4>      生效条件参数类型 4
     * @param <P5>      生效条件参数类型 5
     * @return this
     */
    public <P1, P2, P3, P4, P5> ChainConsumer3<C1, C2, C3> addNode(NodeConsumer3<C1, C2, C3> node, Function5<P1, P2, P3, P4, P5, Boolean> predicate, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5) {
        if (Boolean.TRUE.equals(predicate.apply(p1, p2, p3, p4, p5))) {
            return addNode(node);
        }
        return this;
    }

    /**
     * 执行该链上的节点
     *
     * @param c1 {@link NodeConsumer3#handle(Object, Object, Object)} 入参 1
     * @param c2 {@link NodeConsumer3#handle(Object, Object, Object)} 入参 2
     * @param c3 {@link NodeConsumer3#handle(Object, Object, Object)} 入参 3
     * @return {@link Pipeline}
     */
    public Pipeline execute(C1 c1, C2 c2, C3 c3) {
        nodes.forEach(node -> node.handle(c1, c2, c3));
        return Pipeline.getInstance();
    }

}
