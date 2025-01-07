package cn.srd.library.java.message.engine.server.mqtt.callback;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListeningExecutorService;

import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * @author wjm
 * @since 2025-01-06 22:26
 */
public interface MessageCallback<R> {

    MessageCallback<Void> DO_NOTHING = new MessageCallback<>() {
        @Override
        public void onSuccess(Void result) {
        }

        @Override
        public void onFailure(Throwable throwable) {
        }
    };

    default void process(ListeningExecutorService executorService, Callable<R> task) {
        Consumer<R> onSuccess = this::onSuccess;
        Consumer<Throwable> onFailure = this::onFailure;
        FutureCallback<R> callback = new FutureCallback<>() {
            @Override
            public void onSuccess(R result) {
                try {
                    onSuccess.accept(result);
                } catch (Throwable throwable) {
                    onFailure(throwable);
                }
            }

            @Override
            public void onFailure(Throwable throwable) {
                onFailure.accept(throwable);
            }
        };
        Futures.addCallback(executorService.submit(task), callback, executorService);
    }

    void onSuccess(R result);

    void onFailure(Throwable throwable);

}