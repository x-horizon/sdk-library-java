package cn.srd.library.java.message.engine.contract;

import cn.srd.library.java.contract.constant.text.SymbolConstant;
import cn.srd.library.java.message.engine.contract.strategy.MessageEngineType;
import cn.srd.library.java.message.engine.contract.strategy.MessageQosType;
import cn.srd.library.java.message.engine.contract.support.MessageFlows;

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

    MessageEngineType engine();

    String clientId() default SymbolConstant.EMPTY;

    String[] topic();

    MessageQosType[] qos() default MessageQosType.AT_MOST_ONCE;

    long completionTimeout() default MessageFlows.DEFAULT_COMPLETION_TIMEOUT;

    long disconnectCompletionTimeout() default MessageFlows.DISCONNECT_COMPLETION_TIMEOUT;

}