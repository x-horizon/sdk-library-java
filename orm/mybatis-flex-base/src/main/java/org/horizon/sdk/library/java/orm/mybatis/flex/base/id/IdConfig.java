package org.horizon.sdk.library.java.orm.mybatis.flex.base.id;

import org.horizon.sdk.library.java.tool.id.snowflake.autoconfigure.EnableSnowflakeId;
import org.horizon.sdk.library.java.tool.id.snowflake.support.SnowflakeIds;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.UUID;

/**
 * the global id config
 *
 * @author wjm
 * @since 2023-11-12 21:06
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface IdConfig {

    /**
     * <p>specifies the global ID generation strategy:</p>
     *
     * <ol>
     *  <li><p>{@link IdGenerateType#AUTO_INCREMENT}:</p>
     *  <ul>
     *    <li>requires database primary key to have auto-increment configuration</li>
     *    <li>do <strong>not</strong> set {@code generator()} or {@code generateSQL()}</li>
     *  </ul></li>
     *
     *  <li><p>{@link IdGenerateType#UUID}:</p>
     *  <ul>
     *    <li>auto-generates ID using {@link UUID#randomUUID()} before insertion</li>
     *    <li>do <strong>not</strong> set {@code generator()} or {@code generateSQL()}</li>
     *  </ul></li>
     *
     *  <li><p>{@link IdGenerateType#SNOWFLAKE}:</p>
     *  <ul>
     *    <li>auto-generates ID using {@link SnowflakeIds#get()} before insertion</li>
     *    <li>requires {@link EnableSnowflakeId} annotation on application classpath</li>
     *    <li>do <strong>not</strong> set {@code generator()} or {@code generateSQL()}</li>
     *    <li>implementation based on:
     *      <ul>
     *        <li>{@link EnableSnowflakeId} configuration</li>
     *        <li>{@link SnowflakeIds} generator</li>
     *        <li><a href="https://github.com/yitter/IdGenerator">yitter snowflake algorithm</a></li>
     *      </ul>
     *    </li>
     *  </ul></li>
     *
     *  <li><p>{@link IdGenerateType#CUSTOMER}:</p>
     *  <ul>
     *    <li>requires custom {@link IdGenerator} implementation</li>
     *    <li>generates ID via {@link IdGenerator#generate(Object, String)}</li>
     *    <li>do <strong>not</strong> set {@code generateSQL()}</li>
     *  </ul></li>
     *
     *  <li><p>{@link IdGenerateType#SQL}:</p>
     *  <ul>
     *    <li>requires SQL statement in {@code generateSQL()}</li>
     *    <li>example: <pre>{@code select SEQ_USER_ID.nextval as id from dual}</pre></li>
     *    <li>do <strong>not</strong> set {@code generator()}</li>
     *  </ul></li>
     *
     *  <li><p>{@link IdGenerateType#UNCONTROLLED}:</p>
     *  <ul>
     *    <li>complete manual ID assignment</li>
     *    <li>do <strong>not</strong> set {@code generator()} or {@code generateSQL()}</li>
     *  </ul></li>
     * </ol>
     *
     * @return configured ID generation strategy
     */
    // TODO wjm The implementation here is not good enough, and it is strongly bound with snowflake id. The client does not necessarily need snowflake id. At present, the client must provide the correct redis configuration, otherwise the project will start with an error.
    IdGenerateType generateType() default IdGenerateType.UNCONTROLLED;

    /**
     * the specified id generator implement by {@link IdGenerator}, you need to set it when using {@link IdGenerateType#CUSTOMER}.
     *
     * @return the specified id generator implement by {@link IdGenerator}
     */
    Class<? extends IdGenerator> generator() default UnsupportedIdGenerator.class;

    /**
     * the specified id generate sql, you need to set it when using {@link IdGenerateType#SQL}.
     *
     * @return the specified id generate sql
     */
    String generateSQL() default "";

}