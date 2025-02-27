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
     * <pre>
     * the specified global id generate type:
     *
     * 1. when use {@link IdGenerateType#AUTO_INCREMENT}, make sure the primary key in database has already set the autoincrement action,
     *    and it is not necessary to set {@link #generator()}, {@link #generateSQL()}.
     *
     * 2. when use {@link IdGenerateType#UUID}, it will generate the id by {@link UUID#randomUUID()} automatically before insert to database,
     *    and it is not necessary to set {@link #generator()}, {@link #generateSQL()}.
     *
     * 3. when use {@link IdGenerateType#SNOWFLAKE}, it will generate the id by {@link SnowflakeIds#get()} automatically before insert to database,
     *    and it is not necessary to set {@link #generator()}, {@link #generateSQL()},
     *    you must specified the annotation {@link EnableSnowflakeId} on your class path before using this id generate strategy,
     *    about the algorithm of snowflake id as following:
     *    <ul>
     *      <li>{@link EnableSnowflakeId}</li>
     *      <li>{@link SnowflakeIds}</li>
     *      <li><a href="https://github.com/yitter/IdGenerator">the yitter snowflake id generator</a></li>
     *    </ul>
     * </pre>
     *
     * <pre>
     * 4. when use {@link IdGenerateType#CUSTOMER}, you must need to specified a id generator implement {@link IdGenerator},
     *    then will generate id by {@link IdGenerator#generate(Object, String)} before insert to database,
     *    and it is not necessary to set {@link #generateSQL()}.
     *
     * 5. when use {@link IdGenerateType#SQL}, you must need to specified a sql to generate id,
     *    then will execute the specified {@link #generateSQL() sql} when insert to database, like "select SEQ_USER_ID.nextval as id from dual",
     *    and it is not necessary to set {@link #generator()}.
     *
     * 6. when use {@link IdGenerateType#UNCONTROLLED}, the orm mybatis flex system will not interfere with the generation of id,
     *    you can set the id value completely by yourself,
     *    and it is not necessary to set {@link #generator()}, {@link #generateSQL()}.
     * </pre>
     *
     * @return the specified id generate type
     */
    IdGenerateType generateType() default IdGenerateType.UNCONTROLLED; // TODO wjm 此处实现不够好，与 snowflake id 强绑定，客户端不一定需要用到 snowflake id，目前客户端必须提供正确的 redis 配置，否则项目启动报错

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