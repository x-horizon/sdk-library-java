package org.horizon.library.java.message.engine.client.contract;

import org.horizon.library.java.contract.constant.text.SymbolConstant;
import org.horizon.library.java.message.engine.client.contract.model.enums.MessageClientType;

import java.lang.annotation.*;

/**
 * @author wjm
 * @since 2024-05-25 17:02
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MessageClientConsumer {

    MessageClientConfig config();

    String[] topics();

    MessageClientProducer forwardTo() default @MessageClientProducer(config = @MessageClientConfig(engineType = MessageClientType.NIL), topic = SymbolConstant.EMPTY);

}