package org.horizon.sdk.library.java.message.engine.server.mqtt.callback;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListeningExecutorService;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * @author wjm
 * @since 2025-01-07 21:25
 */
public interface MessageFocusOnSuccessCallback<R> {

    void onSuccess(R result);

    default void process(ListeningExecutorService executorService, Callable<R> task) {
        Consumer<R> onSuccess = this::onSuccess;
        FutureCallback<R> callback = new FutureCallback<>() {
            @Override
            public void onSuccess(R result) {
                onSuccess.accept(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
            }
        };
        Futures.addCallback(executorService.submit(task), callback, executorService);
    }

}