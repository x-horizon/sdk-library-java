package cn.library.java.message.engine.client.contract;

import java.lang.annotation.*;

/**
 * @author wjm
 * @since 2024-05-25 17:02
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MessageClientProducer {

    MessageClientConfig config();

    String topic();

}