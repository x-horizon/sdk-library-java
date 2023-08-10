package cn.srd.sugar.concurrent.actor.core;

import cn.srd.itcp.sugar.context.constant.core.ModuleView;
import cn.srd.sugar.concurrent.actor.event.ActorEvent;
import cn.srd.sugar.concurrent.actor.id.ActorId;
import lombok.extern.slf4j.Slf4j;

/**
 * context aware actor, 用于对 actor system 的上下文感知
 *
 * @author wjm
 * @since 2023-03-20 11:04:19
 */
@Slf4j
public abstract class ContextAwareActor<T> extends DefaultActor<T> {

    /**
     * 获取 {@link ActorType}
     *
     * @return {@link ActorType}
     */
    public ActorType getActorType() {
        return getActorId().getActorType();
    }

    /**
     * 处理事件
     *
     * @param event 事件
     * @return 是否处理成功（该值在 thingsboard 的 actor 系统中目前是没有用的，可以看 {@link TbActorMailbox#processMailbox()}）的处理逻辑，所以该函数是用于在 {@link #doProcess(ActorEvent)} 时若返回了 false，记录一条日志
     */
    @Override
    public boolean process(ActorEvent<T> event) {
        if (log.isTraceEnabled()) {
            log.trace("{}}[{}] is processing event: {}", ModuleView.ACTOR_SYSTEM, getActorId(), event);
        }
        boolean success = doProcess(event);
        if (!success) {
            log.warn("{}[{}] process event failed: {}", ModuleView.ACTOR_SYSTEM, getActorId(), event);
        }
        return success;
    }

    /**
     * 处理事件发生异常时的操作
     *
     * @param throwable 异常
     * @return 处理事件异常策略
     */
    @Override
    public ProcessFailureStrategy onProcessFailure(Throwable throwable) {
        log.error("{}[{}] process event exception: ", ModuleView.ACTOR_SYSTEM, getActorId(), throwable);
        return doProcessFailure(throwable);
    }

    /**
     * 处理事件发生异常时的操作
     *
     * @param throwable 异常
     * @return 处理事件异常策略
     */
    protected ProcessFailureStrategy doProcessFailure(Throwable throwable) {
        if (throwable instanceof Error) {
            return ProcessFailureStrategy.stop();
        } else {
            return ProcessFailureStrategy.resume();
        }
    }

    /**
     * 获取 actor id
     * // TODO wjm 与 ActorCreator 的 createActorId() （因为使用是通过内部类使用，看该类的子类即可看到）重复，待优化
     *
     * @return actor id
     */
    public abstract ActorId getActorId();

    /**
     * 处理事件
     *
     * @param event 事件
     * @return 是否处理成功
     */
    protected abstract boolean doProcess(ActorEvent<T> event);

}
