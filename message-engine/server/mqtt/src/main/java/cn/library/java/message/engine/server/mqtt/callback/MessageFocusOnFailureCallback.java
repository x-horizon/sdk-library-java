package cn.library.java.message.engine.server.mqtt.callback;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListeningExecutorService;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * @author wjm
 * @since 2025-01-07 21:25
 */
public interface MessageFocusOnFailureCallback<R> {

    void onFailure(Throwable throwable);

    default void process(ListeningExecutorService executorService, Callable<R> task) {
        Consumer<Throwable> onFailure = this::onFailure;
        FutureCallback<R> callback = new FutureCallback<>() {
            @Override
            public void onSuccess(R result) {
            }

            @Override
            public void onFailure(Throwable throwable) {
                onFailure.accept(throwable);
            }
        };
        Futures.addCallback(executorService.submit(task), callback, executorService);
    }

}