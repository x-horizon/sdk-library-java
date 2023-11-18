// Copyright (C) 2021-2023 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.web.openfeign;

import cn.srd.library.java.contract.constant.text.SuppressWarningConstant;
import cn.srd.library.java.contract.model.protocol.TransportModel;

import java.lang.annotation.*;

/**
 * enable {@link FeignClientResponseInterceptor}
 *
 * @author wjm
 * @since 2023-03-04 16:48
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface EnableFeignClientResponseModelResolver {

    /**
     * the response models to resolve
     *
     * @return the response models to resolve
     */
    @SuppressWarnings(SuppressWarningConstant.RAW_TYPE)
    Class<? extends TransportModel>[] value();

}
