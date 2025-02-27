package org.horizon.sdk.library.java.concurrent.actor.system;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.ExecutorService;

/**
 * @author wjm
 * @since 2025-01-26 23:33
 */
@Getter
@RequiredArgsConstructor
public class ActorDispatcher {

    private final String dispatcherId;

    private final ExecutorService executor;

}