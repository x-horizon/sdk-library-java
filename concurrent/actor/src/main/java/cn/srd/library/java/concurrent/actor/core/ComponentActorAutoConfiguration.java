package cn.srd.library.java.concurrent.actor.core;

import cn.srd.library.java.contract.constant.module.ModuleView;
import cn.srd.library.java.contract.constant.suppress.SuppressWarningConstant;
import cn.srd.library.java.contract.model.throwable.LibraryJavaInternalException;
import cn.srd.library.java.tool.lang.concurrent.Threads;
import cn.srd.library.java.tool.lang.functional.Assert;
import cn.srd.library.java.tool.lang.object.Classes;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.system.Systems;
import cn.srd.library.java.tool.lang.text.Strings;
import cn.srd.library.java.tool.spring.contract.support.Springs;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.annotation.Order;

import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

/**
 * {@link EnableAutoConfiguration AutoConfiguration} for Library Component Actor
 *
 * @author wjm
 * @since 2023-03-23 20:09:17
 */
@Slf4j
@Order
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

    @SuppressWarnings(SuppressWarningConstant.PREVIEW)
    @PostConstruct
    public void initActorSystem() {
        log.debug("{}starting initializing...", ModuleView.CONCURRENT_ACTOR_SYSTEM);

        ActorSystemSettings settings = new ActorSystemSettings(actorSystemProperties.getActorProcessThroughput(), actorSystemProperties.getSchedulerPoolSize(), actorSystemProperties.getMaxInitActorRetryCount());
        actorSystem = new DefaultActorSystem(settings);

        // 获取顶级 actor 类型策略的所有实现类，但排除掉包含顶级策略名字的实现类，原因是为了让业务方可以定义自己的顶级策略
        Classes.scanBySuper(ActorTypeStrategy.class, Springs.getSpringBootApplicationPackagePath())
                .stream()
                .filter(item -> Strings.notContains(item.getSimpleName(), ActorTypeStrategy.class.getSimpleName()))
                .collect(Collectors.toSet())
                .forEach(actorTypeStrategyClass -> {
                    ActorTypeStrategy actorTypeStrategy = Springs.getBean(actorTypeStrategyClass);
                    Assert.of(LibraryJavaInternalException.class, STR."\{ModuleView.CONCURRENT_ACTOR_SYSTEM}Actor System init failed, Class [\{actorTypeStrategyClass.getSimpleName()}] that implements class [\{ActorTypeStrategy.class.getSimpleName()}] is null, it may be not added to Spring IOC, please check!").throwsIfNull(actorTypeStrategy);
                    String dispatcherName = actorTypeStrategy.getDispatcherName();
                    int dispatcherCount = actorTypeStrategy.getDispatcherCount();
                    log.debug("{}Prepare to create actor dispatcher, name is [{}], count is [{}]", ModuleView.CONCURRENT_ACTOR_SYSTEM, dispatcherName, dispatcherCount);
                    actorSystem.createDispatcher(dispatcherName, newDispatcherExecutor(dispatcherName, dispatcherCount));
                    actorTypeStrategy.setMailbox(actorSystem.createRootActor(dispatcherName, actorTypeStrategy.newActorCreator()));
                });

        log.debug("{}initialized.", ModuleView.CONCURRENT_ACTOR_SYSTEM);
    }

    @PreDestroy
    public void destroyActorSystem() {
        if (actorSystem != null) {
            log.debug("{}Stopping...", ModuleView.CONCURRENT_ACTOR_SYSTEM);
            actorSystem.stop();
            log.debug("{}Stopped.", ModuleView.CONCURRENT_ACTOR_SYSTEM);
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
        if (Nil.isZeroValue(parallelism)) {
            parallelism = Math.max(SINGLE_CONCURRENCY_COUNT, Systems.getAvailableProcessors() / 2);
        }
        if (parallelism == SINGLE_CONCURRENCY_COUNT) {
            return Threads.newSingleThreadPool(dispatcherName);
        } else {
            return Threads.newWorkStealingThreadPool(parallelism, dispatcherName);
        }
    }

}