// Copyright (C) 2021-2026 thinkingto.com Ltd. All rights reserved.
// Use of this source code is governed by SRD.
// license that can be found in the LICENSE file.

package cn.srd.library.java.tool.convert.mapstruct.plus;

import cn.srd.library.java.contract.constant.suppress.SuppressWarningConstant;
import cn.srd.library.java.tool.spring.contract.support.Springs;
import io.github.linpeilie.Converter;
import jakarta.annotation.PostConstruct;
import lombok.Getter;

import java.util.List;

/**
 * mapstruct converter
 *
 * @author wjm
 * @since 2021-05-01 14:13
 */
public class MapstructConverts {

    @Getter private static MapstructConverts instance = null;

    private static Converter internalConverter = null;

    @SuppressWarnings(SuppressWarningConstant.ALL)
    @PostConstruct
    public void initialize() {
        instance = this;
        internalConverter = Springs.getBean(Converter.class);
    }

    public <S, R> R toBean(S source, Class<R> targetClass) {
        return internalConverter.convert(source, targetClass);
    }

    public <S, R> List<R> toBeans(List<S> source, Class<R> targetClass) {
        return internalConverter.convert(source, targetClass);
    }

}