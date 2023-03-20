package cn.srd.itcp.sugar.component.actor.thingsboard.core;

import cn.srd.itcp.sugar.framework.spring.tool.common.core.SpringsUtil;
import cn.srd.itcp.sugar.tool.core.ClassesUtil;
import cn.srd.itcp.sugar.tool.core.CollectionsUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * default signal actor system
 *
 * @author wjm
 * @since 2023-03-20 11:04:19
 */
@Component
public class DefaultActorSystem implements ActorSystem {

    /**
     * all actor system inherit from {@link ActorSystem}
     */
    private static Set<Class<? extends ActorSystem>> SIGNAL_ACTOR_SYSTEMS;

    @PostConstruct
    @Override
    public void init() {
        SIGNAL_ACTOR_SYSTEMS = CollectionsUtil.filter(ClassesUtil.scanPackagesBySuper(new String[]{SpringsUtil.getRootPackagePath()}, ActorSystem.class), signalLifeCycleActorSystem -> Objects.notEquals(DefaultActorSystem.class, signalLifeCycleActorSystem));
        SIGNAL_ACTOR_SYSTEMS.forEach(signalLifeCycleActorSystem -> SpringsUtil.getBean(signalLifeCycleActorSystem).init());
    }

    @PreDestroy
    @Override
    public void destroy() {
        SIGNAL_ACTOR_SYSTEMS.forEach(signalActorSystemLifeCycle -> SpringsUtil.getBean(signalActorSystemLifeCycle).destroy());
    }

}
