package cn.srd.library.java.contract.component.message.engine;

import cn.srd.library.java.contract.component.message.engine.model.enums.MessageEngineType;

import java.lang.annotation.*;

/**
 * @author wjm
 * @since 2024-05-25 17:02
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MessageSend {

    MessageEngineType type();

}