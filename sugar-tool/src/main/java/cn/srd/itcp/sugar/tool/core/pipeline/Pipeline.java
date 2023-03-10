package cn.srd.itcp.sugar.tool.core.pipeline;

/**
 * 管道定义，用于将不同链条进行自由组合后，按顺序执行不同链条上的不同节点
 *
 * @author wjm
 * @since 2023-03-10 09:20:11
 */
public class Pipeline {

    /**
     * singleton pattern
     */
    private static final class SingleTonHolder {
        private static final Pipeline INSTANCE = new Pipeline();
    }

    /**
     * 获取实例
     *
     * @return this
     */
    public static Pipeline getInstance() {
        return SingleTonHolder.INSTANCE;
    }

    /**
     * 构造 {@link ChainConsumer1}
     *
     * @param <C1> {@link NodeConsumer1} 形参类型 1
     * @return {@link NodeConsumer1}
     */
    public <C1> ChainConsumer1<C1> ofChainConsumer1() {
        return new ChainConsumer1<>();
    }

    /**
     * 构造 {@link ChainConsumer2}
     *
     * @param <C1> {@link NodeConsumer2} 形参类型 1
     * @param <C2> {@link NodeConsumer2} 形参类型 2
     * @return {@link NodeConsumer2}
     */
    public <C1, C2> ChainConsumer2<C1, C2> ofChainConsumer2() {
        return new ChainConsumer2<>();
    }

    /**
     * 构造 {@link ChainConsumer3}
     *
     * @param <C1> {@link NodeConsumer3} 形参类型 1
     * @param <C2> {@link NodeConsumer3} 形参类型 2
     * @param <C3> {@link NodeConsumer3} 形参类型 3
     * @return {@link NodeConsumer3}
     */
    public <C1, C2, C3> ChainConsumer3<C1, C2, C3> ofChainConsumer3() {
        return new ChainConsumer3<>();
    }

    /**
     * 构造 {@link ChainConsumer4}
     *
     * @param <C1> {@link NodeConsumer4} 形参类型 1
     * @param <C2> {@link NodeConsumer4} 形参类型 2
     * @param <C3> {@link NodeConsumer4} 形参类型 3
     * @param <C4> {@link NodeConsumer4} 形参类型 4
     * @return {@link NodeConsumer4}
     */
    public <C1, C2, C3, C4> ChainConsumer4<C1, C2, C3, C4> ofChainConsumer4() {
        return new ChainConsumer4<>();
    }

    /**
     * 构造 {@link ChainConsumer5}
     *
     * @param <C1> {@link NodeConsumer5} 形参类型 1
     * @param <C2> {@link NodeConsumer5} 形参类型 2
     * @param <C3> {@link NodeConsumer5} 形参类型 3
     * @param <C4> {@link NodeConsumer5} 形参类型 4
     * @param <C5> {@link NodeConsumer5} 形参类型 5
     * @return {@link NodeConsumer5}
     */
    public <C1, C2, C3, C4, C5> ChainConsumer5<C1, C2, C3, C4, C5> ofChainConsumer5() {
        return new ChainConsumer5<>();
    }

}
