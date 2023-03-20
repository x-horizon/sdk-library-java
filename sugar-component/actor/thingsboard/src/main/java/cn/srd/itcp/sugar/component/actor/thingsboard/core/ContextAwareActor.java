package cn.srd.itcp.sugar.component.actor.thingsboard.core;

import cn.srd.itcp.sugar.component.actor.thingsboard.id.ActorId;
import cn.srd.itcp.sugar.component.actor.thingsboard.strategy.ActorType;
import lombok.extern.slf4j.Slf4j;
import org.thingsboard.server.actors.AbstractTbActor;
import org.thingsboard.server.actors.ProcessFailureStrategy;
import org.thingsboard.server.actors.TbActorCtx;
import org.thingsboard.server.actors.TbActorMailbox;
import org.thingsboard.server.common.msg.TbActorMsg;

/**
 * signal context aware actor, 用于对 actor system 的上下文感知
 *
 * @author wjm
 * @since 2023-03-20 11:04:19
 */
@Slf4j
public abstract class ContextAwareActor extends AbstractTbActor {

    /**
     * signal actor system context
     */
    protected final ActorSystemContext actorSystemContext;

    /**
     * constructor
     *
     * @param context {@link ActorSystemContext}
     */
    protected ContextAwareActor(ActorSystemContext context) {
        super();
        this.actorSystemContext = context;
    }

    /**
     * 替换 {@link #getCtx()}，因为该函数的名称是缩写的，不用
     *
     * @return {@link TbActorCtx}
     */
    public TbActorCtx getActorContext() {
        return this.ctx;
    }

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
     * @return 是否处理成功（该值在 thingsboard 的 actor 系统中目前是没有用的，可以看 {@link TbActorMailbox#processMailbox()}）的处理逻辑，所以该函数是用于在 {@link #doProcess(TbActorMsg)} 时若返回了 false，记录一条日志
     */
    @Override
    public boolean process(TbActorMsg event) {
        if (log.isDebugEnabled()) {
            log.debug("[{}] is processing event: {}", getActorId(), event);
        }
        boolean success = doProcess(event);
        if (!success) {
            log.warn("[{}] process event failed: {}", getActorId(), event);
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
        log.error("[{}] process event exception: {}", getActorId(), throwable);
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
    protected abstract boolean doProcess(TbActorMsg event);

}
