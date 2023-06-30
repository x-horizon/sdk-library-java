package cn.srd.itcp.sugar.component.actor.core;

import lombok.Data;

import java.util.concurrent.ExecutorService;

/**
 * @author wjm
 * @since 2023-03-23 20:09:17
 */
@Data
public class Dispatcher {

    private final String dispatcherId;
    private final ExecutorService executor;

}
