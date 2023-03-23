package cn.srd.itcp.sugar.component.actor.core;

import cn.srd.itcp.sugar.framework.spring.tool.common.core.SpringsUtil;
import cn.srd.itcp.sugar.tool.core.ClassesUtil;
import cn.srd.itcp.sugar.tool.core.CollectionsUtil;
import cn.srd.itcp.sugar.tool.core.Objects;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/**
 * @author wjm
 * @since 2023-03-20 11:04:19
 */
@Slf4j
public class DefaultActorSystemCreator implements ActorSystemCreator {

    /**
     * all actor system inherit from {@link ActorSystem}
     */
    private static Set<Class<? extends ActorSystemCreator>> ACTOR_SYSTEMS;

    @PostConstruct
    @Override
    public void init() {
        ACTOR_SYSTEMS = CollectionsUtil.filter(ClassesUtil.scanPackagesBySuper(new String[]{SpringsUtil.getRootPackagePath()}, ActorSystemCreator.class), item -> Objects.notEquals(DefaultActorSystemCreator.class, item));
        ACTOR_SYSTEMS.forEach(item -> SpringsUtil.getBean(item).init());
    }

    @PreDestroy
    @Override
    public void destroy() {
        ACTOR_SYSTEMS.forEach(item -> SpringsUtil.getBean(item).destroy());
    }

}
