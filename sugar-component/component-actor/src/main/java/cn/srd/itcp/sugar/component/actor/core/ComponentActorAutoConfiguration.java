package cn.srd.itcp.sugar.component.actor.core;

import cn.srd.itcp.sugar.framework.spring.tool.common.core.SpringsUtil;
import cn.srd.itcp.sugar.tool.core.*;
import cn.srd.itcp.sugar.tool.core.object.Objects;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.Set;
import java.util.concurrent.ExecutorService;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Sugar Component Actor
 *
 * @author wjm
 * @since 2023-03-23 20:09:17
 */
@Slf4j
@AutoConfiguration
@EnableConfigurationProperties(ActorSystemProperties.class)
public class ComponentActorAutoConfiguration {

    @Autowired
    private ActorSystemProperties actorSystemProperties;

    private ActorSystem actorSystem;

    /**
     * single concurrency count
     */
    private static final int SINGLE_CONCURRENCY_COUNT = 1;

    @PostConstruct
    public void initActorSystem() {
        log.debug("Actor System enable, starting initializing.");

        ActorSystemSettings settings = new ActorSystemSettings(actorSystemProperties.getActorProcessThroughput(), actorSystemProperties.getSchedulerPoolSize(), actorSystemProperties.getMaxInitActorRetryCount());
        actorSystem = new DefaultActorSystem(settings);

        // 获取顶级 actor 类型策略的所有实现类，但排除掉包含顶级策略名字的实现类，原因是为了让业务方可以定义自己的顶级策略
        Set<Class<? extends ActorTypeStrategy>> actorTypeStrategyClasses = CollectionsUtil.filter(ClassesUtil.scanPackagesBySuper(new String[]{SpringsUtil.getRootPackagePath()}, ActorTypeStrategy.class), item -> StringsUtil.notContains(item.getSimpleName(), ActorTypeStrategy.class.getSimpleName()));
        actorTypeStrategyClasses.forEach(actorTypeStrategyClass -> {
            ActorTypeStrategy actorTypeStrategy = Objects.requireNotNull(() -> StringsUtil.format("Actor System init failed, Class [{}] that implements class [{}] is null, it may be not added to Spring IOC, please check!", actorTypeStrategyClass.getSimpleName(), ActorTypeStrategy.class.getSimpleName()), SpringsUtil.getBean(actorTypeStrategyClass));
            String dispatcherName = actorTypeStrategy.getDispatcherName();
            actorSystem.createDispatcher(dispatcherName, newDispatcherExecutor(dispatcherName, actorTypeStrategy.getDispatcherCount()));
            actorTypeStrategy.setActor(actorSystem.createRootActor(dispatcherName, actorTypeStrategy.newActorCreator()));
        });

        log.debug("Actor System initialized.");
    }

    @PreDestroy
    public void destroyActorSystem() {
        if (actorSystem != null) {
            log.debug("Stopping Actor System.");
            actorSystem.stop();
            log.debug("Actor System Stopped.");
        }
    }

    /**
     * create dispatcher executor
     *
     * @param dispatcherName dispatcher name
     * @param parallelism    concurrency count
     * @return dispatcher executor
     */
    private ExecutorService newDispatcherExecutor(String dispatcherName, int parallelism) {
        if (parallelism == 0) {
            parallelism = Math.max(SINGLE_CONCURRENCY_COUNT, SystemsUtil.getAvailableProcessors() / 2);
        }
        if (parallelism == SINGLE_CONCURRENCY_COUNT) {
            return Threads.newSingleThreadPool(dispatcherName);
        } else {
            return Threads.newWorkStealingThreadPool(parallelism, dispatcherName);
        }
    }

}
