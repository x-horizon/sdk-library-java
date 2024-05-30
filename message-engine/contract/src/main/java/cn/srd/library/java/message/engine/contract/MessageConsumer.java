package cn.srd.library.java.message.engine.contract;

import cn.srd.library.java.message.engine.contract.strategy.MessageEngineType;

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

    MessageEngineType engineType();

    MessageEngineConfig engineConfig();

    String[] topic();

}