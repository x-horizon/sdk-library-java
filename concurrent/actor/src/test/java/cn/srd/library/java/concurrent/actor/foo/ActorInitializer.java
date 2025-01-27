package cn.srd.library.java.concurrent.actor.foo;

import cn.srd.library.java.concurrent.actor.autoconfigure.ActorAutoConfigurer;
import cn.srd.library.java.concurrent.actor.message.ActorMessage;
import cn.srd.library.java.concurrent.actor.system.ActorSystem;
import cn.srd.library.java.tool.lang.concurrent.Threads;
import cn.srd.library.java.tool.lang.object.Nil;
import cn.srd.library.java.tool.lang.system.Systems;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author wjm
 * @since 2025-01-28 00:27
 */
@Slf4j
@Component
@AutoConfigureAfter(ActorAutoConfigurer.class)
@EnableConfigurationProperties(IotActorProperty.class)
public class ActorInitializer<T extends ActorMessage> {

    private static final String APP_DISPATCHER_NAME = "app-dispatcher";

    private static final String TENANT_DISPATCHER_NAME = "tenant-dispatcher";

    private static final String DEVICE_DISPATCHER_NAME = "device-dispatcher";

    private static final String RULE_ENGINE_DISPATCHER_NAME = "rule-engine-dispatcher";

    @Autowired private ActorSystem<T> actorSystem;

    @Autowired private IotActorProperty iotActorProperty;

    @PostConstruct
    public void init() {
        actorSystem.createDispatcher(APP_DISPATCHER_NAME, initDispatcherExecutor(APP_DISPATCHER_NAME, iotActorProperty.getAppDispatcherPoolSize(), iotActorProperty.getNeedToEnableVirtualThread()));
        actorSystem.createDispatcher(TENANT_DISPATCHER_NAME, initDispatcherExecutor(TENANT_DISPATCHER_NAME, iotActorProperty.getTenantDispatcherPoolSize(), iotActorProperty.getNeedToEnableVirtualThread()));
        actorSystem.createDispatcher(DEVICE_DISPATCHER_NAME, initDispatcherExecutor(DEVICE_DISPATCHER_NAME, iotActorProperty.getDeviceDispatcherPoolSize(), iotActorProperty.getNeedToEnableVirtualThread()));
        actorSystem.createDispatcher(RULE_ENGINE_DISPATCHER_NAME, initDispatcherExecutor(RULE_ENGINE_DISPATCHER_NAME, iotActorProperty.getRuleEngineDispatcherPoolSize(), iotActorProperty.getNeedToEnableVirtualThread()));

        appActor = actorSystem.createRootActorReference(APP_DISPATCHER_NAME, new AppActor.ActorCreator(actorContext));
        actorContext.setAppActor(appActor);
    }

    @PreDestroy
    public void stopActorSystem() {
        if (Nil.isNotNull(actorSystem)) {
            actorSystem.stop();
        }
    }

    private ExecutorService initDispatcherExecutor(String dispatcherName, int poolSize, boolean needToEnableVirtualThread) {
        if (needToEnableVirtualThread) {
            return Executors.newThreadPerTaskExecutor(Thread.ofVirtual().name(dispatcherName, 1).factory());
        }
        if (poolSize == 0) {
            poolSize = Math.max(1, Systems.getAvailableProcessors() / 2);
        }
        if (poolSize == 1) {
            return Executors.newSingleThreadExecutor(Thread.ofPlatform().name(dispatcherName, 1).factory());
        } else {
            return Threads.newWorkStealingThreadPool(poolSize, dispatcherName);
        }
    }

}