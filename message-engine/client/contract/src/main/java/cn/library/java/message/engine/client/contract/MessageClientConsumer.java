package cn.library.java.message.engine.client.contract;

import cn.library.java.contract.constant.text.SymbolConstant;
import cn.library.java.message.engine.client.contract.model.enums.MessageClientType;

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