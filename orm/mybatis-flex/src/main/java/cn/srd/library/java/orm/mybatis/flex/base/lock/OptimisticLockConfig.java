// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.lock;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wjm
 * @since 2023-11-12 21:06
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface OptimisticLockConfig {

    String columnName() default "";

}