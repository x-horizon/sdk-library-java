package cn.srd.library.java.message.engine.contract;

import cn.srd.library.java.contract.constant.text.SymbolConstant;
import cn.srd.library.java.message.engine.contract.model.enums.MessageEngineType;

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

    MessageConfig config();

    String[] topics();

    MessageProducer forwardTo() default @MessageProducer(config = @MessageConfig(engineType = MessageEngineType.NIL), topic = SymbolConstant.EMPTY);

}