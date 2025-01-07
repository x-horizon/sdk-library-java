package cn.srd.library.java.message.engine.server.mqtt.strategy;

/**
 * @author wjm
 * @since 2025-01-07 21:39
 */
public interface ClientUnsubscribeStrategy {

    short process(String unsubscribeTopic);

}