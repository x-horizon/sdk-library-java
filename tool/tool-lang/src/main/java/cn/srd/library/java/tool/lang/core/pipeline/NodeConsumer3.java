package cn.srd.library.java.tool.lang.core.pipeline;

/**
 * 消费节点，该节点可以处理接收三个形参，无出参的动作
 *
 * @param <N1> 处理动作的形参类型 1
 * @param <N2> 处理动作的形参类型 2
 * @param <N3> 处理动作的形参类型 3
 * @author wjm
 * @since 2023-03-10 09:20:11
 */
public interface NodeConsumer3<N1, N2, N3> extends Node {

    /**
     * 处理动作
     *
     * @param n1 形参 1
     * @param n2 形参 2
     * @param n3 形参 3
     */
    void handle(N1 n1, N2 n2, N3 n3);

}
