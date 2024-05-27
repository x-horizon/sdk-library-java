package cn.srd.library.java.message.engine.contract;

import cn.srd.library.java.contract.constant.text.SymbolConstant;

import java.lang.annotation.*;

/**
 * @author wjm
 * @since 2024-05-25 17:02
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MessageConsumer {

    MessageEngineType type();

    String clientId() default SymbolConstant.EMPTY;

    String[] topic();

    MessageQosType[] qos() default MessageQosType.AT_MOST_ONCE;

    long completionTimeout() default MessageFlows.DEFAULT_COMPLETION_TIMEOUT;

    long disconnectCompletionTimeout() default MessageFlows.DISCONNECT_COMPLETION_TIMEOUT;

}