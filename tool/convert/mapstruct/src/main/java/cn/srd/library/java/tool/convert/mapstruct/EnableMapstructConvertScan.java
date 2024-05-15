// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.mapstruct;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 指定在哪些包下扫描标记了 {@link BindMapstruct} 的类，若不指定，默认在 {@link SpringBootApplication} 所在的包路径下扫描
 *
 * @author wjm
 * @since 2022-06-24 14:29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MapstructConverts.class)
@Deprecated
public @interface EnableMapstructConvertScan {

    /**
     * 指定在哪些包下扫描标记了 {@link BindMapstruct} 的类，若不指定，默认在 {@link SpringBootApplication} 所在的包路径下扫描
     *
     * @return 待扫描的包路径集合
     */
    String[] value();

}