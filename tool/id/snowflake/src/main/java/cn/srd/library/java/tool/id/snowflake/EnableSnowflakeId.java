// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.id.snowflake;

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
 * @since 2023-11-13 10:26
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(SnowflakeIdSwitcher.class)
public @interface EnableSnowflakeId {

    /**
     * @return
     */
    SnowflakeIdEnvironment environment() default SnowflakeIdEnvironment.STAND_ALONE_SINGLE_INSTANCE;

    /**
     * <pre>
     * the worker id bit length setter to {@link IdGeneratorOptions#WorkerIdBitLength},
     * sequenceBitLength为 6 、同一个 redis 下的机器数量情况如下：
     * 1  时为 14 位，15069150316997，机器数量 1
     * 2  时为 14 位，30138313089157，机器数量 3
     * 3  时为 14 位，60276641894085，机器数量 7
     * 4  时为 15 位，120553307235589，机器数量 15
     * 5  时为 15 位，241106669551941，机器数量 31
     * 6  时为 15 位，482213446152581，机器数量 63
     * 7  时为 15 位，964424066852037，机器数量 127
     * 8  时为 16 位，1928847375287429，机器数量 255
     * 9  时为 16 位，3857708801393093，机器数量 511
     * 10 时为 16 位，7715419168440837，机器数量 1023
     * 11 时为 17 位，15430842986791493，机器数量 2047
     * 12 时为 17 位，30861693610623621，机器数量 4095
     * 13 时为 17 位，61723400668709573，机器数量 8191
     * 14 时为 18 位，123446826092200709，机器数量 16383
     * 15 时为 18 位，246893708121736005，机器数量 32767
     *
     * </pre>
     *
     * @return
     */
    byte workerIdBitLength() default 6;

    /**
     * {@link IdGeneratorOptions#SeqBitLength}
     *
     * @return
     */
    byte sequenceBitLength() default 6;

    /**
     * 3 的倍数，单位秒
     *
     * @return
     */
    int workerIdCacheTimeout() default 60;

}
