// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.orm.mybatis.flex.base.listener;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wjm
 * @since 2023-11-13 21:13
 */
@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface ListenerConfig {

    Class<? extends InsertListener> whenInsert() default NoneInsertListener.class;

    Class<? extends UpdateListener> whenUpdate() default NoneUpdateListener.class;

}