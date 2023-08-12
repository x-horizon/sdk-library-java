package cn.srd.library.concurrent.actor.core;

import lombok.Getter;
import lombok.ToString;

/**
 * @author wjm
 * @since 2023-03-23 20:09:17
 */
@ToString
public class ProcessFailureStrategy {

    @Getter
    private boolean stop;

    private ProcessFailureStrategy(boolean stop) {
        this.stop = stop;
    }

    public static ProcessFailureStrategy stop() {
        return new ProcessFailureStrategy(true);
    }

    public static ProcessFailureStrategy resume() {
        return new ProcessFailureStrategy(false);
    }
}
