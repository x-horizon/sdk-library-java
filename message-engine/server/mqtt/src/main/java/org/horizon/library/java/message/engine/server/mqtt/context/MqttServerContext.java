package org.horizon.library.java.message.engine.server.mqtt.context;

import org.horizon.library.java.message.engine.server.mqtt.constant.MqttServerThreadConstant;
import org.horizon.library.java.message.engine.server.mqtt.model.property.MqttServerCommonProperty;
import org.horizon.library.java.message.engine.server.mqtt.model.property.MqttServerNonSslProperty;
import org.horizon.library.java.message.engine.server.mqtt.model.property.MqttServerSslProperty;
import org.horizon.library.java.tool.lang.booleans.Booleans;
import org.horizon.library.java.tool.lang.concurrent.Threads;
import org.horizon.library.java.tool.lang.object.Nil;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wjm
 * @since 2025-01-05 17:32
 */
public class MqttServerContext {

    @Getter
    @Autowired
    private MqttServerCommonProperty mqttServerCommonProperty;

    @Getter
    @Autowired
    private MqttServerNonSslProperty nonSslServerProperty;

    @Getter
    @Autowired
    private MqttServerSslProperty sslServerProperty;

    @Getter
    private ScheduledExecutorService singleThreadScheduler;

    @Getter
    protected ListeningExecutorService messageCallbackExecutor;

    private final AtomicInteger channelConnectionCounter = new AtomicInteger();

    public void addChannelConnection() {
        channelConnectionCounter.incrementAndGet();
    }

    public void decrementChannelConnection() {
        channelConnectionCounter.decrementAndGet();
    }

    @PostConstruct
    public void init() {
        if (Booleans.isTrue(mqttServerCommonProperty.getNeedToEnableVirtualThread())) {
            this.singleThreadScheduler = Executors.newSingleThreadScheduledExecutor(Thread.ofVirtual().name(MqttServerThreadConstant.SINGLE_THREAD_SCHEDULER, 1).factory());
            this.messageCallbackExecutor = MoreExecutors.listeningDecorator(Executors.newThreadPerTaskExecutor(Thread.ofVirtual().name(MqttServerThreadConstant.MESSAGE_CALLBACK_EXECUTOR, 1).factory()));
        } else {
            this.singleThreadScheduler = Executors.newSingleThreadScheduledExecutor(Thread.ofPlatform().name(MqttServerThreadConstant.SINGLE_THREAD_SCHEDULER, 1).factory());
            this.messageCallbackExecutor = MoreExecutors.listeningDecorator(new ForkJoinPool(mqttServerCommonProperty.getMessageCallbackThreadCount(), Threads.newForkJoinWorkerThreadFactory(MqttServerThreadConstant.MESSAGE_CALLBACK_EXECUTOR), null, true));
        }
    }

    @PreDestroy
    public void stop() {
        if (Nil.isNotNull(singleThreadScheduler)) {
            singleThreadScheduler.shutdownNow();
        }
        if (Nil.isNotNull(messageCallbackExecutor)) {
            messageCallbackExecutor.shutdownNow();
        }
    }

}