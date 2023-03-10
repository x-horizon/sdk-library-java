package cn.srd.itcp.sugar.tool.core.pipeline;

/**
 * 消费节点，该节点可以处理接收一个形参，无出参的动作
 *
 * @author wjm
 * @since 2023-03-10 09:20:11
 */
public interface NodeConsumer1<N1> extends Node {

    /**
     * 处理动作
     *
     * @param n1 形参 1
     */
    void handle(N1 n1);

}
