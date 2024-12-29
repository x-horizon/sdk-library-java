package cn.srd.library.java.tool.id.snowflake.autoconfigure;

import cn.srd.library.java.tool.id.snowflake.model.enums.SnowflakeIdEnvironment;
import com.github.yitter.contract.IdGeneratorOptions;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <pre>
 * provide an annotation to enable snowflake id system.
 *
 * this snowflake id module is based on yitter snowflake id algorithm,
 * you can refer to the following documents: <a href="https://github.com/yitter/IdGenerator">the yitter snowflake id generator</a>
 * </pre>
 *
 * @author wjm
 * @see SnowflakeIdRegistrar
 * @see SnowflakeIdAutoConfigurer
 * @since 2023-11-13 10:26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(SnowflakeIdRegistrar.class)
public @interface EnableSnowflakeId {

    /**
     * current node instance environment.
     *
     * @return current node instance environment
     * @see #workerIdBitLength()
     * @see #sequenceBitLength()
     */
    SnowflakeIdEnvironment environment() default SnowflakeIdEnvironment.SINGLE_NODE;

    /**
     * the worker id bit length setter to set {@link IdGeneratorOptions#WorkerIdBitLength}.
     *
     * <p>this field determines the max total node instance number in {@link SnowflakeIdEnvironment#MULTIPLE_NODE};<br/>
     * the range of this field value is [1, 15];<br/>
     * the max total node instance number calculation: 2 ^ workerIdBitLength - 1;
     * <ul>
     *   <li>if ensure that in case of single node instance, this field value can be set to {@code 1}.</li>
     *   <li>
     *       if multiple node instance is currently required,
     *       and using {@link #sequenceBitLength()} default value {@code 6},
     *       the factors that affect the value of this field are as following:
     *   </li>
     *     <ol>
     *       <li>this field value set to {@code 1}, the max concurrency node instance number wil be {@code 1}, the number of id generate digit is {@code 14}, the id generate sample: {@code 15069150316997}.</li>
     *       <li>this field value set to {@code 2}, the max concurrency node instance number wil be {@code 3}, the number of id generate digit is {@code 14}, the id generate sample: {@code 30138313089157}.</li>
     *       <li>this field value set to {@code 3}, the max concurrency node instance number wil be {@code 7}, the number of id generate digit is {@code 14}, the id generate sample: {@code 60276641894085}.</li>
     *       <li>this field value set to {@code 4}, the max concurrency node instance number wil be {@code 15}, the number of id generate digit is {@code 15}, the id generate sample: {@code 120553307235589}.</li>
     *       <li>this field value set to {@code 5}, the max concurrency node instance number wil be {@code 31}, the number of id generate digit is {@code 15}, the id generate sample: {@code 241106669551941}.</li>
     *       <li>this field value set to {@code 6}, the max concurrency node instance number wil be {@code 63}, the number of id generate digit is {@code 15}, the id generate sample: {@code 482213446152581}.</li>
     *       <li>this field value set to {@code 7}, the max concurrency node instance number wil be {@code 127}, the number of id generate digit is {@code 15}, the id generate sample: {@code 964424066852037}.</li>
     *       <li>this field value set to {@code 8}, the max concurrency node instance number wil be {@code 255}, the number of id generate digit is {@code 16}, the id generate sample: {@code 1928847375287429}.</li>
     *       <li>this field value set to {@code 9}, the max concurrency node instance number wil be {@code 511}, the number of id generate digit is {@code 16}, the id generate sample: {@code 3857708801393093}.</li>
     *       <li>this field value set to {@code 10}, the max concurrency node instance number wil be {@code 1023}, the number of id generate digit is {@code 16}, the id generate sample: {@code 7715419168440837}.</li>
     *       <li>this field value set to {@code 11}, the max concurrency node instance number wil be {@code 2047}, the number of id generate digit is {@code 17}, the id generate sample: {@code 15430842986791493}.</li>
     *       <li>this field value set to {@code 12}, the max concurrency node instance number wil be {@code 4095}, the number of id generate digit is {@code 17}, the id generate sample: {@code 30861693610623621}.</li>
     *       <li>this field value set to {@code 13}, the max concurrency node instance number wil be {@code 8191}, the number of id generate digit is {@code 17}, the id generate sample: {@code 61723400668709573}.</li>
     *       <li>this field value set to {@code 14}, the max concurrency node instance number wil be {@code 16383}, the number of id generate digit is {@code 18}, the id generate sample: {@code 123446826092200709}.</li>
     *       <li>this field value set to {@code 15}, the max concurrency node instance number wil be {@code 32767}, the number of id generate digit is {@code 18}, the id generate sample: {@code 246893708121736005}.</li>
     *     </ol>
     * </ul>
     *
     * @return the worker id bit length setter to set {@link IdGeneratorOptions#WorkerIdBitLength}
     * @apiNote <li>the sum of {@link #sequenceBitLength()} and this field value must <= 22.</li>
     * <li>the max total node instance number depends on the number of node instance in the shared redis database.</li>
     * @see #sequenceBitLength()
     */
    byte workerIdBitLength() default 6;

    /**
     * the sequence bit length setter to set {@link IdGeneratorOptions#SeqBitLength}.
     *
     * <p>the range of this field value is [4, 21];<br/>
     * this field determines the number of id generated per millisecond:
     * <ul>
     *   <li>if the number of id generated <= 5W per second, just keep the sequence bit length by default value {@code 6}.</li>
     *   <li>if the number of id generated > 5W and <= 50W per second, it is recommended to set the sequence bit length value to {@code 10}.</li>
     *   <li>if the number of id generated > 50W and <= 500W per second, it is recommended to set the sequence bit length value to {@code 12}.</li>
     * </ul>
     *
     * @return the sequence bit length setter to set {@link IdGeneratorOptions#SeqBitLength}.
     * @apiNote <li>the sum of {@link #workerIdBitLength()} and this field value must <= 22.</li>
     * <li>increasing the sequence bit length can improve performance, but the generated id will also be longer.</li>
     * @see #workerIdBitLength()
     */
    byte sequenceBitLength() default 6;

    /**
     * the worker id cache timeout, the unit is seconds, can be set as a multiple of 3.
     *
     * @return the worker id cache timeout
     */
    int workerIdCacheTimeout() default 60;

}