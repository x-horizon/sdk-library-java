// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.logic;

import com.mybatisflex.annotation.Column;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * the global delete logic config
 *
 * @author wjm
 * @since 2023-11-13 21:19
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface DeleteLogicConfig {

    /**
     * the value when data in database is not logic deleted.
     * <p>
     * supported type as following:
     * <ul>
     *   <li>any string value can convert to integer.</li>
     *   <li>any string value can convert to boolean.</li>
     *   <li>any string value can convert to not blank string.</li>
     * </ul>
     * </p>
     * <p>
     * for example:
     * <ul>
     *   <li>set this field value to string "0", will be convert to integer 0.</li>
     *   <li>set this field value to string "false", will be convert to boolean false.</li>
     *   <li>set this field value to string "normal", will be convert to string "normal".</li>
     * </ul>
     * </p>
     *
     * @return the value when data in database is not logic deleted.
     * @apiNote only when {@link Column#isLogicDelete()} = true on the field will it take effect, like:
     * <pre>
     * {@code
     *  @Column(value = "row_is_deleted", isLogicDelete = true)
     *  private Boolean rowIsDeleted;
     * }
     * </pre>
     */
    String normalValue() default "0";

    /**
     * the value when data in database already logic deleted.
     * <p>
     * supported type as following:
     * <ul>
     *   <li>any string value can convert to integer.</li>
     *   <li>any string value can convert to boolean.</li>
     *   <li>any string value can convert to not blank string.</li>
     * </ul>
     * </p>
     * <p>
     * for example:
     * <ul>
     *   <li>set this field value to string "1", will be convert to integer 1.</li>
     *   <li>set this field value to string "true", will be convert to boolean true.</li>
     *   <li>set this field value to string "invalid", will be convert to string "invalid".</li>
     * </ul>
     * </p>
     *
     * @return the value when data in database already logic deleted.
     * @apiNote only when {@link Column#isLogicDelete()} = true on the field will it take effect, like:
     * <pre>
     * {@code
     *  @Column(value = "row_is_deleted", isLogicDelete = true)
     *  private Boolean rowIsDeleted;
     * }
     * </pre>
     */
    String deletedValue() default "1";

}